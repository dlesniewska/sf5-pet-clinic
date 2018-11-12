package dagimon.spring5course.sf5petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model) {
        System.out.println("Hello there General Kenobi");
        return "index";
    }

}
