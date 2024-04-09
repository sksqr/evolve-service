package com.example.evolveservice.config;

import com.example.evolveservice.dtos.KafkaEmailPayloadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class KafkaConsumerConfig {
    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "topic01", groupId = "email")
    public void consumeFromTopic01(Object payload) throws JsonProcessingException {
        LOGGER.info("Getting payload from kafka: {}", payload);
        String payloadValue = (String) ((ConsumerRecord)payload).value();
        KafkaEmailPayloadDto kafkaEmailPayloadDto = objectMapper.readValue(payloadValue,KafkaEmailPayloadDto.class);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject(kafkaEmailPayloadDto.getSubject());
        simpleMailMessage.setText(kafkaEmailPayloadDto.getBody());
        simpleMailMessage.setCc(kafkaEmailPayloadDto.getCc());
        simpleMailMessage.setTo(kafkaEmailPayloadDto.getEmailIds().get(0));
        javaMailSender.send(simpleMailMessage);
    }
}
