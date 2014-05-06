package br.com.afrcode.arquitetura.is.spring.config.rmi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Configura��es Spring para exposi��o de servi�os via RMI.
 * 
 * 
 */
public abstract class AbstractRMIServiceExporterBeansConfig<T> {

	private Class<T> classeProxy;

	public AbstractRMIServiceExporterBeansConfig() {
		iniciar();
	}

	/**
	 * M�todo de obten��o do tipo associado ao proxy.
	 */
	private void iniciar() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		// Em geral uma classe de configura��o de proxy cont�m apenas um
		// supertipo gen�rico, por�m mais de um supertipo gen�rico
		// pode surgir na presen�a de aspectos associados a esta classe.
		while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
			clazz = clazz.getSuperclass();
			superClazz = clazz.getGenericSuperclass();
		}
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeProxy = (Class<T>) params[0];
	}

	/**
	 * Configura��es para expor servi�o via RMI.
	 * 
	 * If you have ever created an RMI service WITHOUT Spring, you know that it
	 * involves the following steps: 1) Write the service implementation class
	 * with methods that throw java.rmi.RemoteException. 2) Create the service
	 * interface to extend java.rmi.Remote. 3) Run the RMI compiler (rmic) to
	 * produce client stub and server skeleton classes. 4) Start an RMI registry
	 * to host the services. 5) Register the service in the RMI registry.
	 * 
	 * O RmiServiceExporter, Spring, � respons�vel por: 1) iniciar um RMI
	 * registry, se ainda n�o iniciado; 2) registrar servi�os via RMI registry
	 * na m�quina local, porta 1099; 3) expor o beans geridos (POJOs)
	 * encapsulando-os em um adaptador (RMI Service Adapter).
	 * 
	 * Este adaptador por sua fez: 1) � registrado, pelo Spring, no RMI
	 * registry; 2) responder� por chamadas aos servi�os expostos pelos beans
	 * geridos.
	 * 
	 * @return
	 */
	protected RmiServiceExporter createRmiServiceExporter(String serviceName,
			Object serviceInstance) {
		RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		rmiServiceExporter.setServiceName(serviceName);
		rmiServiceExporter.setService(serviceInstance);
		rmiServiceExporter.setServiceInterface(classeProxy);
		return rmiServiceExporter;
	}
}
