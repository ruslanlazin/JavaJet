package ua.pp.lazin.javajet.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.apache.log4j.Logger;


/**
 * Hash passwords for storage, and test passwords against password tokens.
 * Contains only static methods
 *
 * @author Ruslan Lazin
 */
public class PasswordEncoder {

    /**
     * The higher the number of ITERATIONS the more
     * expensive computing the hash is for us and
     * also for an attacker.
     */
    private static final int ITERATIONS = 20 * 1000;
    private static final int SALT_LENGTH = 32;
    private static final int DESIRED_KEY_LENGTH = 256;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final String SECRET = "%1_aSqk67Y4m?m";
    private static final Logger logger = Logger.getLogger(PasswordEncoder.class);

    private PasswordEncoder() {
    }

    /**
     * Computes a salted PBKDF2 hash suitable for storing in a database
     * from given plaintext password.
     * Empty passwords are not supported.
     */
    public static String getSaltedHash(final String password) {
        if (password == null || password.length() == 0) {
            logger.error("Empty password [" + password + "] was given.");
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }
        byte[] salt = null;
        try {
            salt = SecureRandom.getInstance(SALT_ALGORITHM).generateSeed(SALT_LENGTH);
        } catch (NoSuchAlgorithmException e) {
            logger.error("An exception occurred during salt generating. " +
                    "Default salt value will be used. Contact to security engineer immediately");
            salt = "DefaultSaltValue".getBytes();
        }
        // store the salt with the password
        return Base64.getEncoder().encodeToString(salt) + "$" + hash((password), salt);
    }

    /**
     * Checks whether given plaintext password corresponds
     * to a stored salted hash of the password.
     */
    public static boolean check(final String password, final String stored) {
        if (password == null || password.length() == 0) {
            return false;
        }
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            logger.error("Incorrect format of the stored password = " + stored);
            throw new IllegalStateException("Stored password must be in 'salt$hash' format");
        }
        String hashOfInput = hash((password), Base64.getDecoder().decode(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    /**
     * Calculates hash from password and salt
     */
    private static String hash(final String password, final byte[] salt) {
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(HASH_ALGORITHM);

            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(
                    (SECRET + password).toCharArray(), salt, ITERATIONS, DESIRED_KEY_LENGTH)
            );
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            String errorMessage = "An exception occurred during hash generating";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
