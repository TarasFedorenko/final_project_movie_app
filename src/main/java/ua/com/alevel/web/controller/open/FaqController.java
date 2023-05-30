package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {
    @GetMapping
    public String showFaq(){
        return "/pages/open/faq";
    }
}
