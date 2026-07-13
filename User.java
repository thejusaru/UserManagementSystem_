public class User {

    private String username;
    private String userId;
    private String password;
    private String gmail;
    private String phone;

    public User(String username, String userId, String password,
                String gmail, String phone) {

        this.username = username;
        this.userId = userId;
        this.password = password;
        this.gmail = gmail;
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPhone() {
        return phone;
    }

     public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {

        return username + "," + userId + ","+ password + ","+ gmail + ","+ phone;

    }

}