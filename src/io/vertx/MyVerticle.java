package io.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle{
	
	private MyHttpServer server = null;
	
	@Override
	public void start(Future<Void> fut)
	{
		System.out.println("MyVerticle started!");
		
		server = new MyHttpServer(vertx);
		server.CreateHttpServer(8080, fut);
	}
	
	
	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("MyVerticle stopped!");
        if(server!= null)
        	server.StopHttpServer();
    }
}
