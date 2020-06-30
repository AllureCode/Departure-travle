package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.service.IUserService;

/**
 * @program: travleManager-parent
 * @description: 管理页面控制类
 * @author: wang_sir
 * @create: 2020-06-13 12:28
 **/
@Controller
public class ManagerController {

    @Autowired
    private IUserService userService;

    /**
     * 首页
     * @return
     */
    @GetMapping("/manager/index")
    public ModelAndView manager(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 主页面
     * @return
     * @throws Exception
     */
    @GetMapping("/manager/main")
    public ModelAndView main() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("porvice",userService.countPorvice());
        mv.setViewName("main");

        System.out.println("****************************"+userService.countPorvice());
        return mv;
    }

    /**
     * 错误页面
     * @return
     */
    @GetMapping("/manager/404")
    public ModelAndView notFound(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        return mv;
    }

    /**
     * 系统设置
     * @return
     */
    @GetMapping("/manager/systemParameter")
    public ModelAndView systemParameter(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("systemParameter/systemParameter");
        return mv;
    }
}
