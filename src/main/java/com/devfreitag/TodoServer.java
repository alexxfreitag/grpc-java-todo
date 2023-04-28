package com.devfreitag;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class TodoServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = ServerBuilder.forPort(port)
                .addService(new TodoServiceImpl()).build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!" + server.getPort());
        server.awaitTermination();
    }

}
