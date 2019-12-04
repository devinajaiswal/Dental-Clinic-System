/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class InquiryWorkRequest extends WorkRequest {

    private ArrayList<Message> messages;

    public InquiryWorkRequest() {
        this.messages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
