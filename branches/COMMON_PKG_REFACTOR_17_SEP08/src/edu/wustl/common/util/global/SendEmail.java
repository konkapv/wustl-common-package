/**
 * <p>Title: SendEmail Class>
 * <p>Description:	This Class is used to send emails.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 * Created on Apr 18, 2005
 */

package edu.wustl.common.util.global;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import edu.wustl.common.util.logger.Logger;

/**
 * This Class is used to send E-mails.
 *
 * @author gautam_shetty
 */
public class SendEmail
{

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(SendEmail.class);

	/**
	 * From email address.
	 */
	private String from;

	/**
	 * Host address.
	 */
	private String host;

	/**
	 * Used to send the mail with given parameters.
	 *
	 * @param emailDetails EmailDetails object
	 * @return true if mail was successfully sent, false if it fails
	 */
	public boolean sendmail(EmailDetails emailDetails)
	{

		// create some properties and get the default Session
		Properties props = new Properties();
		props.put("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(false);
		boolean sendStatus;

		try
		{

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));

			msg.setRecipients(Message.RecipientType.CC, emailDetails.getCcInternetAddrArray());
			msg.setRecipients(Message.RecipientType.BCC, emailDetails.getBccInternetAddrArray());
			msg.setRecipients(Message.RecipientType.TO, emailDetails.getToInternetAddrArray());
			msg.setSubject(emailDetails.getSubject());
			msg.setSentDate(new Date());
			// create and fill the first message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(emailDetails.getBody());
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(messageBodyPart);
			// add the Multipart to the message
			msg.setContent(multiPart);
			Transport.send(msg);
			sendStatus = true;
		}
		catch (MessagingException mex)
		{
			logger.warn("Unable to send mail to: " + emailDetails.getToAddress(),mex);
			sendStatus = false;
		}
		return sendStatus;
	}

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from)
	{
		this.from = from;
	}

	/**
	 * @return the host
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host)
	{
		this.host = host;
	}
}
