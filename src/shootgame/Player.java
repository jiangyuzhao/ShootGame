
package shootgame;

import java.awt.Graphics;

/**
 * 表示玩家的游戏物体
 *
 * @author qinyuxuan, hehao ,panxinglu
 */
public class Player extends GameObject {
    
    private double velocityX = 5;
    private double velocityY = 5;

    private int playerId; //标记是哪个玩家，支持多人游戏
    private int life;   //命
    private int doubleFire;
    private long lastShotTime;  // 上一次射击的时间
      
    /** 初始化数据 */  
    public Player(ShootGame game, int playerId){
        super(game);
        image = ResourceManager.getImage("player");
        width = 100;
		height =100;
        life = 100;
        doubleFire = 0;
        x = 300;  
        y = 500;

        collider = new PhysicsEngine.BoxCollider(this, x, y, width, height);
    }

    @Override
    public void update() {
        if (game.inputManager.getInput(InputManager.Key.UP)) {
            y -= velocityY;
        }
        if (game.inputManager.getInput(InputManager.Key.DOWN)) {
            y += velocityY;
        }
        if (game.inputManager.getInput(InputManager.Key.LEFT)) {
            x -= velocityX;
        }
        if (game.inputManager.getInput(InputManager.Key.RIGHT)) {
            x += velocityX;
        }
        
        /**按Z键发射一行子弹*/
        if (game.inputManager.getInput(InputManager.Key.Z)) {
        	game.addProjectiles(rowshoot());
        }
        
        /**按X键喷火*/
        if (game.inputManager.getInput(InputManager.Key.X)) {
        	game.addProjectiles(forwardfire());
        }
        
        // 检查玩家是否移出边界
        if (x <= 0) {
            x = 0;
        } else if ((x + width) >= ShootGame.WIDTH) {
            x = ShootGame.WIDTH - width;
        }
        if (y <= 0) {
            y = 0;
        } else if (y >= ShootGame.HEIGHT) {
            y = ShootGame.HEIGHT;
        }

        
        // 如果经过的射击间隔足够长，那么再次射击
        long shootInterval = 300; // 射击间隔
        if (game.currentTime - lastShotTime >= shootInterval) {
            game.addProjectiles(shoot());
            lastShotTime = game.currentTime;
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Enemy1) {
            this.life -= 1;
        }else if (other instanceof Enemy2) {
            this.life -= 2;
        }else if (other instanceof EnemyBullet) {
            this.life -= 1;
        }else if (other instanceof Missile) {
            this.life -= 2;
        }
    }
      
    /** 获取命 */  
    public int getLife(){  
        return life;
    }
  
    /** 发射子弹 */  
    private Bullet[] shoot(){
        int xStep = width / 4;      //4半
        int yStep = 30;  //步
        if (doubleFire > 0){  //双倍火力
            Bullet[] bullets = new Bullet[2];  
            bullets[0] = new Bullet(game, x + xStep,y - yStep);  //y-yStep(子弹距飞机的位置)
            bullets[1] = new Bullet(game, x + 3*xStep,y - yStep);
            return bullets;  
        } else {      //单倍火力
            Bullet[] bullets = new Bullet[1];  
            bullets[0] = new Bullet(game, x + 2*xStep,y - yStep);
            return bullets;  
        }  
    }
    
    /**发射一行子弹*/
    private OneRowBullet[] rowshoot(){
        OneRowBullet[] bullets = new OneRowBullet[1];  
        bullets[0] = new OneRowBullet(game, x ,y);
        return bullets;  
    }
    
    /**喷火*/
    private ForwardFire[] forwardfire(){
    	ForwardFire[] bullets = new ForwardFire[1];  
        bullets[0] = new ForwardFire(game, x ,y-120);
        return bullets;  
    }
    
    
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY(), width,height, null);
    }
}
