package www.gnawTravle.com.travel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: travleManager-parent
 * @description: 工具类
 * @author: wang_sir
 * @create: 2020-06-11 21:32
 **/
public class Tools {
    public static boolean isEmpty(String s){
        return s==null || " ".equals(s) || "null".equals(s)|| "".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(Object s){
        return s==null || " ".equals(s) || "null".equals(s)|| "".equals(s);
    }



    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format){
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }else{
            return "";
        }
    }

}
