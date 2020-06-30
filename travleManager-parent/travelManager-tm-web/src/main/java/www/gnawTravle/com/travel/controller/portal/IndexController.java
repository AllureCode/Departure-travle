package www.gnawTravle.com.travel.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpSession;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-16 15:06
 **/
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String toIndex(){
        return "portal/index";
    }

    @RequestMapping("/goLogin")
    public ModelAndView goLogin(HttpSession httpSession){
        if(!Tools.isEmpty(httpSession.getAttribute("userName"))){
            return new ModelAndView("portal/index");
        }
        return new ModelAndView("portal/login");
    }

    @RequestMapping("/goLogout")
    public ModelAndView goLogout(HttpSession httpSession){
        if(!Tools.isEmpty(httpSession.getAttribute("userName"))){
            httpSession.removeAttribute("userName");
        }
        return new ModelAndView("portal/login");
    }
}
