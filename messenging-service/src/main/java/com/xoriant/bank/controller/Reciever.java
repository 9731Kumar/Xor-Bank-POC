package com.xoriant.bank.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

@Controller
public class Reciever {

	@Autowired
	private JavaMailSender javaMailSender;

	@RabbitListener(queues = "DepositQ")
	public void sendDepositEmail(Map<String, Object> depositInfo) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("kalyankumara1998@gmail.com");

		//Long trxId = (Long) depositInfo.get("Transaction_Id");
		LocalDate trxDate = (LocalDate) depositInfo.get("Transaction_Date");
		Long accNo = (Long) depositInfo.get("To_Account");
		double amount = (double) depositInfo.get("Transaction_Amount");

		msg.setSubject("Amount Deposit Confirmation");
		msg.setText("You Account Number " + accNo + " credited with amount Rs." + amount
				+ " on " +trxDate);

		javaMailSender.send(msg);
	}
	
	@RabbitListener(queues = "WithdrawQ")
	public void sendWithdrawEmail(Map<String, Object> withdrawInfo) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("kalyankumara1998@gmail.com");

		//Long trxId = (Long) withdrawInfo.get("Transaction_Id");
		LocalDate trxDate = (LocalDate) withdrawInfo.get("Transaction_Date");
		Long accNo = (Long) withdrawInfo.get("From_Account");
		double amount = (double) withdrawInfo.get("Transaction_Amount");

		msg.setSubject("Amount Withdraw Confirmation");
		msg.setText("You Account Number " + accNo + " debited with amount Rs." + amount
				+ " on " +trxDate);

		javaMailSender.send(msg);
	}
	
	
	@RabbitListener(queues = "FundTransferQ")
	public void sendFundTransferEmail(Map<String, Object> transferInfo) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("kalyankumara1998@gmail.com");

		//Long trxId = (Long) withdrawInfo.get("Transaction_Id");
		LocalDate trxDate = (LocalDate) transferInfo.get("Transaction_Date");
		Long accNo = (Long) transferInfo.get("From_Account");
		Long toAccNo = (Long) transferInfo.get("To_Account");
		double amount = (double) transferInfo.get("Transaction_Amount");

		msg.setSubject("Amount Tranfer Confirmation");
		msg.setText("An Amount of " + amount + " has been debited to your Account No." + accNo
				+ " on " +trxDate);

		javaMailSender.send(msg);
	}

}
