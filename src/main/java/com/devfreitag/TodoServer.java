package com.devfreitag;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class TodoServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        Server server = ServerBuilder.forPort(port)
                .addService(new TodoServiceImpl()).build();

        server.start();
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination();
    }

}
