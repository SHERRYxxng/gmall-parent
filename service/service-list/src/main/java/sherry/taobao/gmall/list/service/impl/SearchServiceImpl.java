package sherry.taobao.gmall.list.service.impl;


import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sherry.taobao.gmall.list.repository.GoodsRepository;
import sherry.taobao.gmall.list.service.SearchService;
import sherry.taobao.gmall.model.list.*;
import sherry.taobao.gmall.model.product.BaseAttrInfo;
import sherry.taobao.gmall.model.product.BaseCategoryView;
import sherry.taobao.gmall.model.product.BaseTrademark;
import sherry.taobao.gmall.model.product.SkuInfo;
import sherry.taobao.gmall.product.client.ProductFeignClient;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Resource
    private ProductFeignClient productFeignClient;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    /**
     * @Description
     * @Date 2023/8/8 16:28
     * @Param [searchParam]
     * @return sherry.taobao.gmall.model.list.SearchResponseVo
     * @Author SHERRY
     */
    @Override
    public SearchResponseVo search(SearchParam searchParam) throws IOException {
        // 构建dsl语句
        SearchRequest searchRequest = this.buildQueryDsl(searchParam);
        SearchResponse response = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);

        SearchResponseVo responseVO = this.parseSearchResult(response);
        responseVO.setPageSize(searchParam.getPageSize());
        responseVO.setPageNo(searchParam.getPageNo());
        long totalPages = (responseVO.getTotal()+searchParam.getPageSize()-1)/searchParam.getPageSize();
        responseVO.setTotalPages(totalPages);
        return responseVO;
    }
    // 制作dsl 语句
    private SearchRequest buildQueryDsl(SearchParam searchParam) {
        // 构建查询器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 构建boolQueryBuilder
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 判断查询条件是否为空 关键字
        if (!StringUtils.isEmpty(searchParam.getKeyword())){
            // 小米手机  小米and手机
            // MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("title",searchParam.getKeyword()).operator(Operator.AND);
            MatchQueryBuilder title = QueryBuilders.matchQuery("title", searchParam.getKeyword()).operator(Operator.AND);
            boolQueryBuilder.must(title);
        }
        // 构建品牌查询
        String trademark = searchParam.getTrademark();
        if (!StringUtils.isEmpty(trademark)){
            // trademark=2:华为
            String[] split = StringUtils.split(trademark, ":");
            if (split != null && split.length == 2) {
                // 根据品牌Id过滤
                boolQueryBuilder.filter(QueryBuilders.termQuery("tmId", split[0]));
            }
        }

        // 构建分类过滤 用户在点击的时候，只能点击一个值，所以此处使用term
        if(null!=searchParam.getCategory1Id()){
            boolQueryBuilder.filter(QueryBuilders.termQuery("category1Id",searchParam.getCategory1Id()));
        }
        // 构建分类过滤
        if(null!=searchParam.getCategory2Id()){
            boolQueryBuilder.filter(QueryBuilders.termQuery("category2Id",searchParam.getCategory2Id()));
        }
        // 构建分类过滤
        if(null!=searchParam.getCategory3Id()){
            boolQueryBuilder.filter(QueryBuilders.termQuery("category3Id",searchParam.getCategory3Id()));
        }




        // 构建平台属性查询
        // 23:4G:运行内存
        String[] props = searchParam.getProps();
        if (props!=null && props.length>0){
            // 循环遍历
            for (String prop : props) {
                // 23:4G:运行内存
                String[] split = StringUtils.split(prop, ":");
                if (split!=null && split.length==3){
                    // 构建嵌套查询
                    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                    // 嵌套查询子查询
                    BoolQueryBuilder subBoolQuery = QueryBuilders.boolQuery();
                    // 构建子查==询中的过滤条件
                    subBoolQuery.must(QueryBuilders.termQuery("attrs.attrId",split[0]));
                    subBoolQuery.must(QueryBuilders.termQuery("attrs.attrValue",split[1]));
                    // ScoreMode.None ？
                    boolQuery.must(QueryBuilders.nestedQuery("attrs",subBoolQuery, ScoreMode.None));
                    // 添加到整个过滤对象中
                    boolQueryBuilder.filter(boolQuery);
                }
            }
        }
        // 执行查询方法
        searchSourceBuilder.query(boolQueryBuilder);
        // 构建分页
        int from = (searchParam.getPageNo()-1)*searchParam.getPageSize();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(searchParam.getPageSize());

        // 排序
        String order = searchParam.getOrder();
        if (!StringUtils.isEmpty(order)){
            // 判断排序规则
            String[] split = StringUtils.split(order, ":");
            if (split!=null && split.length==2){
                // 排序的字段
                String field = null;
                // 数组中的第一个参数
                switch (split[0]){
                    case "1":
                        field="hotScore";
                        break;
                    case "2":
                        field="price";
                        break;
                }
                searchSourceBuilder.sort(field,"asc".equals(split[1])? SortOrder.ASC:SortOrder.DESC);
            }else {
                // 没有传值的时候给默认值
                searchSourceBuilder.sort("hotScore",SortOrder.DESC);
            }
        }

        // 构建高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.postTags("</span>");
        highlightBuilder.preTags("<span style=color:red>");

        searchSourceBuilder.highlighter(highlightBuilder);

        //  设置品牌聚合
        TermsAggregationBuilder termsAggregationBuilder =        AggregationBuilders.terms("tmIdAgg").field("tmId")
                .subAggregation(AggregationBuilders.terms("tmNameAgg").field("tmName"))
                .subAggregation(AggregationBuilders.terms("tmLogoUrlAgg").field("tmLogoUrl"));

        searchSourceBuilder.aggregation(termsAggregationBuilder);

        //  设置平台属性聚合
        searchSourceBuilder.aggregation(AggregationBuilders.nested("attrAgg", "attrs")
                .subAggregation(AggregationBuilders.terms("attrIdAgg").field("attrs.attrId")
                        .subAggregation(AggregationBuilders.terms("attrNameAgg").field("attrs.attrName"))
                        .subAggregation(AggregationBuilders.terms("attrValueAgg").field("attrs.attrValue"))));


        // 结果集过滤
        searchSourceBuilder.fetchSource(new String[]{"id","defaultImg","title","price"},null);

        SearchRequest searchRequest = new SearchRequest("goods");
        //searchRequest.types("_doc");
        searchRequest.source(searchSourceBuilder);
        System.out.println("dsl:"+searchSourceBuilder.toString());
        return searchRequest;
    }

    // 制作返回结果集
    private SearchResponseVo parseSearchResult(SearchResponse response) {
        SearchHits hits = response.getHits();
        //声明对象
        SearchResponseVo searchResponseVo = new SearchResponseVo();
        //获取品牌的集合
        Map<String, Aggregation> aggregationMap = response.getAggregations().asMap();
        //ParsedLongTerms ?
        ParsedLongTerms tmIdAgg = (ParsedLongTerms) aggregationMap.get("tmIdAgg");
        List<SearchResponseTmVo> trademarkList = tmIdAgg.getBuckets().stream().map(bucket -> {
            SearchResponseTmVo trademark = new SearchResponseTmVo();
            //获取品牌Id
            trademark.setTmId((Long.parseLong(((Terms.Bucket) bucket).getKeyAsString())));
            //trademark.setTmId(Long.parseLong(bucket.getKeyAsString()));
            //获取品牌名称
            Map<String, Aggregation> tmIdSubMap = ((Terms.Bucket) bucket).getAggregations().asMap();
            ParsedStringTerms tmNameAgg = (ParsedStringTerms) tmIdSubMap.get("tmNameAgg");
            String tmName = tmNameAgg.getBuckets().get(0).getKeyAsString();

            trademark.setTmName(tmName);
            ParsedStringTerms tmLogoUrlAgg = (ParsedStringTerms) tmIdSubMap.get("tmLogoUrlAgg");
            String tmLogoUrl = tmLogoUrlAgg.getBuckets().get(0).getKeyAsString();
            trademark.setTmLogoUrl(tmLogoUrl);

            return trademark;
        }).collect(Collectors.toList());
        searchResponseVo.setTrademarkList(trademarkList);

        //赋值商品列表
        SearchHit[] subHits = hits.getHits();
        List<Goods> goodsList = new ArrayList<>();
        if (subHits!=null && subHits.length>0){
            //循环遍历
            for (SearchHit subHit : subHits) {
                // 将subHit 转换为对象
                Goods goods = JSONObject.parseObject(subHit.getSourceAsString(), Goods.class);

                //获取高亮
                if (subHit.getHighlightFields().get("title")!=null){
                    Text title = subHit.getHighlightFields().get("title").getFragments()[0];
                    goods.setTitle(title.toString());
                }
                goodsList.add(goods);
            }
        }
        searchResponseVo.setGoodsList(goodsList);

        //获取平台属性数据
        ParsedNested attrAgg = (ParsedNested) aggregationMap.get("attrAgg");
        ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attrIdAgg");
        List<? extends Terms.Bucket> buckets = attrIdAgg.getBuckets();
        if (!CollectionUtils.isEmpty(buckets)){
            List<SearchResponseAttrVo> searchResponseAttrVOS = buckets.stream().map(bucket -> {
                //声明平台属性对象
                SearchResponseAttrVo responseAttrVO = new SearchResponseAttrVo();
                //设置平台属性值Id
                responseAttrVO.setAttrId(((Terms.Bucket) bucket).getKeyAsNumber().longValue());
                ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attrNameAgg");
                List<? extends Terms.Bucket> nameBuckets = attrNameAgg.getBuckets();
                responseAttrVO.setAttrName(nameBuckets.get(0).getKeyAsString());
                //设置规格参数列表
                ParsedStringTerms attrValueAgg = ((Terms.Bucket) bucket).getAggregations().get("attrValueAgg");
                List<? extends Terms.Bucket> valueBuckets = attrValueAgg.getBuckets();

                List<String> values = valueBuckets.stream().map(Terms.Bucket::getKeyAsString).collect(Collectors.toList());
                responseAttrVO.setAttrValueList(values);

                return responseAttrVO;

            }).collect(Collectors.toList());
            searchResponseVo.setAttrsList(searchResponseAttrVOS);
        }
        //获取总记录数
        searchResponseVo.setTotal(hits.getTotalHits().value);

        return searchResponseVo;
    }



    /**
     * 商品的上架
     * @param skuId
     */
    @Override
    public void upperGoods(Long skuId) {
        //创建商品对象
        Goods goods=new Goods();

        //获取skuInfo

        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if(skuInfo!=null){

            goods.setId(skuId);
            goods.setDefaultImg(skuInfo.getSkuDefaultImg());
            goods.setTitle(skuInfo.getSkuName());
            goods.setPrice(skuInfo.getPrice().doubleValue());
            goods.setCreateTime(new Date());
            //品牌查询
            BaseTrademark trademark = productFeignClient.getTrademark(skuInfo.getTmId());
            goods.setTmId(trademark.getId());
            goods.setTmName(trademark.getTmName());
            goods.setTmLogoUrl(trademark.getLogoUrl());
            //三级分类数据
            BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
            goods.setCategory1Id(categoryView.getCategory1Id());
            goods.setCategory1Name(categoryView.getCategory1Name());
            goods.setCategory2Id(categoryView.getCategory2Id());
            goods.setCategory2Name(categoryView.getCategory2Name());
            goods.setCategory3Id(categoryView.getCategory3Id());
            goods.setCategory3Name(categoryView.getCategory3Name());

        }

        //查询平台属性数据
        List<BaseAttrInfo> attrList = productFeignClient.getAttrList(skuId);

        //判断
        if(!CollectionUtils.isEmpty(attrList)){
            List<SearchAttr> searchAttrList = attrList.stream().map(baseAttrInfo -> {

                //创建SearchAttr
                SearchAttr searchAttr = new SearchAttr();
                searchAttr.setAttrId(baseAttrInfo.getId());
                searchAttr.setAttrName(baseAttrInfo.getAttrName());
                searchAttr.setAttrValue(baseAttrInfo.getAttrValueList().get(0).getValueName());

                return searchAttr;

            }).collect(Collectors.toList());

            goods.setAttrs(searchAttrList);

        }



        goodsRepository.save(goods);
    }

    /**
     * 商品下架
     * @param skuId
     */
    @Override
    public void lowerGoods(Long skuId) {

        goodsRepository.deleteById(skuId);
    }

    /**
     * 更新商品的热度排名
     * @param skuId
     *
     * 问题：
     *     1.自增是非原子性
     *         redis-原子性
     *     需求：
     *       hotscore   21   8
     *                  22   6
     *                  23   5
     *    类型： zset        incr
     *
     *     2.修改太过频繁
     *         es-磁盘--实时数据更新
     *         每10次修改，记录redis
     *
     *
     */
    @Override
    public void incrHotScore(Long skuId) {

        //定义操作key
        String hotKey="hotscore";
        //统计自增
        Double count = redisTemplate.opsForZSet().incrementScore(hotKey, "skuId:" + skuId, 1);
        //取模是10
        if(count%10==0){
            //获取
            Goods goods = goodsRepository.findById(skuId).get();
            //判断
            if(goods!=null){
                goods.setHotScore(Math.round(count));
                goodsRepository.save(goods);
            }
        }







    }
}
