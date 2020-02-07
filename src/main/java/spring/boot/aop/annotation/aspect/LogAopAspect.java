package spring.boot.aop.annotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import spring.boot.aop.annotation.annotation.LogAnnotation;

/**
 * @Aspect 作用是把当前类标识为一个切面供容器读取
 * @author 81509
 */
@Component
@Aspect
public class LogAopAspect {

    /**
     * 创建切点
     */
    @Pointcut(value = "@annotation(spring.boot.aop.annotation.annotation.LogAnnotation)")
    public void logAop() {  
  
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     * @param proceedingJoinPoint
     * @param logAnnotation
     * @return
     */
    @Around("@annotation(logAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, LogAnnotation logAnnotation) {
        System.out.println("方法环绕start.....");
        //获取注解里参数logAnnotation.value()
        System.out.println("aop around:" + logAnnotation.value());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    @Around("logAop()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("方法环绕start.....");
        //获取注解里的值
        System.out.println("aop around");
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    /**
     * 标识一个前置增强方法，相当于BeforeAdvice的功能
     * @param joinPoint
     * @param logAnnotation
     */
    @Before("@annotation(logAnnotation)")
    public void deBefore(JoinPoint joinPoint, LogAnnotation logAnnotation){
        //获取注解参数logAnnotation.value()
        System.out.println("LogAop before:" + logAnnotation.value());
    }
    @Before("logAop()")
    public void deBefore(JoinPoint joinPoint){
        System.out.println("LogAop before:");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("@annotation(logAnnotation)")
    public void after(JoinPoint joinPoint, LogAnnotation logAnnotation){
        System.out.println("方法最后执行....."+logAnnotation.value());
    }
    @After("logAop()")
    public void after(JoinPoint joinPoint){
        System.out.println("方法最后执行.....");
    }

    /**
     * 后置增强，相当于AfterReturningAdvice，方法退出时执行
     * @param res
     * @throws Throwable
     */
    @AfterReturning(returning = "res", pointcut = "@annotation(logAnnotation)")
    public void doAfterReturning(Object res, LogAnnotation logAnnotation) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + res+"----"+logAnnotation.value());
    }
    @AfterReturning(returning = "res", pointcut = "logAop()")
    public void doAfterReturning(Object res) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + res);
    }

    /**
     * 异常抛出增强，相当于ThrowsAdvice
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(throwing = "exception", pointcut = "logAop()")
    public void throes(JoinPoint joinPoint, Exception exception){
        System.out.println("方法异常时执行.....");
    }


}  
