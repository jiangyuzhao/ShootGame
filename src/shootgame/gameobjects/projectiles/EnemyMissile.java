package shootgame.gameobjects.projectiles;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Player;
import shootgame.gameobjects.Projectile;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


/**
 * 导弹类，enemy3既会发射子弹，又会发射导弹，boss会发射导弹，这个类实现导弹
 *  @author panxinglu
 */
public class EnemyMissile extends Projectile {

	private double curAngle;
	private AffineTransform tx;
	int life = 5;	//这个导弹需要被子弹打多次才会消失
	public EnemyMissile(ShootGame game, double x, double y) {
		super(game);
		this.x = x;  
        this.y = y;  
        this.image = ResourceManager.getImage("missile");
        this.width = 20;
        this.height = 40;
		this.damage = 2;
		this.curAngle = 0;
		this.tx = new AffineTransform();
        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
		
        // 让导弹向着玩家方向前进
        double playerX = game.player.x;
        double playerY = game.player.y;
        // double vx = playerX - this.x;
        // double vy = playerY - this.y;
        // double len = Math.sqrt(vx*vx + vy*vy);
		// vx = 3 * vx / len;
		// vy = 3 * vy / len;
		this.velocityX = 0;
		this.velocityY = 3;
	}
	public void update() {
		double dx = (game.player.x - this.x);
		double dy = (game.player.y - this.y);

		if (dy > 0) {
			dy += Math.abs(dx);
			double len = Math.sqrt(dx * dx + dy * dy);
			this.velocityX = 4 * dx / len;
			this.velocityY = 4 * dy / len;
		}
		
		this.curAngle = Math.atan(this.velocityX / this.velocityY);
		this.x += this.velocityX;
	    this.y += this.velocityY;
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
	
	public static BufferedImage rotate(BufferedImage img, double arc) {  
		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage newImage = new BufferedImage(w, h, img.getType());
		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(-arc, w/2, h/2);  
		g2.drawImage(img,null,0,0);
		return newImage;  
	}

	public void render(Graphics g) {
		// tx.rotate(0);
		// AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		// BufferedImage result = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		// result = op.filter(image, null);

	    g.drawImage(rotate(image, curAngle), getX(), getY(), width, height, null);
	}
}
