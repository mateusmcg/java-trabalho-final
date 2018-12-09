package com.mateusc.trabalhofinal.config;

public class EmailSender {
    public static void sendEmail(String subject, String body) {
		final String fromEmail = "example@gmail.com";
		final String password = "*********";
		final String toEmail = "example@gmail.com";

		System.out.println("Initializing email send");

		EmailConfig config = new EmailConfig();

		config.sendEmail(fromEmail, password, toEmail, subject, body);
	}
}