package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 실무에서는 반드시 System.out.println()이 아닌 로그 사용 필수!
 * 개발서버에서는 디버그 레벨, 운영서버에서는 인포 레벨까지 설정하는게 정석.
 */

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "spring";

        // + 연산자를 안쓰는 것은 불필요한 곳에 리소스를 사용하지 않기 위해서. 즉, 의미없는 현상이 일어나지않음.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";

    }
}
