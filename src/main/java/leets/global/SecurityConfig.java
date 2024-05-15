package leets.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public EncryptHelper encryptConfig() {
        return new BcryptImpl();
    }
}
