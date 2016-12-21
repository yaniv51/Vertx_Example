package io.vertx;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

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
		

		Router router = Router.router(_vertx);
		
		router.route().handler(routingContext -> {

			  // This handler will be called for every request
			  HttpServerResponse response = routingContext.response();
			  response.putHeader("content-type", "text/plain");

			  // Write to the response and end it
			  response.end("Vert.x-Web Routing!");
			});
		
		server.requestHandler(router::accept);
		
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
