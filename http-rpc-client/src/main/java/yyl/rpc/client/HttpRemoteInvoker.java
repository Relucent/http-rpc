package yyl.rpc.client;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import yyl.rpc.core.RemoteInvoker;
import yyl.rpc.core.Serializes;
//InvocationHandler

public class HttpRemoteInvoker implements RemoteInvoker {
	private static final OkHttpClient client = new OkHttpClient();
	private static final MediaType TYPE = MediaType.parse("application/octet-stream; charset=utf-8");

	private volatile String url;

	public HttpRemoteInvoker(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public byte[] invoke(byte[] request) throws IOException {
		return client.newCall(//
				new Request.Builder()//
						.url(url)//
						.post(RequestBody.create(TYPE, //
								Serializes.encode(//
										request//
								)//
						)).build())//
				.execute().body().bytes();
	}
}
