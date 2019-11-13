package com.pi4home.server.messagesBroker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4home.server.model.Light;
import com.pi4home.server.model.Yeelight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Component
public class YeelightsQueueProducer
{

    private static final Logger logger = LoggerFactory.getLogger(YeelightsQueueProducer.class);

    @Value("${yeelights.fanout.exchange}")
    private String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public YeelightsQueueProducer(RabbitTemplate rabbitTemplate)
    {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produce(Yeelight yeelight) throws Exception
    {
        logger.info("Storing notification on Queue");
        rabbitTemplate.setExchange(fanoutExchange);
        rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(yeelight));
        logger.info("Notification stored in queue sucessfully : "
                + yeelight.getName() + " is turned on: " + yeelight.isTurnedOn());
    }

}
