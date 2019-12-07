/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.time.LocalDateTime;

/**
 *
 * @author zhangchuanqi
 */
public class PaymentWorkRequest extends WorkRequest {

    private int paymentId;
    private String type;
    private int treatOrPolicyId;
    private double amount;
    private String status;

    public enum PaymentType {
        TREATMENT("TREATMENT"),
        POLICY("POLICY");

        private PaymentType(String value) {
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

    public enum Status {
        REQUESTED("REQUESTED"),
        PAID("PAID"),
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

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTreatOrPolicyId() {
        return treatOrPolicyId;
    }

    public void setTreatOrPolicyId(int treatOrPolicyId) {
        this.treatOrPolicyId = treatOrPolicyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String paymentStatus) {
        this.status = paymentStatus;
    }

}
