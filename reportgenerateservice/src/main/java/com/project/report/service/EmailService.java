package com.project.report.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMailWithAttachment(String to, String subject, String body) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            File file = new File("Report.xlsx");
            FileSystemResource fsr = new FileSystemResource(file);
            helper.addAttachment(fsr.getFilename(), new InputStreamSource() {
                @Override
                public InputStream getInputStream() throws IOException {
                    return fsr.getInputStream();
                }
            }, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");

        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
        mailSender.send(message);
    }
}
