import java.util.Scanner;

public class LoginApp {
    public static void main(String[] args) throws Exception {
        UserStore store = new UserStore("users.db");
        Scanner sc = new Scanner(System.in);

        System.out.println("1) Register  2) Login");
        System.out.print("Choose: ");
        String choice = sc.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("New username: ");
            String u = sc.nextLine().trim();
            if (store.exists(u)) { System.out.println("User exists."); sc.close(); return; }
            System.out.print("New password: ");
            String p = sc.nextLine();

            String salt = AuthUtils.newSaltB64();
            String hash = AuthUtils.hashPBKDF2(p, salt);
            store.createUser(u, salt, hash);
            store.save();
            System.out.println(" Registered.");

        } else if (choice.equals("2")) {
            System.out.print("Username: ");
            String u = sc.nextLine().trim();
            String[] sh = store.getSaltHash(u);
            if (sh == null) { System.out.println("No such user."); sc.close(); return; }

            System.out.print("Password: ");
            String p = sc.nextLine();
            if (!AuthUtils.verify(p, sh[0], sh[1])) {
                System.out.println(" Wrong password."); sc.close(); return;
            }

            String otp = AuthUtils.generateOTP6();
            System.out.println("(Simulated 2FA) Your code is: " + otp);
            System.out.print("Enter 2FA code: ");
            String entered = sc.nextLine().trim();
            if (!otp.equals(entered)) {
                System.out.println(" Invalid 2FA.");
                sc.close(); return;
            }
            System.out.println(" Success!!! You're logged in, " + u + "!");
        } else {
            System.out.println("bye");
        }
        sc.close();
    }
}


