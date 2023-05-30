package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/az_list")
public class AZController {
    @GetMapping
    public String showList(){
        return "/pages/open/list";
    }

}
