package shootgame;

import java.awt.Graphics;
/**
 * 这个类实现player发射一行子弹
 * @author:panxinglu
 *
 */
public class OneRowBullet extends Projectile {
    
  /** 初始化数据 */  
	
  public OneRowBullet(ShootGame game,double x,double y){
      super(game);
      this.image = ResourceManager.getImage("manybullet");
      this.width = game.WIDTH-10;
      this.height = 10;
      this.velocityY = -3;
      this.x=5;
      this.y=y;
      collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
  }
  
  @Override
  public void update() {
      x += velocityX;
      y += velocityY;
  }

  @Override
  public void onCollision(GameObject other) {
	  
  }
  public void render(Graphics g) {
      g.drawImage(image, getX(), getY(), width,height, null);
  }
  
}
