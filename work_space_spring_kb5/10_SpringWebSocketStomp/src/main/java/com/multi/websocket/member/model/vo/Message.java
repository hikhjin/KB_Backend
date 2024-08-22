package com.multi.websocket.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String from;
    private String text;
    private String time;
    private String menu;
}