package shootgame.gameobjects.enemies;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Player;
import shootgame.gameobjects.projectiles.Bullet;
import shootgame.gameobjects.projectiles.Explosion;
import shootgame.gameobjects.projectiles.ForwardFire;
import shootgame.gameobjects.projectiles.OneRowBullet;

import java.awt.Graphics;
import java.util.Random;

/**
 * 第一种敌人：一枪就死，没有武器，体积小，速度快，碰到敌人自爆
 * @author panxinglu
 */
public class Enemy1 extends Enemy{
	
	public Enemy1(ShootGame game) {
		super(game);
		velocityX = 5;
		velocityY = 5;
		this.image = ResourceManager.getImage("enemy1");
		width = 50;	
		height = 50;
	    y = -height;	
	    Random random = new Random();
	    x = random.nextInt(ShootGame.WIDTH - width);
	    this.random = (Math.random() > 0.5 ? -1 : 1);
	    collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
	}
	
	@Override
	public void update() {
		x += velocityX * random;
	    y += velocityY;
	    if(x > ShootGame.WIDTH-width){
	        velocityX = -2 * random;
	    }
	    if(x < 0){
	        velocityX = 2 * random;
	    }
	}

	/**爆炸，参数是生成爆炸的位置*/
	private Explosion[] explode(double x, double y){
	     Explosion [] explosion = new Explosion[1];
	     explosion[0] = new Explosion(game, x,y,this.velocityX,this.velocityY);
	     return  explosion;
	}
	
	@Override
	public void onCollision(GameObject other) {
		if (other instanceof Bullet) {//遇到player射的子弹，爆炸位置设成子弹的位置，由于图片原因，y往后移一些
			game.addProjectiles(explode(other.x,other.y-50));
			this.enabled = false;
			game.score += 10;
		}
		else if(other instanceof Player){
			game.addProjectiles(explode(other.x,other.y));
			this.enabled = false;
		}else if(other instanceof OneRowBullet){//遇到
			game.addProjectiles(explode(x,y));
			this.enabled = false;
		}else if(other instanceof ForwardFire){
			this.enabled = false;
		}
	}
	
	 public void render(Graphics g) {
	        g.drawImage(image, getX(), getY(), width,height, null);
	 }
}
