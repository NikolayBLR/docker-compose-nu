package com.example.notification_service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        "spring.mail.host=localhost",
        "spring.mail.port=2525",
        "spring.mail.username=test",
        "spring.mail.password=test",
        "spring.mail.properties.mail.smtp.auth=false",
        "spring.mail.properties.mail.smtp.starttls.enable=false",
        "spring.mail.properties.mail.smtp.ssl.enable=false",
        "spring.kafka.listener.auto-startup=false"
})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MailIntegrationTest {

    private GreenMail greenMail;

    @BeforeAll
    void startMailServer() throws InterruptedException {
        greenMail = new GreenMail(new ServerSetup(2525, null, "smtp"));
        greenMail.setUser("test@mail.ru", "test", "test");
        greenMail.start();
        Thread.sleep(500);
    }

    @AfterAll
    void stopMailServer() {
        greenMail.stop();
    }

    @Autowired
    private JavaMailSender mailSender;

    @Test
    void testSendEmail() throws Exception {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("test@mail.ru");
        msg.setSubject("Test");
        msg.setText("Okey");
        mailSender.send(msg);

        greenMail.waitForIncomingEmail(1);

        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertEquals(1, messages.length);

        String body = (String) messages[0].getContent();
        assertTrue(body.contains("Okey"));
    }
}

