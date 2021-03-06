package com.github.cjqcn.htty.core.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * HttyResponse is used to send response back to clients.
 */
public interface HttyResponse {

	/**
	 * Sends json response back to the client. This is a convenient method to send json encoded string with
	 * content type automatically set to {@code application/json}.
	 *
	 * @param status     Status of the response.
	 * @param jsonString The json string to send back.
	 */
	void sendJson(HttpResponseStatus status, String jsonString);

	/**
	 * Send a string response in UTF-8 encoding back to the http client.
	 *
	 * @param status status of the Http response.
	 * @param data   string data to be sent back.
	 */
	void sendString(HttpResponseStatus status, String data);

	/**
	 * Send a string response in UTF-8 encoding back to the http client.
	 *
	 * @param status  status of the Http response.
	 * @param data    string data to be sent back.
	 * @param headers additional headers to send with the response.
	 */
	void sendString(HttpResponseStatus status, String data, HttpHeaders headers);

	/**
	 * Send only a status code back to client without any content.
	 *
	 * @param status status of the Http response.
	 */
	void sendStatus(HttpResponseStatus status);

	/**
	 * Send only a status code back to client without any content.
	 *
	 * @param status  status of the Http response.
	 * @param headers additional headers to send with the response.
	 */
	void sendStatus(HttpResponseStatus status, HttpHeaders headers);

	/**
	 * Send a response containing raw bytes. Default content type is "application/octet-stream", but can be
	 * overridden by the headers parameter.
	 *
	 * @param status  status of the Http response.
	 * @param bytes   bytes to be sent back.
	 * @param headers additional headers to send with the response.
	 */
	void sendByteArray(HttpResponseStatus status, byte[] bytes, HttpHeaders headers);

	/**
	 * Sends a response containing raw bytes. Default content type is "application/octet-stream", but can be
	 * overridden by the headers parameter.
	 *
	 * @param status  status of the Http response
	 * @param buffer  bytes to send
	 * @param headers additional headers to send with the response.
	 */
	void sendBytes(HttpResponseStatus status, ByteBuffer buffer, HttpHeaders headers);

	/**
	 * Respond to the client saying the response will be in chunks. The response body can be sent in chunks
	 * using the {@link ChunkResponder} returned.
	 *
	 * @param status the status code to respond with
	 */
	ChunkResponder sendChunkStart(HttpResponseStatus status);

	/**
	 * Respond to the client saying the response will be in chunks. The response body can be sent in chunks
	 * using the {@link ChunkResponder} returned.
	 *
	 * @param status  the status code to respond with
	 * @param headers additional headers to send with the response.
	 */
	ChunkResponder sendChunkStart(HttpResponseStatus status, HttpHeaders headers);

	/**
	 * Send response back to client. Default content type is "application/octet-stream", but can be
	 * overridden by the headers parameter.
	 *
	 * @param status  Status of the response.
	 * @param content Content to be sent back.
	 * @param headers additional headers to send with the response.
	 */
	void sendContent(HttpResponseStatus status, ByteBuf content, HttpHeaders headers);

	/**
	 * Sends a file content back to client with response status 200 with content type as "application/octet-stream".
	 *
	 * @param file The file to send
	 * @throws IOException if failed to open and read the file
	 */
	void sendFile(File file) throws IOException;

	/**
	 * Sends a file content back to client with response status 200. Default content type is
	 * "application/octet-stream",
	 * but can be overridden by the headers parameter.
	 *
	 * @param file    The file to send
	 * @param headers additional headers to send with the response.
	 * @throws IOException if failed to open and read the file
	 */
	void sendFile(File file, HttpHeaders headers) throws IOException;

	/**
	 * Sends response back to client. The response body is produced by the given {@link BodyProducer}. This method
	 * will return immediate after it is called. Invocation of methods on the given {@link BodyProducer} will be
	 * triggered from another thread. Default content type is "application/octet-stream", but can be
	 * overridden by the headers parameter.
	 *
	 * @param status       Status of the response.
	 * @param bodyProducer a {@link BodyProducer} to produce response body.
	 * @param headers      additional headers to send with the response.
	 */
	void sendContent(HttpResponseStatus status, BodyProducer bodyProducer, HttpHeaders headers);
}
