package example;

import java.io._;
import java.util._;
import java.security._;
import javax.crypto._;
import javax.crypto.spec._;

class Encryption {

    val password = "abcd1234";
    val key = new DESKeySpec(password.getBytes());
    val keyFactory = SecretKeyFactory.getInstance("DES");
    val secretKey = keyFactory.generateSecret(key);

    @throws(classOf[Exception])
    def setupCipher(optMode: Int): Cipher = {
      val cipher = Cipher.getInstance("DES");
      cipher.init(optMode, secretKey);
      return cipher;
    }

    @throws ( classOf[Exception] )
    def encryptPasswordBased(plainText :String) : String =
    {
        val cipher = setupCipher(Cipher.ENCRYPT_MODE);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()))
    }

    @throws ( classOf[Exception] )
    def decryptPasswordBased(cipherText: String): String = {
      val cipher = setupCipher(Cipher.DECRYPT_MODE);
      return new String(cipher.doFinal(Base64.getDecoder()
          .decode(cipherText)));
    }

}
