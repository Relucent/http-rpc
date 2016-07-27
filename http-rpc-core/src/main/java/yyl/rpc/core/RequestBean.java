package yyl.rpc.core;

import java.io.Serializable;

/**
 * 请求实体
 */
@SuppressWarnings("serial")
public class RequestBean implements Serializable {

	private String qualifiedName;
	private Object[] parameters;

	public RequestBean() {
	}

	public RequestBean(String qualifiedName, Object[] parameters) {
		this.qualifiedName = qualifiedName;
		this.parameters = parameters;
	}

	public String getQualifiedName() {
		return qualifiedName;
	}

	public Object[] getParameters() {
		return parameters;
	}

}
