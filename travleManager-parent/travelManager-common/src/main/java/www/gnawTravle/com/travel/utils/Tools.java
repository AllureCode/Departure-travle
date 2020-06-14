package www.gnawTravle.com.travel.utils;

import java.util.UUID;

/**
 * @program: travleManager-parent
 * @description: 工具类
 * @author: wang_sir
 * @create: 2020-06-11 21:32
 **/
public class Tools {
    public static boolean isEmpty(String s){
        return s==null || " ".equals(s) || "null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(Object s){
        return s==null || " ".equals(s) || "null".equals(s);
    }

}
