package com.e.moonchat;

public class MyData {

    private String roomID;
    private String roomName;
    private String lastMsg;
    private String lastTime;

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getNotReadMsg() {
        return notReadMsg;
    }

    public void setNotReadMsg(int notReadMsg) {
        this.notReadMsg = notReadMsg;
    }

    private int notReadMsg;

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
