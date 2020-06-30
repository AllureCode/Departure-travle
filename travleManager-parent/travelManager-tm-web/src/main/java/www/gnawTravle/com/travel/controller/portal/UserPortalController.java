package www.gnawTravle.com.travel.controller.portal;

import org.junit.runners.model.FrameworkMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.utils.ConstantTool;
import www.gnawTravle.com.travel.utils.Tools;
import javax.servlet.http.HttpSession;

/**
 * @program: travleManager-parent
 * @description: 用户前端控制类
 * @author: wang_sir
 * @create: 2020-06-19 09:18
 **/
@Controller
public class UserPortalController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/userLoging")
    public ModelAndView userLoging(String userName, String password, Model model, HttpSession httpSession){
        if(!Tools.isEmpty(httpSession.getAttribute("userName"))){
            return new ModelAndView(new RedirectView("/userCenter"));
        }
        if (Tools.isEmpty(userName)||Tools.isEmpty(password)){
            model.addAttribute("message", ConstantTool.USER_LOGIN_NAME_PWD_NOT_NULL);
            return new ModelAndView(new RedirectView("/goLogin"));
        }
        try {
            User user = userService.login(userName, password);
            if (Tools.isEmpty(user)){
               model.addAttribute("message",ConstantTool.USER_LOGIN_NAME_PWD_IS_ERROR);
                return new ModelAndView(new RedirectView("/goLogin"));
            }else{
                if (user.getState() == 1) {
                    httpSession.setAttribute("userName",userName);
                    return new ModelAndView(new RedirectView("/userCenter"));
                } else {
                    model.addAttribute("message",ConstantTool.USER_LOGIN_ACC_CANCEL);
                    return new ModelAndView(new RedirectView("/goLogin"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView(new RedirectView("/goLogin"));
    }

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("portal/register");
        return mv;
    }

    /**
     * 进行注册
     * @param checkPassword
     * @return
     * @throws Exception
     */
    @RequestMapping("/registering")
    public ModelAndView registering(User user, String checkPassword, RedirectAttributes redirectAttributes) throws Exception{

        if(!user.getPassword().equals(checkPassword)){
            redirectAttributes.addFlashAttribute("message",ConstantTool.USER_PWD_IS_NOT_UNLIKE);
            return new ModelAndView(new RedirectView("/register"));
        }
        User entity = userService.findByUserName(user.getUserName());
        if (entity != null) {
            redirectAttributes.addFlashAttribute("message",ConstantTool.USER_EXIST);
            return new ModelAndView(new RedirectView("/register"));
        }
        try {
            user.setState(1);
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message",ConstantTool.USER_REGISTER_IS_SUCCESS);
        return new ModelAndView(new RedirectView("/goLogin"));
    }

    @RequestMapping("/personInfo")
    public ModelAndView personInfo(HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",userService.findByUserName(httpSession.getAttribute("userName").toString()));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/personalIntro");
        return mv;
    }

    @RequestMapping("/personSave")
    public String personSave(User entity){
        try{
            userService.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/userCenter";
    }

    @RequestMapping("/changePassword")
    public ModelAndView changePassword(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("portal/changePassword");
        return mv;
    }

    @RequestMapping("/changePasswordSave")
    public ModelAndView changePasswordSave(String password,String newPassword,String checkPassword,HttpSession httpSession) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(Tools.isEmpty(password)||Tools.isEmpty(newPassword)||Tools.isEmpty(checkPassword)){
            mv.addObject("message","密码输入不能为空！");
            mv.setViewName("portal/changePassword");
            return mv;
        }
        User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        if(!user.getPassword().equals(password)){
            mv.addObject("message","原密码输入不正确！");
            mv.setViewName("portal/changePassword");
            return mv;
        }else if(!newPassword.equals(checkPassword)){
            mv.addObject("message","新密码与确认密码不一致！");
            mv.setViewName("portal/changePassword");
            return mv;
        }else{
            user.setPassword(newPassword);
            userService.update(user);
        }
        mv.setViewName("portal/userCenter");
        return mv;
    }
}
