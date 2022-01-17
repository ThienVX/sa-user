package thienvx.sauser.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class PasswordHashingUtil {

    @Value("${pw.has.salt}")
    private static final byte[] SALT = "12345".getBytes(StandardCharsets.UTF_8);

    @Value("${pw.has.iteration}")
    private static int ITERATION;

    @Value("${pw.has.size}")
    private static int SIZE;

    @Value("${pw.has.algorithm}")
    private static String SHA1_ALGORITHM;

    public static String pbkdf2Hash(char[] password)
    {
        KeySpec spec = new PBEKeySpec(password, SALT, ITERATION, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(SHA1_ALGORITHM);

            //Encode to String
            Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
            return encoder.encodeToString(f.generateSecret(spec).getEncoded());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Wrong algorithm: " + SHA1_ALGORITHM, ex);
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Invalid SecretKeyFactory", ex);
        }
    }
}
