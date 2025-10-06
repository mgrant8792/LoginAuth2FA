import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AuthUtils {
    private static final SecureRandom RNG = new SecureRandom();
    public static final int SALT_BYTES = 16;
    public static final int ITERATIONS = 120000;
    public static final int KEY_LEN_BITS = 256;

    public static String newSaltB64() {
        byte[] s = new byte[SALT_BYTES];
        RNG.nextBytes(s);
        return Base64.getEncoder().encodeToString(s);
    }

    public static String hashPBKDF2(String password, String saltB64) throws Exception {
        byte[] salt = Base64.getDecoder().decode(saltB64);
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LEN_BITS);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(key);
    }

    public static boolean verify(String password, String saltB64, String expectedHashB64) throws Exception {
        return hashPBKDF2(password, saltB64).equals(expectedHashB64);
    }

    public static String generateOTP6() {
        int code = RNG.nextInt(1_000_000);
        return String.format("%06d", code);
    }
}

