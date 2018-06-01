package shootgame;

import java.awt.Graphics;

/**
 * 这个类实现玩家可以在前面比较远的地方喷火的功能
 * @author：panxinglu
 */
public class ForwardFire extends Projectile {
	
  public ForwardFire(ShootGame game, double x, double y){
      super(game);
      this.x = x;  
      this.y = y;  
      this.image = ResourceManager.getImage("explosion3");
      this.width = this.image.getWidth();
      this.height = this.image.getHeight();

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
    
  }
  
  public void render(Graphics g) {
      g.drawImage(image, getX(), getY(), width,height, null);
  }
}
