package io.vertx;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class MyHttpServer {
	
	private Vertx _vertx;
	private HttpServer _server;
	private Router _router;
	
	public MyHttpServer(Vertx vertx)
	{
		_vertx = vertx;
		_router = Router.router(_vertx);
	}
	
	public void CreateHttpServer(int port, Future<Void> fut)
	{
		_server = _vertx.createHttpServer();
		
		//create route for /healthcheck
		_router.route().path("/healthcheck/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			  response.putHeader("content-type", "text/plain");
			  
			  response.end("I'm alive!!!");
		});
		
		//create route for /hello
		_router.route().path("/hello/").handler(routingContext -> {
			  // This handler will be called for every request
			  HttpServerResponse response = routingContext.response();
			  response.putHeader("content-type", "text/plain");
			  
			  String userName = routingContext.request().getParam("name");
			  
			  String srtResponse = String.format("Hello %s!", userName != null? userName : "");

			  // Write to the response and end it
			  response.end(srtResponse);
		});
		
		//end error for any other routes
		_router.route().handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			response.setStatusCode(404);
			response.end("404 Not Found");
		});
		
		_server.requestHandler(_router::accept);
		
		_server.listen(port, result -> {
	          if (result.succeeded()) {
		            fut.complete();
		          } else {
		            fut.fail(result.cause());
		          }
        });
	}
	
	public void StopHttpServer()
	{
		_server.close();
	}
}
