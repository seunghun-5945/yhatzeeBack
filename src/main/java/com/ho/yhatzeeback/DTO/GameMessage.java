package com.ho.yhatzeeback.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameMessage {
    public enum MessageType{
        ENTER, GAME
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
