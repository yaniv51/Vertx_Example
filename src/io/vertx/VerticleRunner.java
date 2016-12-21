package io.vertx;

import java.util.Scanner;

import io.vertx.core.Vertx;

public class VerticleRunner {

	public static void main(String[] args) {
		int quitNumber;
		Scanner scanner;
		scanner = new Scanner(System.in);
		Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(VertxVerticle.class.getName());
        
        do
        {
        	System.out.println("Type 1 for exit");
        	try
        	{
        		quitNumber = scanner.nextInt();
        	}
        	catch(Exception e)
        	{
        		quitNumber = 0;
        		scanner.nextLine();
        		System.out.println("Are you sure it is a number?");
        	}
        }while(quitNumber != 1);
        
        vertx.close();
        vertx.undeploy(VertxVerticle.class.getName());
        scanner.close();
        System.exit(0);
	}
}
