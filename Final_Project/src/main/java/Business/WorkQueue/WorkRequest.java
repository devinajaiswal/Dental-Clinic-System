/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import java.time.LocalDateTime;

/**
 *
 * @author raunak
 */
public abstract class WorkRequest {

    private int requestId;
    private String message;
    private String senderUsername;
    private String receiverUsername;
    private int receiverOrganizationId;
    private LocalDateTime requestTime;
    private LocalDateTime finishTime;

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public int getReceiverOrganizationId() {
        return receiverOrganizationId;
    }

    public void setReceiverOrganizationId(int receiverOrganizationId) {
        this.receiverOrganizationId = receiverOrganizationId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
