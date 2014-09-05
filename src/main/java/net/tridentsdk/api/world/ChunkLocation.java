package net.tridentsdk.api.world;


/**
 *  Stores the location of a Chunk
 * @author drew
 *
 */

public class ChunkLocation{

	private int x;
	private int z;
	
	public ChunkLocation(int x, int z){
		this.x = x;
		this.z = z;
	}
	
	public ChunkLocation(ChunkLocation coord){
		this.x = coord.getX();
		this.z = coord.getZ();
	}	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	
	public Chunk getChunk(){
		return null;
	}
	
}
