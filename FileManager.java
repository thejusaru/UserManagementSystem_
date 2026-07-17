import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "users.txt";

    public boolean saveUser(User user) {

        if (findUser(user.getUsername()) != null)
            return false;

        if (findUserId(user.getUserId()) != null)
            return false;

        if (findGmail(user.getGmail()) != null)
            return false;

        if (findPhone(user.getPhone()) != null)
            return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            bw.write(user.toString());
            bw.newLine();

            return true;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;
    }

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {

                file.createNewFile();
                return users;

            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().equals(""))
                    continue;

                String[] data = line.split(",");

                if (data.length == 5) {

                    users.add(new User(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4]
                    ));

                }

            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return users;

    }

    public User findUser(String username) {

        ArrayList<User> users = getAllUsers();

        for (User user : users) {

            if (user.getUsername().equalsIgnoreCase(username)) {

                return user;

            }

        }

        return null;

    }

    public User findUserId(String userId) {

        ArrayList<User> users = getAllUsers();

        for (User user : users) {

            if (user.getUserId().equalsIgnoreCase(userId)) {

                return user;

            }

        }

        return null;

    }

    public User findGmail(String gmail) {

        ArrayList<User> users = getAllUsers();

        for (User user : users) {

            if (user.getGmail().equalsIgnoreCase(gmail)) {

                return user;

            }

        }

        return null;

    }

    public User findPhone(String phone) {

        ArrayList<User> users = getAllUsers();

        for (User user : users) {

            if (user.getPhone().equals(phone)) {

                return user;

            }

        }

        return null;

    }

    public boolean updateUser(User updatedUser) {

        ArrayList<User> users = getAllUsers();

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (User user : users) {

                if (user.getUsername().equalsIgnoreCase(updatedUser.getUsername())) {

                    bw.write(updatedUser.toString());

                } else {

                    bw.write(user.toString());

                }

                bw.newLine();

            }

            bw.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    public boolean deleteUser(String username) {

        ArrayList<User> users = getAllUsers();

        boolean deleted = false;

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (User user : users) {

                if (!user.getUsername().equalsIgnoreCase(username)) {

                    bw.write(user.toString());
                    bw.newLine();

                } else {

                    deleted = true;

                }

            }

            bw.close();

            return deleted;

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