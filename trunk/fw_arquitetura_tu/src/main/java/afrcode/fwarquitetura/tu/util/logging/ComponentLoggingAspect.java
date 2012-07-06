package afrcode.fwarquitetura.tu.util.logging;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Aspectos para logging de testes unitários que envolvam componentes Spring,
 * incluindo instrumentação de código para análises de desempenho.
 * 
 * @author alyssonfr
 * 
 */
@Component
@Aspect
public class ComponentLoggingAspect {
    private static final Logger LOG = Logger.getLogger(ComponentLoggingAspect.class);

    @Autowired
    private StopWatch stopWatch;

    @Around("bean(dao*)")
    public Object monitorarDAOs(ProceedingJoinPoint pjp) throws Throwable {
        return monitorar(pjp);
    }

    private Object monitorar(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        boolean okay = true;
        String signature = pjp.getStaticPart().getSignature().toShortString();
        try {
            stopWatch.start();
            retVal = pjp.proceed();
            return retVal;
        } catch (Throwable e) {
            okay = false;
            throw e;
        } finally {
            stopWatch.stop();
            String sufixo = !okay ? " falhou: " : ": ";
            LOG.info(signature + sufixo + stopWatch.toString());
            stopWatch.reset();
        }
    }

}
