package org.javamaster.b2c.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author yudong
 */
@Component
@ServerEndpoint(value = "/macro")
public class SocketHandler extends AbstractWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(getClass());
    int i;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("connect:{}", session.getId());
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    session.getBasicRemote().sendText("important java message " + Thread.currentThread().getName()
                            + " " + Math.random());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @OnClose
    public void onClose() {
        logger.info("close");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        logger.info("receive {} from js,session id:{}", message, session.getId());
        session.getBasicRemote().sendText("important java message " + i++);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("receive {} from js,session id:{}", error.getClass().getSimpleName(), session.getId());
    }
}
