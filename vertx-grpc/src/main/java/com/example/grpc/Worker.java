package com.example.grpc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Worker extends AbstractVerticle {

  public static String EVENT = "event-bus";

  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer(EVENT, event -> {
      try {
        System.out.println(String
          .format("[%s] [%s] Received request", Thread.currentThread().getName(),
            LocalDateTime.now()));
        TimeUnit.MILLISECONDS.sleep(100);
        event.reply(new JsonObject());
      } catch (Exception e) {

      }
    });
  }

}
