package ru.sberbank.trust.aop;


import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Aspect
@FieldDefaults(level = PRIVATE)
public class Poitness {
/*
    @Before("execution(* sellSquishee(..))")
    public void sayHello(JoinPoint joinPiont) {
        AopLog.append("Hello " + ((Customer) joinPiont.getArgs()[0]).getName() + ". How are you doing? \n");
    }

    @AfterReturning(pointcut = "execution(* sellSquishee(..))",
            returning = "retVal", argNames = "retVal")
    public void askOpinion(Object retVal) {
        AopLog.append("Is " + ((Squishee) retVal).getName() + " Good Enough? \n");
    }

    @AfterThrowing("execution (* sellSquishee(..))")
    public void sayYouAreNotAllowed() {
        AopLog.append("Hmmm... \n");
    }

    @After("execution (* sellSquishee(..))")
    public void sayGoodBye() {
        AopLog.append("Good Bye! \n");
    }

    @Around("execution (* sellSquishee(..))")
    public Object sayPoliteWordsAndSell(ProceedingJoinPoint pjp) throws Throwable {
        AopLog.append("Hi! \n");
        Object retVal = pjp.proceed();
        AopLog.append("See you! \n");
        return retVal;
    }
*/

}
