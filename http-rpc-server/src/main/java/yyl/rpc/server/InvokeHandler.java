package yyl.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import yyl.rpc.core.Definition;
import yyl.rpc.core.RequestBean;
import yyl.rpc.core.Serializes;

public class InvokeHandler {
	/**
	 * 调用
	 * @param request HTTP请求
	 * @param response HTTP响应
	 */
	public byte[] invoke(byte[] data) {
		RequestBean request = Serializes.decode(data, RequestBean.class);

		String qualifiedName = request.getQualifiedName();

		Definition definition = ServiceRegistry.getDefinition(qualifiedName);

		if (definition == null) {
			throw new RuntimeException("service :[" + qualifiedName + "] not found!");
		}

		Object bean = ServiceRegistry.getBean(definition);

		Method method = definition.getMethod();

		Object[] parameter = request.getParameters();
		Class<?>[] parameterTypes = method.getParameterTypes();

		if (parameterTypes.length != parameter.length) {
			throw new RuntimeException("service :[" + qualifiedName + "] parameter is not match.");
		}

		Object result = null;
		try {
			result = invoke(bean, method, parameter);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return Serializes.encode(result);
	};

	private static Object invoke(Object bean, Method method, Object[] parameters)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length != parameters.length) {
			throw new IllegalArgumentException("parameter is not match.");
		}
		Object[] parameter = new Object[parameters.length];
		return method.invoke(bean, parameter);
	}
}
