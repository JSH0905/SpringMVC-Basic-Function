package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * Stream은 bytecode 이므로 항상 인코딩을 지정해줘야한다.
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 항상

        log.info("messageBody={}", message);

        response.getWriter().write("ok");
    }

    /**
     * Servlet에 대한 통째로가 필요없어서 InputStream만 가져옴.
     * HttpServletResponse가 빠졌으므로 Writer를 따로 파라미터로 가져옴.
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 항상

        log.info("messageBody={}", message);

        responseWriter.write("ok");
    }

    /**
     * HttpEntity<> : Http header, body 정보를 편리하게 조회할수 있는 객체, @RequestParam, @ModelAttribute와는 아무런 상관없음.
     * HttpEntity<>를 통한 바디 메시지 컨버팅. -> V2처럼 직접 InputStream 이런거 안해줘도됨.
     * 반환타입도 HttpEntity<>로 해서 응답 가능.
     */

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();

        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * @RequestBody : Http 바디 정보를 편리하게 조회. 헤더 정보가 필요하다면 HttpEntity<>나 @RequestHeader 사용.
     * 이또한 요청 파라미터를 조회하는 @RequestParam, @ModelAttribute와 상관이 없다.
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
