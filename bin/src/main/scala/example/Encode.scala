package example

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.security.KeyPair;
import javax.xml.bind.DatatypeConverter;

class Encode {

  val encryption = new Encryption()

  @throws(classOf[Exception])
  def encrypt(plainText: String): String = encryption.encryptPasswordBased(plainText);

  @throws(classOf[Exception])
  def decrypt(cipherText: String): String = encryption.decryptPasswordBased(cipherText);

  def hashpw(pass: String): String = {

    val stored: String = BCrypt.hashpw(pass, BCrypt.gensalt());

    try {

      return encrypt(stored);

    } catch {
      case e: Exception => {

      return "";
      }
    }

  }

  def verify(pass :String, hash: String): Boolean = {
      try{

        val newHash = decrypt(hash);

        return BCrypt.checkpw(pass, newHash);

    } catch {
      case e: Exception => {
        return false;
      }
    }
  }
}
