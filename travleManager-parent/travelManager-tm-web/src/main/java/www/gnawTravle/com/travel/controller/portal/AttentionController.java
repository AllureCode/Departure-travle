package www.gnawTravle.com.travel.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AttentionController {
    @RequestMapping("/attention")
    public String attention(){
        return "portal/attention";
    }
}
