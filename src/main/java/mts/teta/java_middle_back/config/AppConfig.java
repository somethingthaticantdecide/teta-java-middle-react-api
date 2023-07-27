package mts.teta.java_middle_back.config;

import mts.teta.java_middle_back.model.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfig {
    @Bean
    public Map<String, UserInfo> userMap() {
        return new ConcurrentHashMap<>();
    }
}
