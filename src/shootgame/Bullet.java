package shootgame;

/**
 * 这是一种由玩家射出的最普通子弹
 *
 * @author qinyuxuan, hehao
 */
public class Bullet extends Projectile {
      
    /** 初始化数据 */  
    public Bullet(ShootGame game, double x, double y){
        super(game);

        this.x = x;  
        this.y = y;  
        this.image = ShootGame.bullet;

        this.velocityY = -3;
    }

    public Bullet(ShootGame game, double x, double y, boolean b){
        super(game);

        this.x = x;  
        this.y = y;  
        if(b) {
            this.image = ShootGame.bullet1;
        }

        this.velocityY = -3;
    }

    @Override
    public void update() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public void onCollision(GameObject other) {

    }
}
