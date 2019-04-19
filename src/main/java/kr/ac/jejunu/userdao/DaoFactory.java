package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker getConnectionMaker() {
        return new JejuConnectionMaker();
    }

    @Bean
    public UserDao getUserDao() {
        return new UserDao(getConnectionMaker());
    }
}
