package io.vertx;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class MyHttpServer {
	
	private Vertx _vertx;
	private HttpServer server;
	
	public MyHttpServer(Vertx vertx)
	{
		_vertx = vertx;
	}
	
	public void CreateHttpServer(int port, Future<Void> fut)
	{
		server = _vertx.createHttpServer();
		
		server.requestHandler(request->{
			HttpServerResponse response = request.response();
			response.putHeader("content-type", "text/plain");
			
			response.end("Hello World");
		}); 
		
		server.listen(port, result -> {
	          if (result.succeeded()) {
		            fut.complete();
		          } else {
		            fut.fail(result.cause());
		          }
        });
	}
	
	public void StopHttpServer()
	{
		server.close();
	}
}
