package sk.icoin.blockchainj;

import java.security.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	public String txID;
	public PublicKey sender;
	public PublicKey reciepent;
	public float val;
	public byte[] signature;
	public List<TransactionInput> inputs = new ArrayList<>();
	public List<TransactionOutput> outputs = new ArrayList<>();
	private static int seq = 0;
	
	public Transaction(PublicKey from, PublicKey to, float value, List<TransactionInput> inputs) {
		this.sender = from;
		this.reciepent = to;
		this.val = value;
		this.inputs = inputs;
	}
	
	private String calcHash() {
		seq++;
		return SHA256Helper.generateHash(StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepent)+Float.toString(val)+seq);
	}
	
	public void genSignature(PrivateKey key) {
		String data = StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepent)+Float.toString(val);
		signature =  StringUtil.applyECDSASig(key,data);
	}
	
	public boolean verifySignature() {
		String data = StringUtil.getStringFromKey(sender)+StringUtil.getStringFromKey(reciepent)+Float.toString(val);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}

	
	
	
	
}
