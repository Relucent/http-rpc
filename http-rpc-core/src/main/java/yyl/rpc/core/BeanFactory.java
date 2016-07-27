package yyl.rpc.core;

public interface BeanFactory {
	/**
	 * 返回远程服务对象的引用
	 * @param type 服务类型
	 * @return 远程服务的引用
	 */
	public abstract <T> T getBean(Class<T> serviceInterface);
}
