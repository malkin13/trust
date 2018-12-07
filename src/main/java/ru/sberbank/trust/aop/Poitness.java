package ru.sberbank.trust.aop;


import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.sberbank.trust.Person;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Aspect
@Component
@FieldDefaults(level = PRIVATE)
public class Poitness {

    public  @interface Congrats {
    }

    @Before("@annotation(Congrats)")
    public final void sayHello (@NonNull JoinPoint joinPoint) {
      String name =   ((Person) joinPoint.getArgs()[0]).getName();
        System.out.printf("Hello, %s. How are you ? \n", name);
    }
    @FeedBackNeeded
    @AfterReturning(pointcut = "@annotation(FeedBackNeeded)",returning = "retVal")
    public final void askOpinion(@NonNull Object retVal) {
        System.out.printf("Is Good Enough? \n ", ((Squishee) retVal).getName());
    }



/*


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
