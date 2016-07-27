package yyl.rpc.core;

import java.lang.reflect.Method;

public class Definition {
	//===================================Fields==============================================
	public final Method method;
	public final String qualifiedName;

	//===================================Constructors========================================
	/**
	 * 构造函数
	 * @param url 服务的URL
	 * @param type 服务的接口类型
	 */
	public Definition(Method method) {
		this.method = method;
		this.qualifiedName = getQualifiedName(method);
	}

	//===================================Methods=============================================
	/**
	 * 获得方法
	 * @return 方法
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * 获得方法的限定名
	 * @return 方法的限定名
	 */
	public String getQualifiedName() {
		return qualifiedName;
	}

	/**
	 * 获得对象HASH码
	 * @return 对象HASH码
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		return result;
	}

	/**
	 * 比较两个对象是否相等
	 * @return 两个对象相等返回true,否则返回false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Definition other = (Definition) obj;
		if ((method == null ? other.method != null : !method.equals(other.method))) {
			return false;
		}
		return true;
	}

	//===================================StaticMethods=======================================
	/**
	 * 获得方法的限定名(可以定位方法的名字)
	 * @param method 方法
	 * @return 方法的限定名
	 */
	public static String getQualifiedName(Method method) {
		StringBuilder builder = new StringBuilder();
		builder.append(method.getDeclaringClass().getName());
		builder.append("#");
		builder.append(method.getName());
		builder.append("(");
		boolean first = true;
		for (Class<?> type : method.getParameterTypes()) {
			if (first) {
				first = false;
			} else {
				builder.append(",");
			}
			builder.append(type.getName());
		}
		builder.append(")");
		return builder.toString();
	}
}
