package com.ho.yhatzeeback.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ho.yhatzeeback.DTO.GameMessage;
import com.ho.yhatzeeback.GameRoom.GameRoom;
import com.ho.yhatzeeback.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final GameService gameService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        GameMessage chatMessage = objectMapper.readValue(payload, GameMessage.class);

        GameRoom gameRoom = gameService.findRoomById(chatMessage.getRoomId());
        gameRoom.handlerActions(session, chatMessage, gameService);
    }

}
