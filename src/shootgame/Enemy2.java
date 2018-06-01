package shootgame;

import java.awt.Graphics;
import java.util.Random;
/**
 * 第二种敌人：血量适中，会隔一段时间射出一个子弹，体积中等，速度中等
 * 碰到玩家会自爆并赋予伤害
 * @author:panxinglu
 */
public class Enemy2 extends Enemy{
	private int life = 5;
	private long lastShotTime = 0;
	public Enemy2(ShootGame game) {
		super(game);
		velocityX = 3;
		velocityY = 3;
		this.image = ResourceManager.getImage("enemy2");
		width = 100;
		height =100;
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
	    
	    long shootInterval = 600; // 射击间隔
	    if (game.currentTime - lastShotTime >= shootInterval) {
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
				game.score += 15;
			}
		}
		else if(other instanceof Player){
			game.addProjectiles(explode(other.x,other.y));
			this.enabled = false;;
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
