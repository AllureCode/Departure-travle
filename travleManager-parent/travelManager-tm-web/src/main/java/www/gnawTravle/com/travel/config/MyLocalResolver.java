package www.gnawTravle.com.travel.config;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @program: travleManager-parent
 * @description: 根据浏览器参数自动变化语言
 * @author: wang_sir
 * @create: 2020-06-11 20:15
 **/
public class MyLocalResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取到浏览器传递的参数
        String parameter = request.getParameter("l");
        Locale locale=null;
        if (!StringUtils.isEmpty(parameter)){
            String[] s = parameter.split("_");
            locale = new Locale(s[0],s[1]);
        }else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
