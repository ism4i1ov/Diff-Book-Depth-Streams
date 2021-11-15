package com.example.diffbookdepthstreams.websocket;

import com.example.diffbookdepthstreams.dto.StreamCommonResponseDto;
import com.example.diffbookdepthstreams.service.business.DepthBusinessService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class DiffSocketHandler extends TextWebSocketHandler implements WebSocketHandler {

    private final DepthBusinessService depthBusinessService;

    public DiffSocketHandler(DepthBusinessService depthBusinessService) {
        this.depthBusinessService = depthBusinessService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        StreamCommonResponseDto streamCommonResponseDto =
                new Gson().fromJson(message.getPayload(), StreamCommonResponseDto.class);
        depthBusinessService.validateAndSave(streamCommonResponseDto.getData());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        super.afterConnectionClosed(session, status);
    }
}
