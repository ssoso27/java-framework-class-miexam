package kr.ac.jejunu.userdao;

import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class UserDao {
    private JejuJdbcTemplate jejuJdbcTemplate;

    public UserDao(JejuJdbcTemplate jejuJdbcTemplate) {
        this.jejuJdbcTemplate = jejuJdbcTemplate;
    }

    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        User result = null;

        try {
            result = jejuJdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
               User user = new User();
               user.setId(rs.getLong("id"));
               user.setName(rs.getString("name"));
               user.setPassword(rs.getString("password"));
               return user;
            });
        } catch (DataAccessException e) {
        }

        return result;
    }

    public Long add(User user) throws SQLException {
        String sql = "INSERT INTO userinfo(name, password) VALUES (?, ?);";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jejuJdbcTemplate.insert(sql, params);
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE userinfo SET name=?, password=? WHERE id=?;";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};

        jejuJdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM userinfo WHERE id=?;";
        Object[] params = new Object[]{id};

        jejuJdbcTemplate.update(sql, params);
    }

}
