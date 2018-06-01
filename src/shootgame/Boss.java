package shootgame;

import java.awt.Graphics;
import java.util.Random;
/**
 * Boss:目前只把boss改了一张图片
 * 水平方向随机走动，会发射导弹，后续可以再添加新的武器以及实现一些AI
 * @author:panxinglu
 */
public class Boss extends Enemy {
    
    private double velocityX = 5;
    private double velocityY = 0;
    private int life;   //命
    private long lastShotTime;  // 上一次射击的时间
      
    /** 初始化数据 */  
    public Boss(ShootGame game){
        super(game);
        image = ResourceManager.getImage("boss");
        width = 100;
		height =100;
        this.life = 10000;
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
	    
	    long shootInterval = 600; // 射击间隔
	    if (game.currentTime - lastShotTime >= shootInterval) {
	    	game.addProjectiles(shootMissile());
	    	lastShotTime = game.currentTime;
	    }
	}

    /**爆炸，参数是生成爆炸的位置*/
	private Explosion[] explode(double x,double y){
	     Explosion [] explosion = new Explosion[1];
	     explosion[0] = new Explosion(game, x,y,this.velocityX,this.velocityY);
	     return  explosion;
	}
      
	/**发射导弹*/
    private Missile[] shootMissile(){
		 int xStep = width / 4;    
	     int yStep = 104;  
	     Missile[] missiles = new Missile[1];  
	     missiles[0] = new Missile(game, x + 2*xStep,y+yStep);
	     return  missiles;  
	}
    
    @Override
    public void onCollision(GameObject other) {
		
		if (other instanceof Bullet) {//被子弹射到，由于图片原因，将y位置向后偏移，不然还没碰到物体就爆炸
			game.addProjectiles(explode(other.x,other.y-50));
			this.life--;
		}else if(other instanceof Player){
			game.addProjectiles(explode(other.x,other.y));
			this.life--;
		}else if(other instanceof OneRowBullet){
			game.addProjectiles(explode(x,y));
			this.life-=3;
		}else if(other instanceof ForwardFire){
			this.life-=2;
		}
		if(this.life<=0){
			this.enabled=false;
			game.score += 100;
		}
	}
    
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY(), width,height, null);
    }
	
}
