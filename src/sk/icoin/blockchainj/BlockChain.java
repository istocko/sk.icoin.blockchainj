package sk.icoin.blockchainj;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
	private List<Block> blockChain;
	
	public BlockChain() {
		this.blockChain = new ArrayList<>();
	}
	
	public void addBlock(Block block) {
		this.blockChain.add(block);
	}
	
	public List<Block> getBlockChain() {
		return this.blockChain;
	}
	
	public int size() {
		return this.blockChain.size();
	}
	
	public String toString() {
		String blockChain = "";
		for(Block block : this.blockChain) {
			blockChain += block.toString()+"\n";
		}
		return blockChain;
			
	}
	
	public boolean isChainValid() {
		Block currentBlock;
		Block prevBlock;
		String hashTarget = new String(new char[Constants.DIFFICULTY]).replace('\0', '0');
		
		for (int i=1; i < blockChain.size(); i++) { 
			currentBlock = blockChain.get(i);
			prevBlock = blockChain.get(i-1);
			
			
			if (!currentBlock.getHash().equals(currentBlock.generateHash())) {
				System.out.println("Current Hashes not equal");	
				return false;
			}
			if (!prevBlock.getHash().equals(currentBlock.getPrevHash() )) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			if (!currentBlock.getHash().substring(0,Constants.DIFFICULTY).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
		
	}
}
