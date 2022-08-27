package sk.icoin.blockchainj;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class StringUtil {
	public static String getStringFromKey(PublicKey key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static byte[] applyECDSASig(PrivateKey key, String data) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(key);
			byte[] strByte = data.getBytes();
			dsa.update(strByte);
			byte[] realSignature = dsa.sign();
			output = realSignature;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}

	public static boolean verifyECDSASig(PublicKey sender, String data, byte[] signature) {
		try {
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(sender);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature); 
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
