/**
 * 
 */
package cursojsf.test.modelo.entidade.iniciador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author alysson
 *
 */
public class IniciadorBD {
	private static final String PERSISTENCE_UNIT_NAME = "persistenceUnit";

	private static final Logger LOG = Logger.getLogger(IniciadorBD.class);
	
	private static final String HIBERNATE_CFG_PROPS = 
		"hibernate-jpaPropertyMap.properties";
	
	private static void iniciar() throws IOException {
		InputStream is = IniciadorBD.class.getClassLoader().
		  getResourceAsStream(HIBERNATE_CFG_PROPS);
		if (is != null) {
			Ejb3Configuration ejbcfg = new Ejb3Configuration();
			Properties props = new Properties();
			props.putAll(ejbcfg.getProperties());
			props.load(is);
			ejbcfg.setProperties(props);
			ejbcfg = ejbcfg.configure(PERSISTENCE_UNIT_NAME, Collections.EMPTY_MAP);

			AnnotationConfiguration acfg = ejbcfg.getHibernateConfiguration();
			SchemaExport su = new SchemaExport(acfg);
			su.setFormat(true);
			su.setHaltOnError(true);
			su.setOutputFile("jsfdemoschema.sql");
			su.create(true, true);
		}
	}
	
	public static void main(String[] args) {
		boolean ocorreramErros = false;
		try {
			iniciar();
		} catch (IOException e) {
			ocorreramErros = true;
			LOG.error("Erro ao iniciar o banco de dados!", e);
		} finally {
			System.exit(ocorreramErros ? 1 : 0);
		}
	}

}
