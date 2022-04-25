package com.java.configrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.*;


@Configuration
public class MessagingQueue {
	
	//declaring as constants instead of the Hard coded values below
	public static final String queue = "Notification_service_Queue";
	public static final String exchange = "Notification_MSV_Exchange";
	public static final String routingKey = "RabbitMqRoutingKey";
	
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
        
          @Bean public TopicExchange exchange() { return new
          TopicExchange(exchange); }
          
          //binding the exchange to the queue here in this method..
          
          @Bean public Binding binding(Queue queue, TopicExchange exchange) {
          return BindingBuilder.bind(queue).to(exchange).with(routingKey); }
         
	
	//using converters because we are not dealing with Primitive datatypes , we deal with objects so we use converters
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	//with this template we can publish the event /message to queue and can consume it.
	@Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
