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

        private String message;
        private UserAccount from;
        private UserAccount to;
        private LocalDateTime sentTime;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public UserAccount getFrom() {
            return from;
        }

        public void setFrom(UserAccount from) {
            this.from = from;
        }

        public UserAccount getTo() {
            return to;
        }

        public void setTo(UserAccount to) {
            this.to = to;
        }

        public LocalDateTime getSentTime() {
            return sentTime;
        }

        public void setSentTime(LocalDateTime sentTime) {
            this.sentTime = sentTime;
        }
}
    
