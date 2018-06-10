package shootgame.gameobjects.enemies;

import shootgame.*;
import shootgame.gameobjects.GameObject;
import shootgame.gameobjects.Player;
import shootgame.gameobjects.Projectile;
import shootgame.gameobjects.projectiles.*;

import java.awt.*;
import java.util.Random;
/**
 * Boss:目前只把boss改了一张图片
 * 水平方向随机走动，会发射导弹，后续可以再添加新的武器以及实现一些AI
 * @author:panxinglu
 */
public class Boss extends Enemy {

	public static final int MAX_LIFE = 1000;
    private double velocityX = 5;
    private double velocityY = 0;
    private int life;   //命
    private long lastShotTime;  // 上一次射击的时间
      
    /** 初始化数据 */  
    public Boss(ShootGame game){
        super(game);
        image = ResourceManager.getImage("boss");
        width = 150;
		height =150;
        this.life = MAX_LIFE;
        y = height;
	    Random random = new Random();
	    x = random.nextInt(ShootGame.WIDTH - width);
	    this.random = (Math.random() > 0.5 ? -1 : 1);
        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
    }

    @Override
    public void update() {
		x += velocityX *random;
	    y += velocityY;
	    if(x > ShootGame.WIDTH-width){
	        velocityX = -2 * random;
	    }
	    if(x < 0){
	        velocityX = 2 * random;
	    }
	    
	    long shootInterval = 1500; // 射击间隔
	    if (game.currentTime - lastShotTime >= shootInterval) {
	    	game.addProjectiles(shootMissile());
	    	lastShotTime = game.currentTime;
	    }
	}

    /**爆炸，参数是生成爆炸的位置*/
	private Explosion[] explode(double x, double y){
	     Explosion [] explosion = new Explosion[1];
	     explosion[0] = new Explosion(game, x,y,this.velocityX,this.velocityY);
	     return  explosion;
	}

	private LargeExplosion[] explode(double x, double y, int width, int height) {
		LargeExplosion [] explosion = new LargeExplosion[1];
		explosion[0] = new LargeExplosion(game, x,y,this.velocityX,this.velocityY);
		explosion[0].scale(width, height);
		return explosion;
	}
      
	/**发射导弹*/
    private EnemyMissile[] shootMissile(){
		 int xStep = width / 4;    
	     int yStep = 52;
	     EnemyMissile[] enemyMissiles = new EnemyMissile[3];
	     enemyMissiles[0] = new EnemyMissile(game, x + 2*xStep,y+yStep);
		 enemyMissiles[1] = new EnemyMissile(game, x + 3*xStep,y+yStep*2);
		 enemyMissiles[2] = new EnemyMissile(game, x + 2*xStep,y+yStep*3);
	     return enemyMissiles;
	}
    
    @Override
    public void onCollision(GameObject other) {
		
		if (other instanceof Bullet) {//被子弹射到，由于图片原因，将y位置向后偏移，不然还没碰到物体就爆炸
			game.addProjectiles(explode(other.x,other.y-10));
			this.life -= ((Projectile) other).getDamage();
		} else if (other instanceof ForwardFire) {
			//game.addProjectiles(explode(other.x,other.y-10));
			this.life -= ((Projectile) other).getDamage();
		} else if(other instanceof Player) {
			game.addProjectiles(explode(other.x, other.y));
			this.life--;
		}
		if(this.life<=0){
			this.enabled=false;
			game.score += 100;
			game.addProjectiles(explode(x, y, width, height));
			game.bossDied = true;
			game.bossDiedTime = game.currentTime;
		}
	}
    
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY(), width,height, null);
		// 画血条
		g.setColor(Color.RED);
		g.fillRect(getX(), getY() - 5, width * life / MAX_LIFE, 5);
    }
	
}
