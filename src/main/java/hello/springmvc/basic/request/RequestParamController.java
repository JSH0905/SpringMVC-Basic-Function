package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


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
}
