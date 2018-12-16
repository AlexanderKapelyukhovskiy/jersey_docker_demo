package com.alkapa.tradeshift.jersey.docker.demo;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/api/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.alkapa.tradeshift.jersey.docker.demo package
        final ResourceConfig rc = new ResourceConfig()
            .packages("com.alkapa.tradeshift.jersey.docker.demo");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory
            .createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(
            String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        DbStorage dbStorage = new DbStorage();
        try{
            dbStorage.testDatabase(
                "jdbc:mysql://mysql:3306/AmazingCo?user=root&password=qwerty");
                System.out.println("DbTest passed");
        }
        catch(Exception e){
            System.out.println("DbTest failed");
        }

        System.in.read();
        server.shutdownNow();
    }
}