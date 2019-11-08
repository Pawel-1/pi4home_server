package com.pi4home.server.configurations;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitLightsQueueConfiguration
{
    @Value("${lights.fanout.exchange}")
    private String fanoutExchange;
    @Value("${lights.queue.name}")
    private String queueName;

    @Bean
    Queue queue()
    {
        return new Queue(queueName, true);
    }

    @Bean
    FanoutExchange exchange()
    {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
