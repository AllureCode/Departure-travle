package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.admin.Admin;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.IAdminService;
import www.gnawTravle.com.travel.utils.ConstantTool;
import www.gnawTravle.com.travel.utils.Tools;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 管理层控制类
 * @author: wang_sir
 * @create: 2020-06-14 13:23
 **/
@Controller
@RequestMapping("/manager")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 展示所有的管理员
     *
     * @param pageParam
     * @param query
     * @return
     */
    @RequestMapping("/adminList")
    public ModelAndView adminList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = adminService.count();
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
        }
        List<Admin> list = adminService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
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
        mv.setViewName("admin/adminList");
        return mv;
    }

    /**
     * 跳转到编辑页面
     *
     * @return
     */
    @RequestMapping("/adminAdd")
    public String adminAdd(Model model) {
        model.addAttribute("entity", new Admin());
        return "admin/adminEdit";
    }

    /**
     * 保存用户和修改
     * @param id  根据id判断当前是保存还是修改
     * @param admin
     * @return
     */
    @RequestMapping("/adminSave")
    public ModelAndView adminSave(Integer id, Admin admin) {
        ModelAndView mv = new ModelAndView();
        try {
            if (Tools.isEmpty(id)) {
                Admin object = adminService.findByUserName(admin.getUserName());
                if (Tools.isEmpty(object)) {
                    adminService.save(admin);
                } else {
                    mv.addObject("message", ConstantTool.USER_EXIST);
                    mv.setViewName("/admin/adminEdit");
                    return mv;
                }
            } else {
                adminService.update(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("404");
            return mv;
        }
        mv.addObject("pageData", adminService.findByPage(1, 10, null));
        PageParam pageParam = new PageParam();
        long count = 0;
        try {
            count = adminService.count();
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
        mv.setViewName("admin/adminList");
        return mv;
    }

    /**
     * 根据id查询当前用户并且展示其信息
     * @param id
     * @return
     */
    @RequestMapping("/adminView")
    public String adminView(Integer id,Model model){

        try {
            Admin entity = adminService.findById(id);
            model.addAttribute("entity", entity);
            return "admin/adminView";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
    }

    /**
     * 根据id查询用户信息回显并且修改
     * @param id
     * @return
     */
    @RequestMapping("/adminEdit")
    public String adminEdit(Integer id,Model model){
        try {
            model.addAttribute("entity",adminService.findById(id));
            return "admin/adminEdit";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/adminDelete")
    public String adminDelete(Integer id){
        try {
            adminService.deleteByid(id);
            return "redirect:/manager/adminList";
        } catch (Exception e) {
            e.printStackTrace();
            return "404";
        }
    }
}
