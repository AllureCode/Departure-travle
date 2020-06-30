package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.travelroute.TravelRoute;
import www.gnawTravle.com.travel.service.ITravelRouteService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 旅游路线控制类
 * @author: wang_sir
 * @create: 2020-06-15 10:06
 **/

@Controller
@RequestMapping("/manager")
public class TravelRouteController {

    @Autowired
    private ITravelRouteService travelRouteService;

    @RequestMapping("/travelRouteList")
    public ModelAndView travelRouteList(PageParam pageParam, @RequestParam(value = "query", required = false) String query){

        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = travelRouteService.count();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if(count<=10){
                pageParam.setSize(1);
            }else{
                pageParam.setSize(count%10==0?count/10:count/10+1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(10);
        }
        try {
            List<TravelRoute> list = travelRouteService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);

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
        }catch (Exception e){
            mv.setViewName("404");
            return mv;
        }
        mv.addObject("pageParam",pageParam);
        mv.setViewName("travelRoute/travelRouteList");
        return mv;
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    @RequestMapping("/travelRouteAdd")
    public ModelAndView travelRouteAdd(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity",new TravelRoute());
        mv.setViewName("travelRoute/travelRouteEdit");
        return mv;
    }

    /**
     * 展示当前路线信息
     * @param id
     * @return
     */
    @RequestMapping("/travelRouteView")
    public ModelAndView travelRouteView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",travelRouteService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("travelRoute/travelRouteView");
        return mv;
    }

    /**
     * 回显当前路线信息并跳转到编辑页面
     * @param id
     * @return
     */
    @RequestMapping("/travelRouteEdit")
    public ModelAndView travelRouteEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",travelRouteService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("travelRoute/travelRouteEdit");
        return mv;
    }

    /**
     * 删除当前路线
     * @param id
     * @return
     */
    @RequestMapping("/travelRouteDelete")
    public String travelRouteDelete(Integer id){
        if(!Tools.isEmpty(id)){
            try {
                travelRouteService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/manager/travelRouteList";
    }

    /**
     * 保存
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("/travelRouteSave")
    public String travelRouteSave(HttpServletRequest request,TravelRoute entity, @RequestParam("fileName") MultipartFile file){
        try {

            if(file != null && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);
                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\travelRoute" ;
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file.transferTo(dest);
                    entity.setImgUrl("/travelRoute/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())){
                travelRouteService.save(entity);
            }else{
                if (entity.getImgUrl() == null){
                    TravelRoute travelRoute = travelRouteService.findById(entity.getId());
                    entity.setImgUrl(travelRoute.getImgUrl());
                    travelRouteService.update(entity);
                }else{
                    travelRouteService.update(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/travelRouteList";
    }
}
