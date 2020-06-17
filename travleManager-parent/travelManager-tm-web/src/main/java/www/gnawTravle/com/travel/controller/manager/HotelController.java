package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.resources.Messages_de;
import www.gnawTravle.com.travel.entity.hotel.Hotel;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.IHotelService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 酒店管理控制类
 * @author: wang_sir
 * @create: 2020-06-16 10:42
 **/
@Controller
@RequestMapping("/manager")
public class HotelController {
    @Autowired
    private IHotelService hotelService;

    /**
     * 展示所有的酒店
     * @param pageParam
     * @param query
     * @return
     */
    @RequestMapping("/hotelList")
    public ModelAndView hotelList(PageParam pageParam, @RequestParam(value = "query", required = false) String query){
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = hotelService.count2();
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
            List<Hotel> list = hotelService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
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
            return  mv;
        }
        mv.addObject("pageParam",pageParam);
        mv.setViewName("hotel/hotelList");
        return mv;
    }

    /**
     * 跳转到编辑页面
     * @return
     */
    @RequestMapping("/hotelAdd")
    public ModelAndView hotelAdd(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity",new Hotel());
        mv.setViewName("hotel/hotelEdit");
        return mv;
    }

    /**
     * 展示当前酒店信息
     * @param id
     * @return
     */
    @RequestMapping("/hotelView")
    public ModelAndView hotelView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",hotelService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("hotel/hotelView");
        return mv;
    }

    @RequestMapping("/hotelEdit")
    public ModelAndView hotelEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",hotelService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("hotel/hotelEdit");
        return mv;
    }

    /**
     * 保存
     * @param entity
     * @param file
     * @return
     */
    @RequestMapping("/hotelSave")
    public String hotelSave(Hotel entity, @RequestParam("fileName") MultipartFile file){

      try{
            if(file != null && !file.isEmpty()){
                String fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                String path = "E:\\SSM_Case\\Travel\\travleManagerparent\\travleManager-parent\\travelManager-tm-web\\src\\main\\resources\\static\\hotel" ;
                File dest = new File(path + "/" + fileName);
                //判断文件父目录是否存在
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdir();
                }
                try {
                    //保存文件
                    file.transferTo(dest);
                    entity.setImgUrl("/hotel/" + fileName);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (Tools.isEmpty(entity.getId())){

                hotelService.save(entity);
            }else{
                hotelService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/hotelList";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/hotelDelete")
    public String hotelDelete(Integer id){
        if(!Tools.isEmpty(id)){
            try {
                hotelService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/manager/hotelList";
    }
}
