package sherry.taobao.gmall.common.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 */
public class DateUtil {

    private static final String dateFormat = "yyyy-MM-dd";

    /**
     * @Description: 判断两个时间属性相差多少秒负数为时间早,正数为第一个时间晚于第二个时间。
     * @param date1
     * @param date2
     * @return
     */
    public static Long getTimeSubtract(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / 1000;
    }

    /**
     * 返回格式化后的日期字符串,将传入的日期转为简单日期格式化中指定的格式
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);

    }

    /**
     * @Description: 截取比较断两个日期对象的field处的值 。<br>
     *      * 如果第一个日期小于、等于、大于第二个，则对应返回-1、0、1<br>
     *      人话:date1 currentDate: 2023-07-15 10:30:45 date2 activityStartDate: 2023-07-20
     *      15:20:30,这两个时间在月单位前比如日时分秒的结果都是-1<br>
     *         在下面代码中我们先判断当前日期currentDate在第二个日期活动开始日期之后,用来判断活动开始<br>
     *         DateUtils.truncatedCompareTo()截断指定日期天或年或月,指定几个月或者几年
     * @例子: int result = DateUtils.truncatedCompareTo(date1, date2, Calendar.年月日时分秒);<br>
     *       int field的值就是Calendar.年月日时分秒   public final static int YEAR = 1;<br>
     *       1  YEAR         是年 判断是否是同一年,<br><br>
     *       这意味着该方法会忽略两个日期对象的月份、日期、小时、分钟、秒数等其他字段，只比较它们的年份是否相同。<br><br>
     *       假设 beginDate 是 2023-07-25 12:34:56，而 endDate 是 2024-07-25 12:34:59，在使用 Calendar.YEAR<br><br>
     *       字段进行截断比较后，它们截断后的日期部分会变成 2023-01-01 00:00:00 和 2024-01-01 <br><br>
     *       00:00:00。由于只比较了年份部分，而忽略了其他字段，所以在比较时会认为 beginDate 大于 endDate，因为 2023 大于 2024。<br><br>
     *       2  MONTH        是月,判断是否是同一月,<br><br>
     *       3  WEEK_OF_YEAR 是一年里面的第几周,<br><br>
     *       4  WEEK_OF_MONTH是一个月里面的第几周,<br><br>
     *       5  DAY_OF_MONTH 是该月的第几天,<br><br>
     *       5  DATE         是该月的第几天,他们共用代码5,有些开发者更习惯使用 DAY_OF_MONTH 来表示日期，而有些开发者则更习惯使用 DATE 来表示日期，因此提供了这两个常量<br>
     *       Calendar calendar = Calendar.getInstance();<br><br>
     *       calendar.get(5);  // 使用常量5<br><br>
     *       calendar.get(Calendar.DAY_OF_MONTH);  // 使用Calendar类中的DAY_OF_MONTH常量<br><br>
     *       6  DAY_OF_YEAR 是一年里面的第几天,<br><br>
     *       7  DAY_OF_WEEK是 一周里面的第几天,<br><br>
     *       8  DAY_OF_WEEK_IN_MONTH 是第几周的星期几两个参数记住,这里面使用需要指定<br><br>
     *             Calendar calendar = Calendar.getInstance();<br><br>
     *             calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 4); // 第四个<br><br>
     *             calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY); // 星期二（2）<br><br>
     *       9  AM_PM 代表中午前还是中午后,<br><br>
     *       10 HOUR 代表12时制度的小时,<br><br>
     *       11 HOUR_OF_DAY 代表24时制度的小时,上午3点和下午3点在他眼中是同一个三点.<br><br>
     *
     *       field字段来自Calendar类<br>
     *       在上面的注解中我们已经简单的使用了
     *         Calendar cal1 = Calendar.getInstance();
     *          cal1.setTime(date1);
     *          cal1.add(Calendar.MONTH, 4);
     *
     *        12  MINUTE       是分钟<br><br>
     *        13  SECOND       是秒<br><br>
     *        14  MILLISECOND  是毫秒<br><br>
     *
     *
     * @param date1 第一个日期对象，非null
     * @param date2 第二个日期对象，非null
     * @param field Calendar中的阈值
     *              <p>
     *              date1 > date2  返回：1
     *              date1 = date2  返回：0
     *              date1 < date2  返回：-1
     */
    //上面写了很多没用的实际上没有那么难理解是被chatGPT误导了麻了,这个注解就留着作为纪念吧浪费时间了
    //实际上就是忽略比提供单位要小的时间单位,比如提供的是日,只比较年月周日,而不看时分秒了
    public static int truncatedCompareTo(final Date date1, final Date date2, final int field) {
        //调用java提供的DateUtils工具类中的截断比较
        return DateUtils.truncatedCompareTo(date1, date2, field);
    }

    /**
     * 比对时间大小<br>
     * 比较时间大小本质是忽略比第三个参数小的这里是从s开始比较,<br>
     * 如果第一个时间在s以及以上的级别比第二个时间早,返回true
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean dateCompare(Date beginDate, Date endDate) {
                //如果开始时间早于或等于结束时间则返回true
                if (DateUtil.truncatedCompareTo(beginDate, endDate, Calendar.SECOND) == 1) {
                    return false;
                }
        // beginDate  <= endDate
        return true;
    }
    //一个测试类用于便捷传入Calendar的单位
    public static boolean testDateCompare(Date beginDate, Date endDate,final int field) {
        //如果开始时间早于结束时间则返回true
        if (DateUtil.truncatedCompareTo(beginDate, endDate,field) == 1) {
            return false;
        }
        // beginDate  <= endDate
        return true;
    }
}
