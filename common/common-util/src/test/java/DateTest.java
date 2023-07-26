import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import sherry.taobao.gmall.common.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author: SHERRY
 * @email: <a href="mailto:SherryTh743779@gmail.com">TianHai</a>
 * @Date: 2023/7/25 11:36
 */
public class DateTest {
    @Test
    public void test(){
        Date date = new Date();
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.SECOND, -1);
        calendar.add(Calendar.MINUTE, -1);

//        calendar.add(Calendar.HOUR,2);
        //date1的时间为当前时间推迟一个月
        //calendar.add(Calendar.MONTH,1);
        //date1的时间提前一年
//        calendar.add(Calendar.YEAR,2);
        date1 = calendar.getTime();
        System.out.println("data1: "+date1);//data1的时间大于data的时间,
        // data1为8月秒小于date但是我们不管是比较月份还是秒得到的都是false
        System.out.println("date: " + date);//
        DateUtil dateUtil = new DateUtil();
        dateUtil.dateCompare(date1,date);
        //第一个参数的时间小于等于第二个时间的单位为true在S符合
        System.out.println("DateUtil.getTimeSubtract(data1,data) = " + DateUtil.getTimeSubtract(date1, date));
    }
}
