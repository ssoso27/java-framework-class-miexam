package kr.ac.jejunu.userdao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public JejuJdbcTemplate jdbcTemplate() { return new JejuJdbcTemplate(dataSource()); }

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(classname));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
