package shootgame.gameobjects.projectiles;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Player;
import shootgame.gameobjects.Projectile;

/**
 * 这是由敌人射出的子弹
 * @author:panxinglu
 */

public class EnemyBullet extends Projectile {

  public  EnemyBullet(ShootGame game, double x, double y){
      super(game);
      this.x = x;  
      this.y = y;  
      this.image = ResourceManager.getImage("bullet1");
      this.width = this.image.getWidth();
      this.height = this.image.getHeight();
      this.velocityY = 3;
      this.damage = 1;

      collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
  }

  @Override
  public void update() {
      x += velocityX;
      y += velocityY;
  }

  @Override
  public void onCollision(GameObject other) {
      if(other instanceof Player){
      	this.enabled = false;
      }else if(other instanceof Bullet){
      	this.enabled = false;
      }else if(other instanceof OneRowBullet){
          this.enabled = false;
	  }
  }
}
