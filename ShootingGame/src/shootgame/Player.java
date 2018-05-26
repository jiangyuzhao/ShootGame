
package shootgame;

/**
 * 表示玩家的游戏物体
 *
 * @author qinyuxuan, hehao
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

        image = ResourceManager.getImage("hero0");
        width = image.getWidth();
        height = image.getHeight();

        life = 10;
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

        // 检查玩家是否移出边界
        if (x <= 0) {
            x = 0;
        } else if ((x + width) >= ShootGame.WIDTH) {
            x = ShootGame.WIDTH - width;
        }
        if (y <= 0) {
            y = 0;
        } else if (y >= 600) {
            y = 600;
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
        if (other instanceof Enemy) {
            this.life -= 1;
            other.enabled = false;
        }
    }
      
    /** 获取命 */  
    public int getLife(){  
        return life;
    }
  
    /** 发射子弹 */  
    private Bullet[] shoot(){
        int xStep = width / 4;      //4半
        int yStep = 20;  //步
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
}
