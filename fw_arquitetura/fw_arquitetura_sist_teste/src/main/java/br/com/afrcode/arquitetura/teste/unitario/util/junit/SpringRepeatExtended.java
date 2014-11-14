package br.com.afrcode.arquitetura.teste.unitario.util.junit;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.statements.SpringRepeat;

import br.com.afrcode.arquitetura.teste.unitario.util.time.NanoStopWatch;

/**
 * Estensão do SpringRepeat Statement para inclusão de funcionalidades para
 * registro de tempos de execução para posteriores análises de desempenho.
 * 
 * 
 */
public class SpringRepeatExtended extends SpringRepeat {
	private static final Logger LOG = Logger
			.getLogger(SpringRepeatExtended.class);

	private Statement next;
	private Method testMethod;
	private int repeat;

	private NanoStopWatch stopWatch;

	public SpringRepeatExtended(Statement next, Method testMethod, int repeat) {
		super(next, testMethod, repeat);
		this.next = next;
		this.testMethod = testMethod;
		this.repeat = repeat;
		String stopWatchId = testMethod.getDeclaringClass().getName() + "."
				+ testMethod.getName();
		this.stopWatch = new NanoStopWatch(stopWatchId);
	}

	@Override
	public void evaluate() throws Throwable {
		for (int i = 0; i < repeat; i++) {
			String taskName = testMethod.getName() + "[" + (i + 1) + "]";
			stopWatch.start(taskName);
			next.evaluate();
			stopWatch.stop();
		}
		LOG.info(stopWatch.prettyPrint());
	}

}
