package com.devfreitag;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.UUID;

public class TodoServiceImpl extends TodoServiceGrpc.TodoServiceImplBase {

    @Override
    public void createTodo(CreateTodoRequest createTodoRequest, StreamObserver<Todo> todoStreamObserver) {
        Todo todo = Todo.newBuilder()
                .setId(String.valueOf(UUID.randomUUID()))
                .setDescription(createTodoRequest.getDescription())
                .setDone(false)
                .build();

        todoStreamObserver.onNext(todo);
        todoStreamObserver.onCompleted();
    }

    @Override
    public StreamObserver<CreateTodoRequest> createTodoClientStreaming(StreamObserver<TodoList> responseObserver) {
        ArrayList<Todo> todoList = new ArrayList<>();

        return new StreamObserver<CreateTodoRequest>() {
            @Override
            public void onNext(CreateTodoRequest createTodoRequest) {
                Todo todo = Todo.newBuilder()
                        .setId(String.valueOf(UUID.randomUUID()))
                        .setDescription(createTodoRequest.getDescription())
                        .setDone(false)
                        .build();
                todoList.add(todo);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(TodoList.newBuilder().addAllTodos(todoList).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<CreateTodoRequest> createTodoBidirectionalStreaming(StreamObserver<Todo> responseObserver) {
        return new StreamObserver<CreateTodoRequest>() {
            @Override
            public void onNext(CreateTodoRequest createTodoRequest) {

                Todo todo = Todo.newBuilder()
                        .setId(String.valueOf(UUID.randomUUID()))
                        .setDescription(createTodoRequest.getDescription())
                        .setDone(false)
                        .build();
                responseObserver.onNext(todo);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }

        };
    }
}
