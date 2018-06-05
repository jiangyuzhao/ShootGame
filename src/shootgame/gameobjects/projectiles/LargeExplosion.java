package shootgame.gameobjects.projectiles;

import shootgame.gameobjects.Projectile;
import shootgame.ResourceManager;
import shootgame.ShootGame;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
 * 飞机和boss的爆炸效果
 * @author hehao
 */
public class LargeExplosion extends Projectile {

    private int lastTime = 60;	//爆炸持续时间

    public LargeExplosion(ShootGame game, double x, double y, double Vx, double Vy) {
        super(game);
        this.x = x;
        this.y = y;
        this.image = ResourceManager.getImage("explosion2");
        this.width = 40;
        this.height = 40;
        this.velocityX = Vx;
        this.velocityY = Vy;
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        this.lastTime--;
        if(this.lastTime<=0){
            this.enabled=false;
        }
    }

    public void render(Graphics g) {
        int frame = 15 - lastTime / 4;
        int row = frame / 4;
        int column = frame % 4;
        BufferedImage curr = this.image.getSubimage(128 * column, 128 * row, 128, 128);
        g.drawImage(curr, (int)x, (int)y, width, height, null);
    }

    public void scale(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
