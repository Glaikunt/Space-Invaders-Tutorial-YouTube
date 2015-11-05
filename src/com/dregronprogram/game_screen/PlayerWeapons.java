package com.dregronprogram.game_screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.dregronprogram.explosion.ExplosionManager;
import com.dregronprogram.player_bullets.MachineGun;
import com.dregronprogram.player_bullets.PlayerWeaponType;
import com.dregronprogram.timer.Timer;

public class PlayerWeapons {

	private Timer timer;
	private ExplosionManager explosionManager;
	public ArrayList<PlayerWeaponType> weapons = new ArrayList<PlayerWeaponType>();
	
	public PlayerWeapons(){
		explosionManager = new ExplosionManager();
		timer = new Timer();
	}
	
	public void draw(Graphics2D g){
		
		explosionManager.draw(g);
		for(int i = 0; i < weapons.size(); i++){
			weapons.get(i).draw(g);
		}
	}
	
	public void update(double delta, BasicBlocks blocks){
		
		explosionManager.update(delta);
		for(int i = 0; i < weapons.size(); i++){
			weapons.get(i).update(delta, blocks);
			if(weapons.get(i).destory()) {
				ExplosionManager.createPixelExplosion(weapons.get(i).getxPos(), weapons.get(i).getyPos());
				weapons.remove(i);
			}
		}
	}
	
	public void shootBullet(double xPos, double yPos, int width, int height){
		if(timer.timerEvent(250) && weapons.size() < 3) {
			weapons.add(new MachineGun(xPos + 22, yPos + 15, width, height));
		}
	}
}
