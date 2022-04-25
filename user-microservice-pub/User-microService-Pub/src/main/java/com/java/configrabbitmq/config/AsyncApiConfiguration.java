package com.java.configrabbitmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import com.google.common.collect.ImmutableMap;
import com.java.configrabbitmq.dto.OrderStatus;

import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
/** 
 * <code>AsyncApiConfiguration</code> is ... <br>
 * This class is a configuration which scans the packages for reading listners
 * and adds producer configuration 
 * @author Aravind
 */
@Configuration
@EnableAsyncApi
public class AsyncApiConfiguration {
    private final String amqpHost;
    private final String amqpPort;
    
    /**
     * @param amqpHost
     * @param amqpPort
     */
    public AsyncApiConfiguration(@Value("${server.rmq.host}") String amqpHost, @Value("${server.rmq.port}") int amqpPort) 
    {
        this.amqpHost = amqpHost;
        this.amqpPort = String.valueOf(amqpPort);
    }
    /**
     * 
     * This describes the metadata and the producer details of the 
     * API.
     * 
     * @return AsyncApiDocket
     */
    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Order-service-Publisher")
                .build();        
        ProducerData producerData = ProducerData.builder()
        		.channelName(MessagingQueue.queue)
        		.binding(ImmutableMap.of("amqp", new AMQPOperationBinding()))
        		.payloadType(OrderStatus.class)
        		.build();
        Server amqp = Server.builder()
                .protocol("amqp")
                .url(String.format("%s:%s", amqpHost, amqpPort))
                .build();
        return AsyncApiDocket.builder()
                .basePackage("com.java.configrabbitmq")
                .info(info)
                .server("amqp", amqp)
                .producer(producerData)
                .build();
    }
}
