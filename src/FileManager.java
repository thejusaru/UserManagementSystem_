import java.sql.*;
import java.util.ArrayList;

public class FileManager {

    public boolean saveUser(User user) {

        if (findUser(user.getUsername()) != null)
            return false;

        if (findUserId(user.getUserId()) != null)
            return false;

        if (findGmail(user.getGmail()) != null)
            return false;

        if (findPhone(user.getPhone()) != null)
            return false;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUserId());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getGmail());
            ps.setString(5, user.getPhone());

            ps.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                users.add(new User(
                        rs.getString("username"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("gmail"),
                        rs.getString("phone")
                ));

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return users;

    }

    public User findUser(String username) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getString("username"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("gmail"),
                        rs.getString("phone")
                );

                con.close();

                return user;

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public User findUserId(String userId) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE userid=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getString("username"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("gmail"),
                        rs.getString("phone")
                );

                con.close();

                return user;

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public User findGmail(String gmail) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE gmail=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, gmail);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getString("username"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("gmail"),
                        rs.getString("phone")
                );

                con.close();

                return user;

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public User findPhone(String phone) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE phone=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, phone);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User(
                        rs.getString("username"),
                        rs.getString("userid"),
                        rs.getString("password"),
                        rs.getString("gmail"),
                        rs.getString("phone")
                );

                con.close();

                return user;

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public boolean updateUser(User updatedUser) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "UPDATE users SET userid=?, password=?, gmail=?, phone=? WHERE username=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, updatedUser.getUserId());
            ps.setString(2, updatedUser.getPassword());
            ps.setString(3, updatedUser.getGmail());
            ps.setString(4, updatedUser.getPhone());
            ps.setString(5, updatedUser.getUsername());

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean deleteUser(String username) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM users WHERE username=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);

            int rows = ps.executeUpdate();

            con.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean changePassword(String username, String newPassword) {

        User user = findUser(username);

        if (user == null)
            return false;

        user.setPassword(newPassword);

        return updateUser(user);

    }

    public ArrayList<User> searchUser(String keyword) {

        ArrayList<User> result = new ArrayList<>();

        keyword = keyword.toLowerCase();

        ArrayList<User> users = getAllUsers();

        for (User user : users) {

            if (user.getUsername().toLowerCase().contains(keyword)
                    || user.getUserId().toLowerCase().contains(keyword)
                    || user.getGmail().toLowerCase().contains(keyword)
                    || user.getPhone().contains(keyword)) {

                result.add(user);

            }

        }

        return result;

    }

    public int getTotalUsers() {

        return getAllUsers().size();

    }

    public boolean usernameExists(String username) {

        return findUser(username) != null;

    }
    public boolean userIdExists(String userId) {
        return findUserId(userId) != null;
    }

    public boolean gmailExists(String gmail) {

        return findGmail(gmail) != null;

    }

    public boolean phoneExists(String phone) {

        return findPhone(phone) != null;

    }

}