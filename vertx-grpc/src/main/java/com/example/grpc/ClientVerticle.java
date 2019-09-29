package com.example.grpc;

import com.example.grpc.InvoiceServiceGrpc.InvoiceServiceVertxStub;
import io.grpc.ManagedChannel;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.grpc.VertxChannelBuilder;

public class ClientVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx.vertx(new VertxOptions().setPreferNativeTransport(true))
      .deployVerticle(ClientVerticle.class.getName());
  }

  @Override
  public void start() throws Exception {
    ManagedChannel channel = VertxChannelBuilder
      .forAddress(vertx, "localhost", 8080)
      .usePlaintext(true)
      .build();

    InvoiceServiceVertxStub stub = InvoiceServiceGrpc.newVertxStub(channel);
    stub.invoice(InvoiceRequest.getDefaultInstance(), event -> {
      System.out.println(event);
      System.exit(0);
    });
  }

}
