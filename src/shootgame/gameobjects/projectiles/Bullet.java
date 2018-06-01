package shootgame.gameobjects.projectiles;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Projectile;
import shootgame.gameobjects.enemies.Enemy;

/**
 * 这是一种由玩家射出的最普通子弹
 *
 * @author qinyuxuan, hehao
 */
public class Bullet extends Projectile {
      
    /** 初始化数据 */  
	
    public Bullet(ShootGame game, double x, double y){
        super(game);

        this.x = x;  
        this.y = y;  
        this.image = ResourceManager.getImage("bullet0");
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.damage = 1;
        this.velocityY = -3;

        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
    }
    
    @Override
    public void update() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public void onCollision(GameObject other) {
        if(other instanceof EnemyBullet){
        	this.enabled = false;
        }else if(other instanceof Enemy){
        	this.enabled = false;
        }else if(other instanceof EnemyMissile){
        	this.enabled = false;
        }
    }
}
