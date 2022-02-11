package com.xoriant.bank.controller;

import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;



@Controller
public class RabbitSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Bean
	public Queue deposit() {
		return new Queue("DepositQ",false);
	}
	
	@Bean
	public Queue withdraw() {
		return new Queue("WithdrawQ", false);
	}
	
	@Bean
	public Queue fundTranfer() {
		return new Queue("FundTransferQ", false);
	}
	
	public void sendDepositEmail(Map<String, Object> depositInfo) {
		rabbitTemplate.convertAndSend("DepositQ",depositInfo);
	}

	public void sendWithDrawEmail(Map<String, Object> withdrawInfo) {
		rabbitTemplate.convertAndSend("WithdrawQ", withdrawInfo);
	}

	public void sendTransferEmail(Map<String, Object> transferInfo) {
		rabbitTemplate.convertAndSend("FundTransferQ", transferInfo);
	}
	
	

}
