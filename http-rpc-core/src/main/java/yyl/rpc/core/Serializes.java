package yyl.rpc.core;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class Serializes {

	public static <T> byte[] encode(T object) {
		@SuppressWarnings("unchecked")
		Schema<T> schema = RuntimeSchema.getSchema((Class<T>) object.getClass());
		return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(512));
	}

	public static <T> T decode(byte[] data, Class<T> clazz) {
		Schema<T> schema = RuntimeSchema.getSchema(clazz);
		T message = (T) schema.newMessage();
		ProtobufIOUtil.mergeFrom(data, message, schema);
		return message;
	}
}
