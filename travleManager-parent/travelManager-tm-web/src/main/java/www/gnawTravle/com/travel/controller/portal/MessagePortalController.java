package www.gnawTravle.com.travel.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import www.gnawTravle.com.travel.entity.message.Message;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.service.IMessageService;
import www.gnawTravle.com.travel.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MessagePortalController  {

    @Autowired
    IMessageService messageService;
    @Autowired
    IUserService userService;

    @RequestMapping("/myMessage")
    public ModelAndView myMessage(HttpSession httpSession) throws Exception {
        ModelAndView mv =  new ModelAndView();
        User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        mv.addObject("messageCount", messageService.countByUserId(user.getId()+""));
        mv.setViewName("portal/myMessage");
        return mv;
    }

    @RequestMapping("/saveMeessage")
    public ModelAndView saveMeessage(Message message,HttpSession httpSession,HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        message.setUserId(user.getId()+"");
        message.setUserName(user.getUserName());
        message.setName(user.getName());
        message.setState(1);
        message.setTitle(message.getTitle());
        message.setContent(message.getContent());
        messageService.save(message);
        mv.addObject("messageCount", messageService.countByUserId(user.getId()+""));
        mv.addObject("message","保存成功！");
        mv.setViewName("portal/myMessage");
        return mv;
    }

    @RequestMapping("/messageList")
    public ModelAndView messageList(
            HttpSession httpSession,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize
    ) throws Exception {
        ModelAndView mv =  new ModelAndView();
        User user = userService.findByUserName(httpSession.getAttribute("userName").toString());
        PageParam pageParam = messageService.findByPageByUserId(pageNum,pageSize,user.getId()+"");
        mv.addObject("pageData", pageParam.getResult());
        mv.addObject("pageParam",pageParam);
        mv.setViewName("portal/messageList");
        return mv;
    }
}
