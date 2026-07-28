import org.mindrot.jbcrypt.BCrypt;

public class TestBCrypt {

    public static void main(String[] args) {

        String password = "admin123";

        // Encrypt password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println("Original Password : " + password);
        System.out.println("Encrypted Password: " + hashedPassword);

        // Verify password
        boolean valid = BCrypt.checkpw(password, hashedPassword);

        if (valid) {
            System.out.println("Password Matched");
        } else {
            System.out.println("Password Incorrect");
        }

    }

}