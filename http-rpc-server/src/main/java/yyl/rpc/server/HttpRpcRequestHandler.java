package yyl.rpc.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRpcRequestHandler {

	private InvokeHandler handler = new InvokeHandler();

	/**
	 * 调用
	 * @param request HTTP请求
	 * @param response HTTP响应
	 * @throws IoException
	 */
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		write(handler.invoke(read(request)), response);
	}

	/**
	 * 读取数据
	 * @param request HTTP请求
	 * @param 请求数据
	 */
	private static byte[] read(HttpServletRequest request) throws IOException {
		InputStream input = request.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int n;
		byte[] buffer = new byte[4096];
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}

	/**
	 * 返回数据
	 * @param data 返回的数据
	 * @param response HTTP响应
	 */
	private static void write(byte[] data, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getOutputStream().write(data);
	}

}
