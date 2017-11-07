package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DY on 2017. 11. 7..
 */
@Controller
public class BasicController {

    @RequestMapping(value = "/")
    public String test(){
        return "index";
    }
}