package kr.ac.jejunu.userdao;

import javax.sql.DataSource;

public class JejuJdbcTemplate extends JdbcContext {
    public JejuJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }
}
