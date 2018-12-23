package com.mateusc.trabalhofinal.config;

public class EmailSender {
    public static void sendEmail(String subject, String body, String toEmail) {
		final String fromEmail = "example@gmail.com";
		final String password = "*********";

		System.out.println("Initializing email send");

		new EmailConfig().sendEmail(fromEmail, password, toEmail, subject, body);
	}
}