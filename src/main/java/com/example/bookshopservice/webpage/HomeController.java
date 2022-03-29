package com.example.bookshopservice.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // mark this class as a Spring MVC controller.
public class HomeController {

    // @RequestMapping flags the index() method to support the "/" route.
    @RequestMapping(value = "/")
    public String index() {
        // It returns "index" as the name of the template, which
        // Spring Boot’s autoconfigured view resolver will map
        // to "src/main/resources/templates/index.html".
        return "index";
    }
}
