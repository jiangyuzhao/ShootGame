package shootgame.gameobjects.projectiles;

import shootgame.gameobjects.Projectile;
import shootgame.ResourceManager;
import shootgame.ShootGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 爆炸效果
 * @author panxinglu
 */
public class Explosion extends Projectile {
	
	int lastTime=45;	//爆炸持续时间

	public Explosion(ShootGame game, double x, double y, double Vx, double Vy) {
		super(game);
		this.x = x;  
        this.y = y;  
        this.image = ResourceManager.getImage("explosion1");
        this.width = 40;
        this.height = 40;
        this.velocityX = Vx;
        this.velocityY = Vy;
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
		int frame = 15 - lastTime / 3;
		int row = frame / 4;
		int column = frame % 4;
		BufferedImage curr = this.image.getSubimage(64 * column, 64 * row, 64, 64);
		g.drawImage(curr, (int)x, (int)y, width, height, null);
	}

	public void scale(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
