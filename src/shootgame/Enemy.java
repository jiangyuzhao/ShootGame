package shootgame;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 敌人类目前只有一个类，但是计划将其拓展成多种敌人类
 * 请负责这块的同学思考怎么拓展
 *
 * @author qinyuxuan, hehao
 */
public class Enemy extends GameObject {
    
    private double velocityX = 2;   //x坐标移动速度
    private double velocityY = 2;   //y坐标移动速度
    private int random;

    /** 初始化数据 */
    public Enemy(ShootGame game) {
        super(game);

        BufferedImage[] images = new BufferedImage[] {
                ShootGame.airplane,
                ShootGame.airplane0,
                ShootGame.airplane1,
                ShootGame.airplane2,
                ShootGame.airplane3,
                ShootGame.airplane4,
        };

        Random random = new Random();
        int s = random.nextInt(images.length);
        this.image = images[s];
        width = image.getWidth();
        height = image.getHeight();
        y = -height;

        Random rand = new Random();
        x = rand.nextInt(ShootGame.WIDTH - width);
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

    @Override
    public void onCollision(GameObject other) {
        if (other instanceof Projectile) {
            this.enabled = false;
            game.score += 10;
        }
    }
    
    /** 获取分数 */
    public int getScore() {  
        return 5;
    }

}
