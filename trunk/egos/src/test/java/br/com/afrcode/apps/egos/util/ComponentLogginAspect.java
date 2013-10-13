package br.com.afrcode.apps.egos.util;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ComponentLogginAspect {
	private static final Logger LOG = Logger
			.getLogger(ComponentLogginAspect.class);

	@Around("bean(dao*)")
	public Object monitorarDAOs(ProceedingJoinPoint pjp) throws Throwable {
		return monitorar(pjp);
	}

	private Object monitorar(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		boolean okay = true;
		String signature = pjp.getStaticPart().
				getSignature().toShortString();
		StopWatch stopWatch = new StopWatch();
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
