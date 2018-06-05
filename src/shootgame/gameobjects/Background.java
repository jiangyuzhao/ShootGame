package shootgame.gameobjects;

import shootgame.ResourceManager;
import shootgame.ShootGame;
import shootgame.gameobjects.GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 实现一个每一帧会移动的背景图片
 *
 * @author hehao
 */
public class Background extends GameObject {

    private BufferedImage background;
    // 画三张图片实现无缝对接
    private int y1, y2, y3;
    private int speed = 2;

    public Background(ShootGame game) {
        super(game);

        background = ResourceManager.getImage("background1");
        width = game.getWidth();
        height = background.getHeight();
        y1 = 0;
        y2 = y1 + background.getHeight();
        y3 = y1 - background.getHeight();
    }

    @Override
    public void update() {
        y1 += speed;
        y2 += speed;
        y3 += speed;
        if (y3 >= 0) {
            y1 = 0;
            y2 = y1 + background.getHeight();
            y3 = y1 - background.getHeight();
        }
    }

    @Override
    public void render(Graphics g) {
        width = game.getWidth();

        g.drawImage(background, 0, y1, width, height, null);
        g.drawImage(background, 0, y2, width, height, null);
        g.drawImage(background, 0, y3, width, height, null);
    }

}
