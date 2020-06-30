package www.gnawTravle.com.travel.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.insurance.Insurance;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.service.InsuranceService;
import www.gnawTravle.com.travel.utils.Tools;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class InsurancePortalController {
    @Autowired
    InsuranceService insuranceService;
    @Autowired
    IUserService userService;
    @Autowired
    IOrderService orderService;

    @RequestMapping("/insurance")
    public ModelAndView insurance(PageParam pageParam) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = insuranceService.state1count();
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
        mv.addObject("pageData", insuranceService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize()));
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/insurance");
        return mv;
    }

    @RequestMapping("/insurancePortalView")
    public ModelAndView insurancePortalView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",insuranceService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/insuranceView");
        return mv;
    }

    @RequestMapping("/insuranceCreatOrder")
    public ModelAndView travelRouteCreatOrder(Integer id,HttpSession httpSession){
        ModelAndView mv =  new ModelAndView();
        try {
            Insurance insurance = insuranceService.findById(id);
            User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
            Order order  = new Order();
            order.setImgUrl(insurance.getImgUrl());
            order.setUserId(user.getId()+"");
            order.setUserName(user.getUserName());
            order.setProductId(insurance.getId()+"");

            order.setProductName(insurance.getTitle());
            order.setFee(insurance.getPrice());
            order.setProductType(4);
            order.setLinkTel(user.getLinkTel());
            order.setIcCode(user.getIcCode());
            order.setRequirement("无");
            order.setState(0);
            order.setOrderCode("O"+ Tools.getUUID().substring(0,6).toUpperCase());
            order.setOrderTime(Tools.date2Str(new Date(),"yyyy-MM-dd"));
            order.setSetoffTime(Tools.date2Str(new Date(),"yyyy-MM-dd"));
            orderService.save(order);
            mv.addObject("entity",insurance);
            mv.addObject("CreatSuccess",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/insuranceView");
        return mv;
    }
}
