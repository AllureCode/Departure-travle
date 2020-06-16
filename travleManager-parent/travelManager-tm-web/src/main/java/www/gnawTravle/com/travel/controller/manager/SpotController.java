package www.gnawTravle.com.travel.controller.manager;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.spot.Spot;
import www.gnawTravle.com.travel.service.ISpotService;
import www.gnawTravle.com.travel.utils.Tools;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 景点控制类
 * @author: wang_sir
 * @create: 2020-06-15 15:01
 **/
@Controller
@RequestMapping("/manager")
public class SpotController {
    @Autowired
    private ISpotService spotService;

    /**
     * 展示所有景点信息
     * @param pageParam
     * @param query
     * @return
     */
    @RequestMapping("/scenicSpotList")
    public ModelAndView scenicSpotList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if (pageParam.getPageNumber() < 1) {
            pageParam = new PageParam();
            long count = 0;
            try {
                count = spotService.count();
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
            List<Spot> list = spotService.findByPage(pageParam.getPageNumber(), pageParam.getPageSize(), query);
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
        mv.setViewName("scenicSpot/scenicSpotList");
        return mv;
    }

    /**
     * 跳转到编辑页面
     * @return
     */

    @RequestMapping("/scenicSpotAdd")
    public ModelAndView scenicSpotAdd() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity", new Spot());
        mv.setViewName("scenicSpot/scenicSpotEdit");
        return mv;
    }

    /**
     * 查看当前景点信息
     * @param id
     * @return
     */
    @RequestMapping("/scenicSpotView")
    public ModelAndView scenicSpotView(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", spotService.findById(id));
            System.out.println("测试回显数据"+spotService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("scenicSpot/scenicSpotView");
        return mv;
    }

    /**
     * 回显信息并跳转到编辑页面
     * @param id
     * @return
     */

    @RequestMapping("/scenicSpotEdit")
    public ModelAndView scenicSpotEdit(Integer id) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity", spotService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("scenicSpot/scenicSpotEdit");
        return mv;
    }

    /**
     * 保存
     * @param request
     * @param entity
     * @param file
     * @return
     */
    @RequestMapping("/scenicSpotSave")
    public String scenicSpotSave(HttpServletRequest request,  Spot entity, @RequestParam("fileName") MultipartFile file) {

        try {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\scenicSpot";
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file.transferTo(dest);
                    entity.setImgUrl("/scenicSpot/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())) {
                spotService.save(entity);
            } else {
                spotService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/scenicSpotList";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/scenicSpotDelete")
    public String scenicSpotDelete(Integer id) {
        if (!Tools.isEmpty(id)) {
            try {
                spotService.deleteByid(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  "redirect:/manager/scenicSpotList";
    }
}
