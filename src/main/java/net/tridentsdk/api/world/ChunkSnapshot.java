package net.tridentsdk.api.world;

import java.util.Collections;
import java.util.Map;

public class ChunkSnapshot {

	private final Map<ChunkLocation, Chunk> chunks;
	
	public ChunkSnapshot(Map<ChunkLocation, Chunk> snapshot){
		chunks = Collections.unmodifiableMap(snapshot);
	}
	
	public Chunk getChunkAt(ChunkLocation location){
		return chunks.get(location);
	}
	
	public Chunk getChunkAt(int x, int z){
		return this.getChunkAt(new ChunkLocation(x, z));
	}
	
}
