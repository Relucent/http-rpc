package yyl.rpc.core;

import java.io.IOException;

public interface RemoteInvoker {
	public byte[] invoke(byte[] request) throws IOException;
}
