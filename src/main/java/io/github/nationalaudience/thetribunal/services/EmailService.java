package io.github.nationalaudience.thetribunal.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static io.github.nationalaudience.thetribunal.constant.InternalServicesStaticValues.*;

@Service
@EnableAsync
public class EmailService {

    @Async
    public void sendFollowedByUserMail(String follower, String emailAddress) throws URISyntaxException {

        URI url = new URI(URL_FOLLOW_BY_USER_MAIL);

        List<String> mail = new ArrayList<>();

        String emailSubject = "Someone is interested in your opinions...";

        String emailBody = ("You got a new follower in TheTribunal!\n");
        emailBody += (follower + " is interested in your opinions!\n");

        mail.add(emailAddress);
        mail.add(emailSubject);
        mail.add(emailBody);

        sendMail(mail, url);
    }

    private void sendMail(List<String> mail, URI url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List> reqEntity = new HttpEntity<>(mail, head);

        restTemplate.postForEntity(url, reqEntity, String.class);
    }


}
