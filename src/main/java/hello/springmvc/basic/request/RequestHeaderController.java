package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


/**
 * @RequestMapping은 어지간한 파라미터를 다 받을 수 있다.
 * Locale 클래스 -> 국가코드 및 국가명, 국가별 언어가 필요할 때
 * MultiValueMap -> 하나의 키에 여러개의 값 가지기가 가능.
 */
@Slf4j // 로그 찍기
@RestController // 응답값을 문자 그대로 http 응답에 반환
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String requestMapping(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpMethod httpMethod,
                                 Locale locale,
                                 @RequestHeader MultiValueMap<String, String> headerMap,
                                 @RequestHeader("host") String host,
                                 @CookieValue(value = "myCookie", required = false) String cookie
                                 ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);


        return "ok";

    }
}
