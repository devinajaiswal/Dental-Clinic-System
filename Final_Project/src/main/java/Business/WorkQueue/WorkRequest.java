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
    private String status;
    private LocalDateTime requestTime;
    private LocalDateTime assignTime;
    private LocalDateTime finishTime;
    private LocalDateTime confirmTime;

    public enum Status {
        SENT("SENT"),
        ASSIGNED("ASSIGNED"),
        FINISHED("FINISHED"),
        CONFIRMED("CONFIRMED");

        private Status(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

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

    public LocalDateTime getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(LocalDateTime assignTime) {
        this.assignTime = assignTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDateTime getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(LocalDateTime confirmTime) {
        this.confirmTime = confirmTime;
    }

    public WorkRequest() {
        requestTime = LocalDateTime.now();
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



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
