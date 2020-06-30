package www.gnawTravle.com.travel.controller.portal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.spot.Spot;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.service.ISpotService;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class SpotPortalController {

    @Autowired
    ISpotService scenicSpotService;
    @Autowired
    IUserService userService;
    @Autowired
    IOrderService orderService;

    @RequestMapping("/travelSpot")
    public ModelAndView travelSpot(PageParam pageParam) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = scenicSpotService.count2();
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
        mv.addObject("pageData", scenicSpotService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize()));
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/travelSpot");
        return mv;
    }

    @RequestMapping("/scenicSpotPortalView")
    public ModelAndView scenicSpotPortalView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",scenicSpotService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/travelSpotView");
        return mv;
    }

    @RequestMapping("/travelSpotCreatOrder")
    public ModelAndView travelSpotCreatOrder(Integer id,HttpSession httpSession){
        ModelAndView mv = new ModelAndView();
        try {
            Spot scenicSpot = scenicSpotService.findById(id);
            User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
            Order order  = new Order();
            order.setImgUrl(scenicSpot.getImgUrl());
            order.setUserId(user.getId()+"");
            order.setUserName(user.getUserName());
            order.setProductId(scenicSpot.getId()+"");
            order.setProductName(scenicSpot.getSpotName());
            order.setFee(scenicSpot.getTicketsMessage());
            order.setProductType(1);
            order.setLinkTel(user.getLinkTel());
            order.setIcCode(user.getIcCode());
            order.setRequirement("无");
            order.setState(0);
            order.setOrderCode("S"+ Tools.getUUID().substring(0,6).toUpperCase());
            order.setOrderTime(Tools.date2Str(new Date(),"yyyy-MM-dd"));
            order.setSetoffTime(scenicSpot.getOpenTime());
            orderService.save(order);
            mv.addObject("entity",scenicSpot);
            mv.addObject("CreatSuccess",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/travelSpotView");
        return mv;
    }
}
