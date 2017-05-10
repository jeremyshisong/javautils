package com.chan.webservice;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

/**
 * Created by shisong on 16/11/13.
 */
public class WebSocketServer extends BaseWebSocketHandler {
    private int connectionCount;

    public void onOpen(WebSocketConnection connection) {
        connection.send("Hello! There are " + connectionCount + " other connections active");
        connectionCount++;
    }

    public void onClose(WebSocketConnection connection) {
        connectionCount--;
    }

    public void onMessage(WebSocketConnection connection, String message) {
        connection.send(message.toUpperCase()); // echo back message in upper case
    }
}
