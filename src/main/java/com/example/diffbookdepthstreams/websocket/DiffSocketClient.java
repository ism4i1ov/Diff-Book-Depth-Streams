package com.example.diffbookdepthstreams.websocket;

import com.example.diffbookdepthstreams.config.ApplicationConfiguration;
import com.example.diffbookdepthstreams.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class DiffSocketClient {

    private final DiffSocketHandler diffSocketHandler;

    public DiffSocketClient(DiffSocketHandler diffSocketHandler) {
        this.diffSocketHandler = diffSocketHandler;
    }

    public void connect(String ms) throws ExecutionException, InterruptedException {
        StandardWebSocketClient socketClient = new StandardWebSocketClient();
        ListenableFuture<WebSocketSession> webSocketSessionListenableFuture =
                socketClient.doHandshake(diffSocketHandler,
                        CommonUtil.formatUrl(ApplicationConfiguration.getBinanceSocketUrl(), ms));
        WebSocketSession webSocketSession = webSocketSessionListenableFuture.get();
        webSocketSession.setTextMessageSizeLimit(1024 * 1024);
        webSocketSession.setBinaryMessageSizeLimit(1024 * 1024);
    }
}
