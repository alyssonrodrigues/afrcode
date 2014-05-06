package br.com.afrcode.arquitetura.is.spring.config.rmi;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Configurações Spring para exposição de serviços via RMI.
 * 
 * 
 */
public abstract class AbstractRMIServiceExporterBeansConfig<T> {

	private Class<T> classeProxy;

	public AbstractRMIServiceExporterBeansConfig() {
		iniciar();
	}

	/**
	 * Método de obtenção do tipo associado ao proxy.
	 */
	private void iniciar() {
		Class<?> clazz = this.getClass();
		Type superClazz = clazz.getGenericSuperclass();
		// Em geral uma classe de configuração de proxy contém apenas um
		// supertipo genérico, porém mais de um supertipo genérico
		// pode surgir na presença de aspectos associados a esta classe.
		while (!ParameterizedType.class.isAssignableFrom(superClazz.getClass())) {
			clazz = clazz.getSuperclass();
			superClazz = clazz.getGenericSuperclass();
		}
		ParameterizedType tipoParametrizado = (ParameterizedType) superClazz;
		Type[] params = tipoParametrizado.getActualTypeArguments();
		classeProxy = (Class<T>) params[0];
	}

	/**
	 * Configurações para expor serviço via RMI.
	 * 
	 * If you have ever created an RMI service WITHOUT Spring, you know that it
	 * involves the following steps: 1) Write the service implementation class
	 * with methods that throw java.rmi.RemoteException. 2) Create the service
	 * interface to extend java.rmi.Remote. 3) Run the RMI compiler (rmic) to
	 * produce client stub and server skeleton classes. 4) Start an RMI registry
	 * to host the services. 5) Register the service in the RMI registry.
	 * 
	 * O RmiServiceExporter, Spring, é responsável por: 1) iniciar um RMI
	 * registry, se ainda não iniciado; 2) registrar serviços via RMI registry
	 * na máquina local, porta 1099; 3) expor o beans geridos (POJOs)
	 * encapsulando-os em um adaptador (RMI Service Adapter).
	 * 
	 * Este adaptador por sua fez: 1) é registrado, pelo Spring, no RMI
	 * registry; 2) responderá por chamadas aos serviços expostos pelos beans
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
