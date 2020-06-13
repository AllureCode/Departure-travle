package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.gnawTravle.com.travel.entity.admin.Admin;
import www.gnawTravle.com.travel.service.IAdminService;
import www.gnawTravle.com.travel.utils.ConstantTool;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: travleManager-parent
 * @description: 登录控制器
 * @author: wang_sir
 * @create: 2020-06-11 21:29
 **/
@Controller
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        if (Tools.isEmpty(username) || Tools.isEmpty(password)) {
            model.addAttribute("msg", ConstantTool.USERNAME_PASSWORD_IS_NULL);
            return "login";
        }
        try {
            Admin admin = adminService.login(username, password);
            if (Tools.isEmpty(admin)){
                model.addAttribute("msg", ConstantTool.LOGIN_USERNAME_NOT_EXIST);
            }else {
                if (admin.getState()==1){
                    session.setAttribute("admin", admin);
                    return "redirect:manager/login";
                }else {
                    model.addAttribute("msg", ConstantTool.ACCOUNT_DISABLE);
                    return "login";
                }
            }
        } catch (Exception e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
        return null;
    }

    /**
     * 跳转到管理员登录
     * @param request
     * @return
     */
    @GetMapping("/manager/login")
    public  String adminLogin(HttpServletRequest request){
        Object admin = request.getSession().getAttribute("admin");
        if (admin!=null){
            return "index";
        }else {
            return "login";
        }
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        Object admin = request.getSession().getAttribute("admin");
        if (admin!=null){
            request.getSession().removeAttribute("admin");
        }
        return "login";
    }
}
