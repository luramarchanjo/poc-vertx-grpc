package com.example.grpc;

import com.example.grpc.InvoiceServiceGrpc.InvoiceServiceVertxImplBase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.grpc.VertxServerBuilder;
import java.util.UUID;

public class MainVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(new VertxOptions().setPreferNativeTransport(true).setWorkerPoolSize(200));
    vertx.deployVerticle(MainVerticle.class.getName());
    vertx.deployVerticle(Worker.class.getName(), new DeploymentOptions().setWorker(true).setInstances(200));
  }

  @Override
  public void start() throws Exception {
    InvoiceServiceVertxImplBase invoiceService = new InvoiceServiceGrpc.InvoiceServiceVertxImplBase() {
      @Override
      public void invoice(InvoiceRequest request, Future<InvoiceResponse> response) {
        vertx.eventBus().send(Worker.EVENT, new JsonObject(), event -> {
          response.tryComplete(InvoiceResponse.newBuilder()
            .setResponse(UUID.randomUUID().toString())
            .build());
        });
      }
    };

    VertxServerBuilder
      .forAddress(vertx, "localhost", 8080)
      .addService(invoiceService)
      .build()
      .start(event -> System.out.println("gPRC server started"));
  }

}
