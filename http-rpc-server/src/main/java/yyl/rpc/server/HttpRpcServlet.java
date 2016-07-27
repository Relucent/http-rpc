package yyl.rpc.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpRpcServlet
 */
@SuppressWarnings("serial")
public class HttpRpcServlet extends HttpServlet {
	// ==============================Fields===========================================
	private HttpRpcRequestHandler handler = new HttpRpcRequestHandler();

	// ==============================Methods==========================================
	/**
	 * POST请求处理
	 * @param request HTTP请求
	 * @param response HTTP响应
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)//
			throws ServletException, IOException {
		handler.handleRequest(request, response);
	}
}
