package com.pi4home.server.messagesBroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.server.model.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer
{

    private static final Logger logger = LoggerFactory.getLogger(QueueProducer.class);

    @Value("${fanout.exchange}")
    private String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate)
    {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Light light) throws Exception
    {
        logger.info("Storing notification on Queue");
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(light));
        logger.info("Notification stored in queue sucessfully : "
                + light.getName() + " is turned on: " + light.isTurnedOn());
    }
}
