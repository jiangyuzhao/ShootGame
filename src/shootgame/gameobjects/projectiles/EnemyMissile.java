package shootgame.gameobjects.projectiles;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Player;
import shootgame.gameobjects.Projectile;

import java.awt.Graphics;

/**
 * 导弹类，enemy3既会发射子弹，又会发射导弹，boss会发射导弹，这个类实现导弹
 *  @author panxinglu
 */
public class EnemyMissile extends Projectile {

	int life = 5;	//这个导弹需要被子弹打多次才会消失
	public EnemyMissile(ShootGame game, double x, double y) {
		super(game);
		this.x = x;  
        this.y = y;  
        this.image = ResourceManager.getImage("missile");
        this.width = 20;
        this.height = 40;
        this.damage = 2;
        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);

        // 让导弹向着玩家方向前进
        double playerX = game.player.x;
        double playerY = game.player.y;
        double vx = playerX - this.x;
        double vy = playerY - this.y;
        double len = Math.sqrt(vx*vx + vy*vy);
		vx = 3 * vx / len;
		vy = 3 * vy / len;
		this.velocityX = vx;
		this.velocityY = vy;
	}
	public void update() {
		x += velocityX;
	    y += velocityY;
	}

	@Override
	public void onCollision(GameObject other) {
	    if (other instanceof Player){
	    	this.enabled = false;
	    }
	    else if(other instanceof Bullet){
	    	this.life--;
	    	if(this.life<=0){
	    		 this.enabled = false;
	    	}
	    }else if(other instanceof OneRowBullet){
			this.enabled = false;
		}
	}
	
	public void render(Graphics g) {
	    g.drawImage(image, getX(), getY(), width,height, null);
	}
}
