package shootgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 爆炸效果，这里没有实现精灵动画，而是直接把一张爆炸的图片输出
 * @author panxinglu
 */
public class Explosion extends Projectile{
	
	int lastTime=15;	//爆炸持续时间
	Explosion(ShootGame game, double x, double y,double Vx,double Vy) {
		super(game);
		this.x = x;  
        this.y = y;  
        this.image = ResourceManager.getImage("explosion3");
        this.width = 30;	
        this.height = 30;	
        this.velocityX = Vx;
        this.velocityY = Vy;
        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
	}
	
	
	public void update() {
		x += velocityX;
	    y += velocityY;
	    this.lastTime--;
		if(this.lastTime<=0){
			this.enabled=false;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(this.image, getX(), getY(), width,height,null);
	}
}
