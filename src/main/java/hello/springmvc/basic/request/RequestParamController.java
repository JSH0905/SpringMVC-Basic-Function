package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;


/**
 * Jar를 사용하여 내장 톰캣만 사용하는 경우 이전에 했던 /WEB-INF 등의 웹앱 경로를 사용불가. -> 따라서 정적 리소스를 다 스태틱폴더 하위에 넣어서 사용한다.
 */
@Slf4j
@Controller
public class RequestParamController {


    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);


        response.getWriter().write("ok");
    }


    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }


    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 변수명이 쿼리 파라미터의 이름과 같을 경우 ("username") 생략 가능
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age ) { // String, Integer, Int 등의 단순 타입이면 @RequestParam 생략가능, 하지만 우리가 만든 Member 이런건 생략 불가
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // 쿼리 파라미터에 username 값 기입이 필수! 없으면 튕긴다.
            @RequestParam(required = false) Integer age) { // 쿼리 파라미터에 age 값 기입이 필수가 아님. 또한 int는 null값을 가질 수 없으므로 반드시 Integer로 해줘야한다. Integer는 객체 타입이다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // 쿼리 파라미터에 username 값 기입이 필수! 없으면 튕긴다.
            @RequestParam(required = false, defaultValue = "-1") int age) { // 쿼리 파라미터에 age 값 기입이 필수가 아님. 또한 int는 null값을 가질 수 없으므로 반드시 Integer로 해줘야한다. Integer는 객체 타입이다.

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody // 선언시 return의 ok문자를 HTTP 응답 메시지에 넣어서 그대로 반환
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
