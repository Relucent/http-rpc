package yyl.rpc.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.sf.cglib.proxy.Enhancer;
import yyl.rpc.core.BeanFactory;
import yyl.rpc.core.RemoteInvoker;

/**
 * 远程服务工厂类
 */
public class RemoteBeanFactory implements BeanFactory {

	//===================================Fields==============================================
	/** 单例对象缓存 : bean name --> bean instance */
	private final ConcurrentMap<String, Object> singletonProxys = new ConcurrentHashMap<String, Object>(64);
	private volatile RemoteInvoker remoteInvoker;

	//===================================Constructors========================================
	/**
	 * 构造函数
	 * @param url 服务URL
	 */
	public RemoteBeanFactory(String url) {
		remoteInvoker = new HttpRemoteInvoker(url);
	}

	//===================================Methods=============================================
	/**
	 * 返回远程服务对象的引用
	 * @param type 服务类型
	 * @return 远程服务的引用
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> serviceInterface) {
		String name = serviceInterface.getName();
		Object bean = null;
		while (true) {
			if ((bean = singletonProxys.get(name)) != null
					|| singletonProxys.putIfAbsent(name, bean = createProxy(serviceInterface)) == null) {
				break;
			}
		}
		return (T) bean;
	}

	/**
	 * 返回远程服务对象的代理
	 * @param serviceInterface 服务接口
	 * @return 远程服务的代理
	 */
	private Object createProxy(Class<?> serviceInterface) {
		return Enhancer.create(null, new Class[] { serviceInterface }, new ProxyInvocationHandler(remoteInvoker));
	}

}
