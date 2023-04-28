package com.devfreitag;

import io.grpc.stub.StreamObserver;

public class TodoServiceImpl extends TodoServiceGrpc.TodoServiceImplBase {

    @Override
    public void createTodo(CreateTodoRequest createTodoRequest, StreamObserver<Todo> todoStreamObserver) {
        Todo todo = Todo.newBuilder()
                .setId("1")
                .setDescription(createTodoRequest.getDescription())
                .setDone(false)
                .build();

        todoStreamObserver.onNext(todo);
        todoStreamObserver.onCompleted();
    }
}
