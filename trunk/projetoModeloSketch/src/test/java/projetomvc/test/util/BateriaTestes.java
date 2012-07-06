package projetomvc.test.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import projetomvc.test.spring.TesteSpringTUContext;
import projetomvc.test.util.jms.TesteJMS;

@RunWith(Suite.class)
@SuiteClasses({TesteSpringTUContext.class, TesteJMS.class})
public class BateriaTestes {

}
