import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private final String FILE_NAME = "users.txt";

    public boolean saveUser(User user) {

        try {
            if (findUser(user.getUsername()) != null) {
                return false;
            }

            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(user.toString());
            bw.newLine();

            bw.close();
            fw.close();

            return true;

        } catch (Exception e) {
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

                if (line.trim().length() == 0)
                    continue;

                String data[] = line.split(",");

                if (data.length == 5) {

                    User user = new User(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4]);

                    users.add(user);

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

        for (User u : users) {

            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }

        }

        return null;

    }

    public boolean updateUser(User updatedUser) {

        ArrayList<User> users = getAllUsers();

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (User u : users) {

                if (u.getUsername().equalsIgnoreCase(updatedUser.getUsername())) {
                    bw.write(updatedUser.toString());
                } else {
                    bw.write(u.toString());
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

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (User u : users) {

                if (!u.getUsername().equalsIgnoreCase(username)) {

                    bw.write(u.toString());
                    bw.newLine();

                }

            }

            bw.close();

            return true;

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

        ArrayList<User> users = getAllUsers();

        for (User u : users) {

            if (u.getUsername().toLowerCase().contains(keyword.toLowerCase())
                    || u.getUserId().toLowerCase().contains(keyword.toLowerCase())) {

                result.add(u);

            }

        }

        return result;

    }
}