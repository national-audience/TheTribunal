package io.github.nationalaudience.thetribunal.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static io.github.nationalaudience.thetribunal.constant.InternalServicesStaticValues.URL_FEED;

@Service
public class FeedService {

    @Value("${service.feed}")
    private String service;

    public List<FeedResponse> getFeedFromInternalService(String loggedUser) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        URI url = new URI(service + URL_FEED);

        HttpHeaders head = new HttpHeaders();
        head.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> reqEntity = new HttpEntity<>(loggedUser, head);

        FeedResponse[] data = restTemplate.postForObject(url, reqEntity, FeedResponse[].class);

        assert data != null;

        return Arrays.stream(data).toList();
    }
}
