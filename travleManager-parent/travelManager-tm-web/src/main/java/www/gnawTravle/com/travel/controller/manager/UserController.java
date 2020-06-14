package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.utils.Tools;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 注册用户控制类
 * @author: wang_sir
 * @create: 2020-06-13 15:20
 **/
@Controller
@RequestMapping("/manager")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 展示所有用户信息
     *
     * @param pageParam
     * @param query
     * @return
     */
    @RequestMapping("/userList")
    public ModelAndView userList(PageParam pageParam,
                                 @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = userService.count();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if (count <= 10) {
                pageParam.setSize(1);
            } else {
                pageParam.setSize((count % 10 == 0 ? (count / 10) : (count / 10 + 1)));
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        List<User> list = userService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
        mv.addObject("pageData", list);
        if (!Tools.isEmpty(query)) {
            mv.addObject("query", query);
            pageParam.setCount(list.size());
            if (list.size() > pageParam.getPageSize()) {
                pageParam.setSize(list.size() / pageParam.getPageSize());
            } else {
                pageParam.setSize(1);
            }
        }
        mv.addObject("pageParam", pageParam);
        mv.setViewName("user/allUsers");
        return mv;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("/userAdd")
    public ModelAndView userAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity", new User());
        mv.setViewName("user/userEdit");
        return mv;
    }

    /**
     * 用户保存与修改
     * @param id
     * @return
     */
    @RequestMapping("/userSave")
    public ModelAndView userSave(Integer id, User entity) {
        ModelAndView mv = new ModelAndView();
        try {
            if (Tools.isEmpty(id)) {
                if (Tools.isEmpty(entity.getId())) {
                    User object = userService.findByUserName(entity.getUserName());
                    if (object != null) {
                        mv.addObject("message", "用户名已存在!");
                        mv.addObject("entity", entity);
                        mv.setViewName("user/userEdit");
                        return mv;
                    }
                    userService.save(entity);
                }
            } else {
                userService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("pageData", userService.findByPage(1, 10, null));
        PageParam pageParam = new PageParam();
        long count = 0;
        try {
            count = userService.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageParam.setCount(count);
        if (count <= 10) {
            pageParam.setSize(1);
        } else {
            pageParam.setSize(count % 10 == 0 ? count / 10 : count / 10 + 1);
        }
        pageParam.setPageNumber(1);
        pageParam.setPageSize(10);
        mv.addObject("pageParam", pageParam);
        mv.setViewName("user/allUsers");
        return mv;
    }

    /**
     * 跳转到修改页面并且回显用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/userEdit")
    public ModelAndView userEdit(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", userService.findById(id));
            mv.setViewName("user/userEdit");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("404");
            return mv;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/userDelete")
    public String userDelete(Integer id) {
        try {
            userService.deleteByid(id);
            return "redirect:/manager/userList";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
    }

    @RequestMapping("/userView")
    public ModelAndView userView(Integer id){
        ModelAndView mv = new ModelAndView();
        User user = null;
        try {
            user = userService.findById(id);
            mv.addObject("entity", user);
            mv.setViewName("user/userView");
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("404");
            return mv;
        }
    }
}
