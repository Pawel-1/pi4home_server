package com.pi4home.server.messagesBroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.server.model.Blind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlindsQueueProducer
{

    private static final Logger logger = LoggerFactory.getLogger(BlindsQueueProducer.class);

    @Value("${blinds.fanout.exchange}")
    private String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BlindsQueueProducer(RabbitTemplate rabbitTemplate)
    {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }


    public void produce(Blind blind) throws Exception
    {
        logger.info("Storing notification on Queue");
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(blind));
        logger.info("Notification stored in queue sucessfully : "
                + blind.getName() + " is turned on: " + blind.getPercentageMaskingState());
    }

}
