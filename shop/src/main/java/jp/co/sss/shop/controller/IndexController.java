package jp.co.sss.shop.controller; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
 
@Controller 
public class IndexController { 
    @RequestMapping(path = "/") 
    public String index() { 
            return "index"; 
    } 
    
    @RequestMapping(path = "/before")
    public String before() {
    	return "before";
    }
    
    @RequestMapping(value = "/after")
    public String after() {
    	return "after";
    }
    
    @RequestMapping(path = "/transition")
    public String sampleTransition() {
    	return "sample_transition";
    }
    
    @RequestMapping(path = "/index_f")
    public String indexForward() {
    	return "forward:/";
    }
    
    @RequestMapping(path = "index_r")
    public String indexRedirect() {
    	return "redirect:/";
    }
    
    @RequestMapping("/layout_view") 
    public String layoutView() { 
     return "layout_view"; 
    }
}