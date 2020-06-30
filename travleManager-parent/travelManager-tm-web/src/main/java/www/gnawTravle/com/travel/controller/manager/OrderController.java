package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 订单控制类
 * @author: wang_sir
 * @create: 2020-06-17 12:11
 **/
@Controller
@RequestMapping("/manager")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("/orderList")
    public ModelAndView orderList(PageParam pageParam, @RequestParam(value = "query", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = orderService.count();
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
            List<Order> list = orderService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
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
        mv.setViewName("order/orderList");
        return mv;
    }

    @RequestMapping("/orderAdd")
    public ModelAndView travelRouteAdd(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity",new Order());
        mv.setViewName("order/orderEdit");
        return mv;
    }

    @RequestMapping("/orderView")
    public ModelAndView orderView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",orderService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("order/orderView");
        return mv;
    }

    @RequestMapping("/orderEdit")
    public ModelAndView orderEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",orderService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("order/orderEdit");
        return mv;
    }

    @RequestMapping("/orderSave")
    public String orderSave(Order entity){

        try {
            if (Tools.isEmpty(entity.getId())){
                orderService.save(entity);
            }else{
                orderService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/orderList";
    }

    @RequestMapping("/orderDelete")
    public String orderDelete(Integer id){
        if(!Tools.isEmpty(id)){
            try {
                orderService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/manager/orderList";
    }
}
