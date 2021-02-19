package org.javamaster.javalin;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2020/6/22
 */
public class HelloWorld {

    public static void main(String[] args) {
        // Javalin app = Javalin.create().start(7000);
        // app.get("/", ctx -> ctx.result("Hello World"));

        Javalin app = Javalin.create(config -> {
                    config.defaultContentType = "application/json";
                    config.autogenerateEtags = true;
                    config.addStaticFiles("/public");
                    config.asyncRequestTimeout = 10_000L;
                    config.enforceSsl = false;
                }
        ).routes(() -> {

        }).start(7000);

        app.before(ctx -> {
            // runs before all requests
        });
        app.before("/path/*", ctx -> {
            // runs before request to /path/*
        });

        app.after(ctx -> {
            // run after all requests
        });
        app.after("/path/*", ctx -> {
            // runs after request to /path/*
        });

        app.get("/", ctx -> ctx.result("Hello World"));

        app.post("/", ctx -> {
            String bodyString = ctx.body();
            System.out.println(bodyString);
            ctx.result(bodyString);
        });

        app.wsBefore(ws -> {
            // runs before all WebSocket requests
        });
        app.wsBefore("/path/*", ws -> {
            // runs before websocket requests to /path/*
        });

        app.wsAfter(ws -> {
            // runs after all WebSocket requests
        });
        app.wsAfter("/path/*", ws -> {
            // runs after websocket requests to /path/*
        });

        app.ws("/websocket/:path", ws -> {
            ws.onConnect(ctx -> System.out.println("Connected"));
            ws.onMessage(ctx -> {
                Map<String, String> map = new HashMap<>();
                map.put("name", "liangyudong");
                ctx.send(map); // convert to json and send back
            });
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> System.out.println("Closed"));
            ws.onError(ctx -> System.out.println("Errored"));
        });

    }

}
