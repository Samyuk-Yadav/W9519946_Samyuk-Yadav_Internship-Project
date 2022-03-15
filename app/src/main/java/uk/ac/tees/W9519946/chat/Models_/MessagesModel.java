package uk.ac.tees.W9519946.chat.Models_;

public class MessagesModel {

    Long timeStamp;
    String userID, message, idMessage;

    public MessagesModel(Long timeStamp, String userID, String message) {
        this.timeStamp = timeStamp;
        this.userID = userID;
        this.message = message;
    }

    public MessagesModel(String userID, String message) {
        this.userID = userID;
        this.message = message;
    }
    public MessagesModel(){}


    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

