package www.gnawTravle.com.travel.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.service.IUserService;

import javax.servlet.http.HttpSession;

@Controller
public class OrderPortalController {

    @Autowired
    IUserService userService;
    @Autowired
    IOrderService orderService;

    @RequestMapping("/myOrder")
    public ModelAndView myOrder(HttpSession httpSession,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        PageParam pageParam = orderService.findByPageByUserId(pageNum,pageSize,user.getId()+"");
        mv.addObject("pageData", pageParam.getResult());
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/myOrder");
        return mv;
    }

    @RequestMapping("/payOrder")
    public String payOrder(Integer id) throws Exception {
        Order order = orderService.findById(id);
        order.setState(1);
        orderService.update(order);
        return "redirect:/myOrder";
    }

    @RequestMapping("/deleteOrder")
    public String deleteOrder(Integer id) throws Exception {
        Order order = orderService.findById(id);
        order.setState(2);
        orderService.update(order);
        return "redirect:/myOrder";
    }
}
