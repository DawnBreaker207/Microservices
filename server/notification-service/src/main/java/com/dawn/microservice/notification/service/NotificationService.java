package com.dawn.microservice.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(com.dawn.microservice.order.event.OrderPlacedEvent orderPlacedEvent) {
	log.info("Got Message from order-placed topic {}", orderPlacedEvent);
	MimeMessagePreparator messagePerparator = mimeMessage -> {
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	    messageHelper.setFrom("springshop@email.com");
	    messageHelper.setTo(orderPlacedEvent.getEmail().toString());
	    messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully",
		    orderPlacedEvent.getOrderNumber()));
	    messageHelper.setText(String.format("""
	    	Hi

	    	Your order with order number %s is now placed successfully

	    	Best Regards

	    	Spring Shop
	    	""", orderPlacedEvent.getOrderNumber(), orderPlacedEvent.getLastName().toString(),
		    orderPlacedEvent.getOrderNumber()));

	};

	try {
	    javaMailSender.send(messagePerparator);
	    log.info("Order Notification email send!!");
	} catch (MailException e) {
	    log.error("Exception occurred when sending email", e);
	    throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
	}
    }
}
