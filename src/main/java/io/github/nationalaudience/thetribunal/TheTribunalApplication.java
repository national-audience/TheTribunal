package io.github.nationalaudience.thetribunal;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableHazelcastHttpSession
public class TheTribunalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheTribunalApplication.class, args);
    }
  
    @Bean
    public Config config(){
        var config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        return config;
    }
}
