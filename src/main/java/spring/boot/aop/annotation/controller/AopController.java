package spring.boot.aop.annotation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.aop.annotation.annotation.LogAnnotation;

/**
 * @author 81509
 */
@RestController
public class AopController {

    @RequestMapping("/logAop")
    @LogAnnotation("logAopController")
    public Object logAop() {
        System.out.println("logAop controller");
        return "logAop controller";
    }

}
