package io.github.nationalaudience.thetribunal.services;

import io.github.nationalaudience.thetribunal.entity.Review;
import io.github.nationalaudience.thetribunal.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static io.github.nationalaudience.thetribunal.constant.InternalServicesStaticValues.URL_FEED;

@Service
public class FeedService {



    public List<FeedResponse> getFeedFromInternalService() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        URI url = new URI(URL_FEED);

        FeedResponse[] data = restTemplate.getForObject(url, FeedResponse[].class);

        assert data != null;

        return Arrays.stream(data).toList();
    }
}
