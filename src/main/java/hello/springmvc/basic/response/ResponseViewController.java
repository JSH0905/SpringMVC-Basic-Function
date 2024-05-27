package hello.springmvc.basic.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");


        return mav;
    }

    /**
     * @Controller넣으면서 String 반환하면 반환값이 뷰의 논리 이름이 된다.
     */

//    @ResponseBody 추가시 뷰를 찾는게 아니라 반환 문자를 화면에 찍음.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello");

        return "response/hello";
    }
}
