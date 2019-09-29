package com.example.grpc;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Application extends InvoiceServiceGrpc.InvoiceServiceImplBase {

  @Override
  public void invoice(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {
    try {
      System.out.println(String.format("[%s] [%s] Received request", Thread.currentThread().getName(), LocalDateTime.now()));
      TimeUnit.MILLISECONDS.sleep(100);
      responseObserver.onNext(InvoiceResponse.newBuilder()
          .setResponse(UUID.randomUUID().toString())
          .build());
    } catch (Exception e) {
      // Do nothing
    } finally {
      responseObserver.onCompleted();
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    ServerBuilder.forPort(8081)
        .addService(new Application())
        .build()
        .start()
        .awaitTermination();
  }

}