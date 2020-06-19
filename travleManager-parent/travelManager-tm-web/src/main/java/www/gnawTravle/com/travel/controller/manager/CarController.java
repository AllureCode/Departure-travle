package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.car.Car;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.ICarService;
import www.gnawTravle.com.travel.utils.Tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 车票控制类
 * @author: wang_sir
 * @create: 2020-06-17 10:11
 **/
@Controller
@RequestMapping("/manager")
public class CarController {

    @Autowired
    private ICarService carService;

    @RequestMapping("/carList")
    public ModelAndView travelRouteList(PageParam pageParam, @RequestParam(value = "query", required = false) String query){
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = carService.count();
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
        try{
            List<Car> list = carService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);

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
        mv.setViewName("car/carList");
        return mv;
    }

    @RequestMapping("/carAdd")
    public ModelAndView travelRouteAdd(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity",new Car());
        mv.setViewName("car/carEdit");
        return mv;
    }

    @RequestMapping("/carView")
    public ModelAndView travelRouteView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",carService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("car/carView");
        return mv;
    }

    @RequestMapping("/carEdit")
    public ModelAndView travelRouteEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",carService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("car/carEdit");
        return mv;
    }

    @RequestMapping("/carSave")
    public String travelRouteSave(Car entity, @RequestParam("fileName") MultipartFile file){

        try {

            if(file != null && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\car" ;
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file.transferTo(dest);
                    entity.setImgUrl("/car/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())){
                carService.save(entity);
            }else{
                carService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/carList";
    }

    @RequestMapping("/carDelete")
    public String travelRouteDelete(Integer id){
        if(!Tools.isEmpty(id)){
            try {
                carService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/manager/carList";
    }
}
