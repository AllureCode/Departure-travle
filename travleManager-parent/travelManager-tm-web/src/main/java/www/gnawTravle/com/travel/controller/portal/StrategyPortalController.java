package www.gnawTravle.com.travel.controller.portal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.IStragtegyService;

@Controller
public class StrategyPortalController {

    @Autowired
    IStragtegyService strategyService;

    @RequestMapping("/strategy")
    public ModelAndView strategy(PageParam pageParam) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = strategyService.state1count();
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
        mv.addObject("pageData", strategyService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize()));
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/strategy");
        return mv;
    }

    @RequestMapping("/strategyPortalView")
    public ModelAndView strategyPortalView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",strategyService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("portal/strategyView");
        return mv;
    }
}
