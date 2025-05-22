package com.solid.principles;

public class ASingleResponsibility {
//	The Single Responsibility Principle (SRP) is one of the SOLID principles of 
//	object-oriented design. It states that a class should have only one reason to change, 
//	meaning it should have only one job or responsibility.
}

//Without SRP
class UserManager {
	public void createUser(String username, String email) {
		// Code to create a user
		System.out.println("User " + username + " created.");
		sendEmail(email, "Welcome!", "Thank you for registering.");
	}

	public void sendEmail(String to, String subject, String body) {
		// Code to send an email
		System.out.println("Sending email to " + to + ": " + subject + " - " + body);
	}

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		userManager.createUser("john_doe", "john@example.com");
	}
}

//With SRP
class UserManagerSRP {
	private EmailService emailService;

	public UserManagerSRP(EmailService emailService) {
		this.emailService = emailService;
	}

	public void createUser(String username, String email) {
		// Code to create a user
		System.out.println("User " + username + " created.");
		emailService.sendEmail(email, "Welcome!", "Thank you for registering.");
	}

	public static void main(String[] args) {
		EmailService emailService = new EmailService();
		UserManagerSRP userManager = new UserManagerSRP(emailService);
		userManager.createUser("john_doe", "john@example.com");
	}
}

class EmailService {
	public void sendEmail(String to, String subject, String body) {
		// Code to send an email
		System.out.println("Sending email to " + to + ": " + subject + " - " + body);
	}
}
