package io.vertx;

import io.vertx.core.Vertx;

public class VerticleRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Vertx vertx = Vertx.vertx();

		
        vertx.deployVerticle(MyVerticle.class.getName());
	}

}
