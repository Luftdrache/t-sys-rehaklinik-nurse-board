package com.tsystems.javaschool.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


@Stateless
@MessageDriven(name = "nurse-board-listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/todayTreatmentEventsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class NurseBoardMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(NurseBoardMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("NURSE BOARD: {}", message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
