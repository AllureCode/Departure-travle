package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.strategy.Strategy;
import www.gnawTravle.com.travel.service.IStragtegyService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 攻略控制类
 * @author: wang_sir
 * @create: 2020-06-16 14:20
 **/
@Controller
@RequestMapping("/manager")
public class StrategyController {

    @Autowired
    private IStragtegyService stragtegyService;

    /**
     * 展示所有信息
     *
     * @param pageParam
     * @param query
     * @return
     */
    @RequestMapping("/strategyList")
    public ModelAndView strategyList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = stragtegyService.count();
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
            List<Strategy> list = stragtegyService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
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
        mv.setViewName("strategy/strategyList");
        return mv;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping("/strategyAdd")
    public ModelAndView strategyAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity", new Strategy());
        mv.setViewName("strategy/strategyEdit");
        return mv;
    }

    /**
     * 展示当前信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/strategyView")
    public ModelAndView scenicSpotView(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", stragtegyService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("strategy/strategyView");
        return mv;
    }

    /**
     * 编辑
     *
     * @param id
     * @return
     */
    @RequestMapping("/strategyEdit")
    public ModelAndView scenicSpotEdit(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", stragtegyService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("strategy/strategyEdit");
        return mv;
    }

    /**
     * 保存
     *
     * @param entity
     * @param file1
     * @param file2
     * @return
     */
    @RequestMapping("/strategySave")
    public String strategySave(Strategy entity, @RequestParam("fileName1") MultipartFile file1,
                               @RequestParam("fileName2") MultipartFile file2) {
        try {

            if (file1 != null && !file1.isEmpty()) {
                String fileName = file1.getOriginalFilename();
                int size = (int) file1.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\strategy";
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file1.transferTo(dest);
                    entity.setImgUrl("/strategy/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (file2 != null && !file2.isEmpty()) {
                String fileName = file2.getOriginalFilename();
                int size = (int) file2.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\strategy";
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file2.transferTo(dest);
                    entity.setIntroUrl("/strategy/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())) {

                stragtegyService.save(entity);
            } else {
                if (entity.getImgUrl() == null) {
                    Strategy strategy = stragtegyService.findById(entity.getId());
                    entity.setImgUrl(strategy.getImgUrl());
                    stragtegyService.update(entity);
                } else {
                    stragtegyService.update(entity);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/manager/strategyList";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/strategyDelete")
    public String strategyDelete(Integer id) {
        if (!Tools.isEmpty(id)) {
            try {
                stragtegyService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/manager/strategyList";
    }
}
