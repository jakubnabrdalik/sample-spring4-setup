package eu.solidcraft.starter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Profile("aspectTest")
class ExampleAspect {
    public static final String ATTACHED_STRING = "AspectWasHere";

    @Around("eu.solidcraft.starter.aop.ExampleAspect.loggedUserNameCall()")
    public String addToName(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = (String) joinPoint.proceed();
        return username + ATTACHED_STRING;
    }

    @Pointcut("execution(String eu.solidcraft.starter.infrastructure.security.LoggedUserRepository.getLoggedUserName())")
    public void loggedUserNameCall() {}
}
