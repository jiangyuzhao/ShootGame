package shootgame;

import java.awt.Graphics;
import java.util.Random;
/**
 * 第三种敌人：血量较高，既会射子弹又会射导弹，体积较大，速度较慢
 * 只会在上方动，碰到玩家什么也不会发生
 * @author:panxinglu
 */
public class Enemy3 extends Enemy{
	private int life = 15;
	private long lastShotTime;
	private int emit=0;//发射子弹还是导弹
	public Enemy3(ShootGame game) {
		super(game);
		velocityX = 1;
		velocityY = 0;
		this.image = ResourceManager.getImage("enemy3");
		width = 140;
		height =140;
	    y = height;
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
	    
	    long shootInterval = 600; // 射击间隔
	    if (game.currentTime - lastShotTime >= shootInterval) {//目前射两个子弹后射一个导弹
	    	emit=(emit+1)%3;
	    	if(emit%3==0)
	    		game.addProjectiles(shootMissile());
	    	else
	    		game.addProjectiles(shootBullet());
	    	lastShotTime = game.currentTime;
	    }
	}
	
	/**射子弹*/
	private EnemyBullet[] shootBullet(){
		 int xStep = width / 4;   
	     int yStep = 104; 
	     EnemyBullet[] bullets = new EnemyBullet[1];  
	     bullets[0] = new EnemyBullet(game, x+xStep,y+yStep);
	     return bullets;  
    }
	
	/**射导弹*/
	private Missile[] shootMissile(){
		 int xStep = width / 4;  
	     int yStep = 104; 
	     Missile[] missiles = new Missile[1];  
	     missiles[0] = new Missile(game, x + 2*xStep,y+yStep);
	     return  missiles;  
	}
	
	/**爆炸，参数是生成爆炸的位置*/
	private Explosion[] explode(double x,double y){
	     Explosion [] explosion = new Explosion[1];
	     explosion[0] = new Explosion(game, x,y,this.velocityX,this.velocityY);
	     return  explosion;
	}

	public void onCollision(GameObject other) {
		if (other instanceof Bullet) {//遇到player射的子弹，爆炸位置设成子弹的位置，由于图片原因，y往后移一些
			game.addProjectiles(explode(other.x,other.y-50));
			this.life--;
			if(this.life<=0){
				this.enabled=false;
				game.score += 20;
			}
		}else if(other instanceof Player){
			game.addProjectiles(explode(other.x,other.y));
			this.enabled = false;
		}else if(other instanceof OneRowBullet){
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
