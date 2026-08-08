package com.rabbi.userservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Centralized logging for the controller and service layers using Spring AOP.
 * Logs method entry (with arguments), successful exit (with return value and
 * execution time), and any exception thrown — so individual classes don't need
 * to scatter {@code log} calls throughout their code.
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /** Matches every method in the controller layer. */
    @Pointcut("execution(* com.rabbi.userservice.controller..*(..))")
    public void controllerLayer() {
    }

    /** Matches every method in the service layer. */
    @Pointcut("execution(* com.rabbi.userservice.service..*(..))")
    public void serviceLayer() {
    }

    /** Combined pointcut covering both the controller and service layers. */
    @Pointcut("controllerLayer() || serviceLayer()")
    public void applicationLayer() {
    }

    /**
     * Logs entry, successful exit and execution time for every advised method,
     * re-throwing any exception unchanged so behaviour is never altered.
     */
    @Around("applicationLayer()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String target = joinPoint.getSignature().getDeclaringType().getSimpleName()
                + "." + joinPoint.getSignature().getName();

        log.info("--> {}() called with args: {}", target, Arrays.toString(joinPoint.getArgs()));

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            log.info("<-- {}() returned: {} ({} ms)", target, result, elapsed);
            return result;
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.debug("<-- {}() failed after {} ms", target, elapsed);
            throw ex;
        }
    }

    /**
     * Logs any exception thrown by the advised layers at ERROR level. The
     * {@code @Around} advice re-throws, so this runs for every failure.
     */
    @AfterThrowing(pointcut = "applicationLayer()", throwing = "ex")
    public void logAfterThrowing(org.aspectj.lang.JoinPoint joinPoint, Throwable ex) {
        String target = joinPoint.getSignature().getDeclaringType().getSimpleName()
                + "." + joinPoint.getSignature().getName();

        log.error("!!! {}() threw {}: {}", target, ex.getClass().getSimpleName(), ex.getMessage());
    }
}
