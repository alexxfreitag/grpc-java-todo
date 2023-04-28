package com.devfreitag;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TodoClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        TodoServiceGrpc.TodoServiceBlockingStub stub =
                TodoServiceGrpc.newBlockingStub(channel);


        Todo todoResponse = stub.createTodo(CreateTodoRequest.newBuilder()
                .setDescription("Test 1")
                .build());

        System.out.println("Response received from server:\n" + todoResponse);

        channel.shutdown();
    }
}
