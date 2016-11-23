package com.zigabyte.polygondefense.level;

import java.util.ArrayList;

import com.zigabyte.polygondefense.entities.Entity;
import com.zigabyte.polygondefense.entities.mobs.Mob;

public class Wave {

	public static int spawns[][] = {
			{3},
			{5},
			{10},
			{15}};
	
	private Level level;
	public final int INDEX;
	
	private boolean spawned = false;
	
	private ArrayList<Entity> mobs;
	
	public Wave(int index, Level level){
		this.level = level;
		
		// Check if index is in bounds
		if(index >= spawns.length){
			index = spawns.length - 1;
		}
		
		this.INDEX = index;

		// Generate mobs
		generateMobs();
	}

	/**
	 * Spawns the mobs
	 * */
	public void spawnMobs(){
		spawned = true;
		level.addEntities(mobs);;
	}
	
	/**
	 * Generates the array of mobs to spawn
	 * */
	private void generateMobs(){
		mobs = new ArrayList<Entity>();
		
		int n = spawns[INDEX][0];
		for(int i = 0; i < n; i++){
			// Add a mob at the start location and move it left
			mobs.add(new Mob(level, level.start.getCenter().add(- 100 - i * 20, 0)));
		}
	}
	
	/**
	 * Also implement a timing of the wave
	 * */
	public boolean hasEnded(){
		for(Entity e : mobs){
			Mob m = (Mob)e;
			if(!m.dead)	return false;
		}
		return true;
	}

	/**
	 * Checks if the mobs have been spawned yet
	 * */
	public boolean hasStarted() {
		return spawned;
	}
}
