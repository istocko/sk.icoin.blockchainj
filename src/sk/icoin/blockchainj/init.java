package sk.icoin.blockchainj;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class init {
	
	public static void main(String[] args) {
		System.out.println("init blockchainj");
		
		Security.addProvider(new BouncyCastleProvider());
		Wallet walletA = new Wallet();
		Wallet walletB = new Wallet();
		
		BlockChain blockChain = new BlockChain();
		
		Transaction tx = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		tx.genSignature(walletA.privateKey);
		System.out.println("is sig for tx valid? "+tx.verifySignature());
		
		Miner miner = new Miner();
		Block block0 = new Block(0,"transaction1",Constants.GENESIS_PREV_HASH);
		miner.mine(block0, blockChain);
		Block block1 = new Block(1,"transaction2",blockChain.getBlockChain().get(blockChain.size()-1).getHash());
		miner.mine(block1, blockChain);
		Block block2 = new Block(2,"transaction3",blockChain.getBlockChain().get(blockChain.size()-1).getHash());
		miner.mine(block2, blockChain);
		boolean valid = blockChain.isChainValid();
		System.out.println("\n"+"Blockchain valid? "+valid);
		System.out.println("\n"+"Blockchain:\n"+blockChain);
		System.out.println("Miner's reward: "+miner.getReward());
	}

}
