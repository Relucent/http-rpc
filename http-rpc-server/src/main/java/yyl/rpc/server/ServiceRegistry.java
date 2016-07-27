package yyl.rpc.server;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import yyl.rpc.core.Definition;

/**
 * 服务注册<br>
 */
public class ServiceRegistry {
	//===================================Fields==============================================
	private static final ConcurrentMap<Definition, Object> beanInstances = new ConcurrentHashMap<Definition, Object>();
	private static final ConcurrentMap<String, Definition> definitions = new ConcurrentHashMap<String, Definition>();

	//===================================Methods=============================================
	/**
	 * 绑定服务
	 * @param serviceInterface 服务的接口
	 * @param serviceImpl 服务的实现类
	 */

	public static <T> void register(Class<T> serviceInterface, T serviceImpl) {
		if (serviceInterface.isInterface()) {
			throw new IllegalArgumentException(" serviceInterface nterface must be interface");
		}
		for (Method method : serviceInterface.getDeclaredMethods()) {
			Definition definition = new Definition(method);
			beanInstances.put(definition, serviceImpl);
		}
	}

	/**
	 * 获得服务
	 * @param definition 服务定义
	 * @return 服务的实现类
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Definition definition) {
		return (T) beanInstances.get(definition);
	}

	/**
	 * 获得服务定义
	 * @param qualifiedName 服务名
	 * @return 服务定义
	 */
	public static Definition getDefinition(String qualifiedName) {
		return definitions.get(qualifiedName);
	}
}
