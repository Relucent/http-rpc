package yyl.rpc.client;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import yyl.rpc.core.BeanFactory;

public class Remotes {

	//===================================Fields==============================================
	private static final ConcurrentMap<String, BeanFactory> urlFactoryMap = new ConcurrentHashMap<String, BeanFactory>();

	//===================================Methods=============================================
	/**
	 * 将URL与远程服务关联，并返回的远程服务对象的引用
	 * @param url 服务URL
	 * @return 远程服务工厂
	 */
	public static BeanFactory getBeanFactory(String url) {
		BeanFactory factory = null;
		while (true) {
			if ((factory = urlFactoryMap.get(url)) != null
					|| urlFactoryMap.putIfAbsent(url, factory = new RemoteBeanFactory(url)) == null) {
				break;
			}
		}
		return factory;
	}
}
