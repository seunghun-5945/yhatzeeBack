package com.ho.yhatzeeback.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ho.yhatzeeback.GameRoom.GameRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;


//이건 DB 접근을 하지않는 Service    로직

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {
    private final ObjectMapper objectMapper;
    private Map<String, GameRoom> gameRooms;

    @PostConstruct
    private void init() {
        gameRooms = new LinkedHashMap<>();
    }

    public List<GameRoom> findAllRoom() {
        return new ArrayList<>(gameRooms.values());
    }

    public GameRoom findRoomById(String roomId) {
        return gameRooms.get(roomId);
    }

    public GameRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        GameRoom chatRoom = GameRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        gameRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
