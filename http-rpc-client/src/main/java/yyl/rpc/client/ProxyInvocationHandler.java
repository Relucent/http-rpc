package yyl.rpc.client;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import yyl.rpc.core.Definition;
import yyl.rpc.core.RemoteInvoker;
import yyl.rpc.core.RequestBean;
import yyl.rpc.core.Serializes;

/**
 * 代理执执行器
 */
public class ProxyInvocationHandler implements MethodInterceptor {

	private RemoteInvoker remoteinvoker;

	public ProxyInvocationHandler(RemoteInvoker remoteinvoker) {
		this.remoteinvoker = remoteinvoker;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] parameters, MethodProxy methodProxy) throws Throwable {
		return Object.class.equals(method.getDeclaringClass()) //
				? methodProxy.invokeSuper(obj, parameters)
				: Serializes.decode(//
						remoteinvoker.invoke(//
								Serializes.encode(//
										new RequestBean(//
												Definition.getQualifiedName(method), //
												parameters//
										)//
								)//
						), method.getReturnType());
	}
}
