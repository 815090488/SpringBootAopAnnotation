package spring.boot.aop.annotation.annotation;

import java.lang.annotation.*;

/**
 * @author 81509
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String value() default "";
} 