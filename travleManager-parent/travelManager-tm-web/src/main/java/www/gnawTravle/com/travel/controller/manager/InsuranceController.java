package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.insurance.Insurance;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.InsuranceService;
import www.gnawTravle.com.travel.utils.Tools;

import java.io.File;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 保险控制类
 * @author: wang_sir
 * @create: 2020-06-17 10:57
 **/
@Controller
@RequestMapping("/manager")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping("/insuranceList")
    public ModelAndView insuranceList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = insuranceService.count();
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
        try {
            List<Insurance> list = insuranceService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
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
        } catch (Exception e) {
            mv.setViewName("404");
            return mv;
        }
        mv.addObject("pageParam", pageParam);
        mv.setViewName("insurance/insuranceList");
        return mv;
    }

    @RequestMapping("/insuranceAdd")
    public ModelAndView insuranceAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity", new Insurance());
        mv.setViewName("insurance/insuranceEdit");
        return mv;
    }

    @RequestMapping("/insuranceView")
    public ModelAndView insuranceView(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", insuranceService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("insurance/insuranceView");
        return mv;
    }

    @RequestMapping("/insuranceEdit")
    public ModelAndView insuranceEdit(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", insuranceService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("insurance/insuranceEdit");
        return mv;
    }

    @RequestMapping("/insuranceSave")
    public String insuranceSave(Insurance entity, @RequestParam("fileName") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\insurance";
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file.transferTo(dest);
                    entity.setImgUrl("/insurance/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())) {
                insuranceService.save(entity);
            } else {
                if (entity.getImgUrl() == null) {
                    Insurance insurance = insuranceService.findById(entity.getId());
                    entity.setImgUrl(insurance.getImgUrl());
                    insuranceService.update(entity);
                } else {
                    insuranceService.update(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/insuranceList";
    }

    @RequestMapping("/insuranceDelete")
    public String insuranceDelete(Integer id) {
        if (!Tools.isEmpty(id)) {
            try {
                insuranceService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/manager/insuranceList";
    }
}
