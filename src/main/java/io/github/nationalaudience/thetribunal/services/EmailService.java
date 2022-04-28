package io.github.nationalaudience.thetribunal.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static io.github.nationalaudience.thetribunal.constant.InternalServicesStaticValues.URL_FOLLOW_BY_USER_MAIL;

@Service
@EnableAsync
public class EmailService {

    @Value("${service.email}")
    private String service;


    public record EmailRequest(String to, String subject, String body) {
    }

    public void sendFollowedByUserMail(String follower, String emailAddress)
            throws URISyntaxException {
        var url = new URI(service + URL_FOLLOW_BY_USER_MAIL);
        var request = new EmailRequest(
                emailAddress,
                "Someone is interested in your opinions...",
                """
                        You got a new follower in TheTribunal!
                                                
                        %s is interested in your opinions!                        
                        """.formatted(follower)
        );
        sendMail(request, url);
    }

    @Async
    void sendMail(EmailRequest request, URI url) {
        var restTemplate = new RestTemplate();
        var head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        var reqEntity = new HttpEntity<>(request, head);

        restTemplate.postForEntity(url, reqEntity, EmailRequest.class);
    }


}
