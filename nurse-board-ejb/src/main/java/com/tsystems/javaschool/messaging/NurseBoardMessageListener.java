package com.tsystems.javaschool.messaging;

import com.tsystems.javaschool.data.TreatmentEvent;
import com.tsystems.javaschool.data.TreatmentEventsRepository;
import com.tsystems.javaschool.service.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.List;


@Stateless
@MessageDriven(name = "nurse-board-listener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/todayTreatmentEventsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class NurseBoardMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(NurseBoardMessageListener.class);

    @Inject
    RestClient restClient;

    @Inject
    TreatmentEventsRepository treatmentEventsRepository;

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("NURSE BOARD: {}", message.getBody(String.class));
            List<TreatmentEvent> treatmentEventList = restClient.getTodayTreatmentEvents();
            treatmentEventsRepository.setTreatmentEventList(treatmentEventList);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
