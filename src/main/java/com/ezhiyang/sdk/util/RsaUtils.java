package com.ezhiyang.sdk.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
/**
 * rsa utils
 * @author ZY
 *
 */
public class RsaUtils {

  static private Base64.Encoder encoder = Base64.getMimeEncoder();

  public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
    byte[] bytes = Base64.getDecoder().decode(cipherText);

    Cipher decriptCipher = Cipher.getInstance("RSA");
    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

    return new String(decriptCipher.doFinal(bytes), UTF_8);
  }

  public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
    Cipher encryptCipher = Cipher.getInstance("RSA");
    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

    return Base64.getEncoder().encodeToString(cipherText);
  }

  public static KeyPair generateKeyPair() throws Exception {
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
    generator.initialize(2048, new SecureRandom());
    KeyPair pair = generator.generateKeyPair();

    return pair;
  }

  

  public static PrivateKey loadPrivateKey(String privateKeyContent)
      throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
    privateKeyContent = privateKeyContent.replaceAll("\\n", "")
        .replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "")
        .replace("-----BEGIN RSA PRIVATE KEY-----", "")
        .replace("-----END RSA PRIVATE KEY-----", "");
    KeyFactory kf = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpecPkcs8 =
        new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(privateKeyContent));
    PrivateKey privKey = kf.generatePrivate(keySpecPkcs8);

    return privKey;
  }

  public static RSAPublicKey loadPublicKey(String publicKeyContent)
      throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, URISyntaxException {
    publicKeyContent = publicKeyContent.replaceAll("\\n", "")
        .replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "")
        .replace("-----BEGIN RSA PUBLIC KEY-----", "")
        .replace("-----END RSA PUBLIC KEY-----", "");;

    KeyFactory kf = KeyFactory.getInstance("RSA");

    X509EncodedKeySpec keySpecX509 =
        new X509EncodedKeySpec(Base64.getMimeDecoder().decode(publicKeyContent));
    RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
    return pubKey;
  }

  public static String sign(String plainText, PrivateKey privateKey) throws Exception {
    Signature privateSignature = Signature.getInstance("SHA256withRSA");
    privateSignature.initSign(privateKey);
    privateSignature.update(plainText.getBytes(UTF_8));

    byte[] signature = privateSignature.sign();

    return Base64.getEncoder().encodeToString(signature);
  }

  public static boolean verify(String plainText, String signature, PublicKey publicKey) {
    Signature publicSignature;
    try {
      publicSignature = Signature.getInstance("SHA256withRSA");
      publicSignature.initVerify(publicKey);
      publicSignature.update(plainText.getBytes(UTF_8));

      byte[] signatureBytes = Base64.getDecoder().decode(signature);

      return publicSignature.verify(signatureBytes);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



  static public String write(PrivateKey privateKey) throws IOException {
    StringWriter out = new StringWriter();
    out.write("-----BEGIN RSA PRIVATE KEY-----\n");
    writeBase64(out, privateKey);
    out.write("-----END RSA PRIVATE KEY-----\n");
    return out.toString();
  }


  static public String write(PublicKey publicKey) throws IOException {
    StringWriter out = new StringWriter();
    out.write("-----BEGIN RSA PUBLIC KEY-----\n");
    writeBase64(out, publicKey);
    out.write("-----END RSA PUBLIC KEY-----\n");
    return out.toString();
  }

  static private void writeBase64(Writer out, Key key)
      throws java.io.IOException {
    byte[] buf = key.getEncoded();
    out.write(encoder.encodeToString(buf));
    out.write("\n");
  }
}
