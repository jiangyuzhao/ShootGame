
package shootgame;

/**
 * 表示玩家的游戏物体
 *
 * @author qinyuxuan, hehao
 */
public class Player extends GameObject {
    
    private double velocityX;
    private double velocityY;
    
    private int life;   //命
    private int doubleFire;
    private long lastShotTime;  // 上一次射击的时间
    private long shootInterval = 500; // 射击间隔
      
    /** 初始化数据 */  
    public Player(ShootGame game){
        super(game);

        image = ShootGame.hero0;
        width = image.getWidth();
        height = image.getHeight();

        life = 10;
        doubleFire = 0;
        x = 300;  
        y = 500;  
    }

    @Override
    public void update() {

        x += velocityX;
        y += velocityY;
        // 检查玩家是否移出边界
        if (x <= 0) {
            x = 0;
        } else if ((x + width) >= ShootGame.WIDTH) {
            x = ShootGame.WIDTH - width;
        }
        if (y <= (ShootGame.HEIGHT >> 1)) {
            y = (ShootGame.HEIGHT >> 1);
        } else if (y >= 600) {
            y = 600;
        }

        // 如果经过的射击间隔足够长，那么再次射击
        if (game.currentTime - lastShotTime >= shootInterval) {
            game.addProjectiles(shoot());
            lastShotTime = game.currentTime;
        }
    }

    @Override
    public void onCollision(GameObject other) {
        // TODO
    }
      
    /** 获取命 */  
    public int getLife(){  
        return life;
    }  
      
    /** 当前物体移动了一下，相对距离，x,y鼠标位置  */  
    public void moveTo(int x,int y){     
        //this.x = x - width/2;  
        //this.y = y - height/2;  
        this.x = x;
        this.y = y;
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
