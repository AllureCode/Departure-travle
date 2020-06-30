package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.service.*;

/**
 * @program: travleManager-parent
 * @description: 数据管理控制类
 * @author: wang_sir
 * @create: 2020-06-17 13:11
 **/
@Controller
@RequestMapping("/manager")
public class DataController {

    @Autowired
    IUserService userService;
    @Autowired
    ITravelRouteService travelRouteService;
    @Autowired
    ISpotService spotService;
    @Autowired
    IHotelService hotelService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IStragtegyService stragtegyService;
    @Autowired
    ICarService carService;
    @Autowired
    InsuranceService insuranceService;

    @RequestMapping("/userData")
    public ModelAndView userDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state1", userService.state1count());
        mv.addObject("state2", userService.state2count());
        mv.setViewName("data/userData");
        return mv;
    }


    @RequestMapping("/travelRouteData")
    public ModelAndView travelRouteDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", travelRouteService.state0count());
        mv.addObject("state1", travelRouteService.state1count());
        mv.addObject("state2", travelRouteService.state2count());
        mv.setViewName("data/travelRouteData");
        return mv;
    }


    @RequestMapping("/scenicSpotData")
    public ModelAndView scenicSpotDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", spotService.state0count());
        mv.addObject("state1", spotService.state1count());
        mv.addObject("state2", spotService.state2count());
        mv.setViewName("data/scenicSpotData");
        return mv;
    }

    @RequestMapping("/hotelData")
    public ModelAndView hotelDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("hotel0", hotelService.state0count());
        mv.addObject("hotel1", hotelService.state1count());
        mv.addObject("hotel2", hotelService.state2count());
        System.out.println(hotelService.state0count());
        System.out.println(hotelService.state1count());
        System.out.println(hotelService.state2count());

        mv.setViewName("data/hotelData");
        return mv;
    }


    @RequestMapping("/orderData")
    public ModelAndView orderDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", orderService.state0count());
        mv.addObject("state1", orderService.state1count());
        mv.addObject("state2", orderService.state2count());
        mv.setViewName("data/orderData");
        return mv;
    }

    @RequestMapping("/strategyData")
    public ModelAndView strategyDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", stragtegyService.state0count());
        mv.addObject("state1", stragtegyService.state1count());
        mv.addObject("state2", stragtegyService.state2count());
        mv.setViewName("data/strategyData");
        return mv;
    }

    @RequestMapping("/carData")
    public ModelAndView carDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", carService.state0count());
        mv.addObject("state1", carService.state1count());
        mv.addObject("state2", carService.state2count());
        mv.addObject("type0", carService.type0count());
        mv.addObject("type1", carService.type1count());
        mv.addObject("type2", carService.type2count());
        System.out.println(carService.state0count());
        System.out.println(carService.state0count());
        System.out.println(carService.state0count());
        System.out.println(carService.type0count());
        System.out.println(carService.type1count());
        System.out.println(carService.type2count());
        mv.setViewName("data/carData");
        return mv;
    }

    @RequestMapping("/insuranceData")
    public ModelAndView insuranceDate() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("state0", insuranceService.state0count());
        mv.addObject("state1", insuranceService.state1count());
        mv.addObject("state2", insuranceService.state2count());
        mv.addObject("company0", insuranceService.company0count());
        mv.addObject("company1", insuranceService.company1count());

        mv.setViewName("data/insuranceData");
        return mv;
    }
}
