package www.gnawTravle.com.travel.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.message.Message;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.service.IMessageService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 留言控制类
 * @author: wang_sir
 * @create: 2020-06-16 12:32
 **/
@Controller
@RequestMapping("/manager")
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @RequestMapping("/messageList")
    public ModelAndView messageList(PageParam pageParam, @RequestParam(value = "query", required = false) String query){
        ModelAndView mv = new ModelAndView();
        if(pageParam.getPageNumber()<1){
            pageParam =new PageParam();
            long count = 0;
            try {
                count = messageService.count();
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
            List<Message> list = messageService.findByPage(pageParam.getPageNumber(),pageParam.getPageSize(), query);
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
        mv.setViewName("message/messageList");
        return mv;
    }

    @RequestMapping("/messageAdd")
    public ModelAndView messageAdd(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("entity",new Message());
        mv.setViewName("message/messageEdit");
        return mv;
    }

    @RequestMapping("/messageView")
    public ModelAndView messageView(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",messageService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("message/messageView");
        return mv;
    }

    @RequestMapping("/messageEdit")
    public ModelAndView messageEdit(Integer id){
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("entity",messageService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        mv.setViewName("message/messageEdit");
        return mv;
    }

    @RequestMapping("/messageSave")
    public String messageSave(Message entity){
        try {
            if (Tools.isEmpty(entity.getId())){

                messageService.save(entity);
            }else{
                messageService.update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/manager/messageList";
    }

    @RequestMapping("/messageDelete")
    public String messageDelete(Integer id){
        if(!Tools.isEmpty(id)){
            try {
                messageService.deleteByid(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "redirect:/manager/messageList";
    }
}
