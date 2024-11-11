package rest.logging_aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ReadControlleeLogging {

    @Pointcut("execution(* rest.read.ReadController.*(..))")
    public void loggingPointCut(){
    }

    @Around("loggingPointCut()")
    public  Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper=new ObjectMapper();
        String methodName=proceedingJoinPoint.getSignature().getName();
        String className=proceedingJoinPoint.getTarget().getClass().getSimpleName();
        Object[] array=proceedingJoinPoint.getArgs();
        log.info("method invoke "+className+" : "+methodName+" arguments : "+objectMapper.writeValueAsString(array));
        Object object=proceedingJoinPoint.proceed();
        log.info(className+" : "+methodName+" response : "+objectMapper.writeValueAsString(object));

        return  object;
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint){
        log.info("Before method invokee :: "+joinPoint.getSignature());
    }

    @AfterReturning("loggingPointCut()")
    public void after(JoinPoint joinPoint){
        log.info("After method invokee :: "+joinPoint.getSignature());
    }

    @AfterThrowing("loggingPointCut()")
    public void afterThrowing(JoinPoint joinPoint){

        log.info("AfterThrowing method :: "+joinPoint.getSignature());
    }
}
