package br.com.afrcode.arquitetura.is.util.junit.logging;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

public class EJB3LoggingInterceptor {
    private static final Logger LOG = Logger.getLogger(EJB3LoggingInterceptor.class);

    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object monitorar(InvocationContext ic) throws Exception {
        StopWatch stopWatch = new StopWatch();
        Object retVal = null;
        boolean okay = true;
        String signature = sessionContext.getCallerPrincipal().getName() + " : " + ic.toString();
        try {
            stopWatch.start();
            retVal = ic.proceed();
            return retVal;
        } catch (Exception e) {
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
