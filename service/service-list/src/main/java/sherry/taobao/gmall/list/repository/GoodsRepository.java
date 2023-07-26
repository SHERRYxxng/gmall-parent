package sherry.taobao.gmall.list.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import sherry.taobao.gmall.model.list.Goods;

@Repository
public interface GoodsRepository  extends ElasticsearchRepository<Goods,Long> {
}
