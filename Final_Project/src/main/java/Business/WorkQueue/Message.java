/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.UserAccount.UserAccount;
import java.time.LocalDateTime;

/**
 *
 * @author zhangchuanqi
 */
public class Message {

    private int id;
    private String message;
    private String fromUsername;
    private String toUsername;
    private LocalDateTime sentTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String from) {
        this.fromUsername = from;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String to) {
        this.toUsername = to;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
}
