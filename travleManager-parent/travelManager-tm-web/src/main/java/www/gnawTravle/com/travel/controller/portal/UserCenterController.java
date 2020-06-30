package www.gnawTravle.com.travel.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-19 14:47
 **/
@Controller
public class UserCenterController {
    @RequestMapping("/userCenter")
    public ModelAndView userCenter(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("portal/userCenter");
        return mv;
    }
}
