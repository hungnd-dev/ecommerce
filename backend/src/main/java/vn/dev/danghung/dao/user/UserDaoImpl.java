package vn.dev.danghung.dao.user;

import org.springframework.stereotype.Component;
import vn.dev.danghung.dao.AbstractDao;
import vn.dev.danghung.entities.User;
import vn.dev.danghung.factory.MySQLConnectionFactory;
import vn.dev.danghung.model.request.UserRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDaoImpl extends AbstractDao implements UserDao{
    @Override
    public User find(String username) {
        String sql = "SELECT * FROM "+tUser
                + " WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs = null;
        try {
            conn = MySQLConnectionFactory.getInstance().getMySQLConnection();
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullname"));
                user.setRole(rs.getString("role"));
                user.setState(rs.getInt("state"));
                user.setCreatedAt(rs.getLong("created_at"));
                user.setModifiedAt(rs.getLong("modified_at"));
                user.setTelephone(rs.getString("telephone"));
            }

        } catch (Exception e) {
            eLogger.error("error find user by username: ", e);
        } finally {
            releaseConnect(conn,ps,rs);
        }
        return user;
    }

    @Override
    public void create(UserRequest userRequest) throws SQLException {
        Long currentTime = System.currentTimeMillis();
        String sql = "INSERT INTO " + tUser +
                "(username,password,telephone,fullname,role,created_at,state) VALUES(?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = MySQLConnectionFactory.getInstance().getMySQLConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setQueryTimeout(1);
            ps.setString(1, userRequest.getUserName());
            ps.setString(2, userRequest.getPassWord());
            ps.setString(3, userRequest.getPhone());
            ps.setString(4, userRequest.getFullName());
            ps.setString(5,"client");
            ps.setString(6,currentTime.toString());
            ps.setString(7,String.valueOf(1));

            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            eLogger.error("error insert user: ", e);
            conn.rollback();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
