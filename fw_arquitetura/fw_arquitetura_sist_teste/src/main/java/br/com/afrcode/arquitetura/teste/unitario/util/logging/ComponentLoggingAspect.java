package br.com.afrcode.arquitetura.teste.unitario.util.logging;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspectos para logging de testes unitários que envolvam componentes Spring,
 * incluindo instrumentação de código para análises de desempenho.
 * 
 * 
 */
@Component
@Aspect
public class ComponentLoggingAspect {
    private static final Logger LOG = Logger.getLogger(ComponentLoggingAspect.class);

    @Around("bean(dao*)")
    public Object monitorarDAOs(ProceedingJoinPoint pjp) throws Throwable {
        return monitorar(pjp);
    }

    private Object monitorar(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        boolean okay = true;
        String signature = pjp.getStaticPart().getSignature().toShortString();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            retVal = pjp.proceed();
            return retVal;
        } catch (Exception e) {
            okay = false;
            throw e;
        } finally {
            stopWatch.stop();
            if (LOG.isDebugEnabled()) {
                String sufixo = !okay ? " falhou: " : ": ";
                LOG.debug(signature + sufixo + stopWatch.toString());
            }
            stopWatch.reset();
        }
    }

}
