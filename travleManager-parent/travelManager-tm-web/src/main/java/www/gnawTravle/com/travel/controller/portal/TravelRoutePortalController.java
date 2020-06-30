package www.gnawTravle.com.travel.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.travelroute.TravelRoute;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.service.ITravelRouteService;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-19 15:20
 **/
@Controller
public class TravelRoutePortalController {

    @Autowired
    ITravelRouteService travelRouteService;
    @Autowired
    IUserService userService;
    @Autowired
    IOrderService orderService;



    @RequestMapping("/travelRoute")
    public ModelAndView travelRoute(PageParam pageParam) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = travelRouteService.count2();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageParam.setCount(count);
            if(count<=7){
                pageParam.setSize(1);
            }else{
                pageParam.setSize(count%7==0?count/7:count/7+1);
            }
            pageParam.setPageNumber(1);
            pageParam.setPageSize(7);
        }
        mv.addObject("pageData", travelRouteService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize()));
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/travelRoute");
        return mv;
    }

    @RequestMapping("/travelRoutePortalView")
    public ModelAndView travelRoutePortalView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",travelRouteService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/travelRouteView");
        return mv;
    }

    @RequestMapping("/travelRouteCreatOrder")
    public ModelAndView travelRouteCreatOrder(Integer id,HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        try {
            TravelRoute travelRoute = travelRouteService.findById(id);
            User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
            Order order  = new Order();
            order.setImgUrl(travelRoute.getImgUrl());
            order.setUserId(user.getId()+"");
            order.setUserName(user.getUserName());
            order.setProductId(travelRoute.getId()+"");
            order.setProductName(travelRoute.getTitle());
            order.setFee(travelRoute.getPrice());
            order.setProductType(0);
            order.setLinkTel(user.getLinkTel());
            order.setIcCode(user.getIcCode());
            order.setRequirement("无");
            order.setState(0);
            order.setOrderCode("O"+Tools.getUUID().substring(0,6).toUpperCase());
            order.setOrderTime(Tools.date2Str(new Date(),"yyyy-MM-dd"));
            order.setSetoffTime(travelRoute.getStartTime());
            orderService.save(order);
            mv.addObject("entity",travelRoute);
            mv.addObject("CreatSuccess",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/travelRouteView");
        return mv;
    }


}
