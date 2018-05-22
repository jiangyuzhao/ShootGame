
package shootgame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 最主要的游戏物体类，所有游戏内物体都会继承自此类
 * 编写新的GameObject，主要靠继承其update()，render()和onCollision()方法
 *
 * @author hehao
 */
public abstract class GameObject {
    protected ShootGame game; // 存储指向这个物体所属游戏类的指针
    protected boolean enabled;// 表示这个物体是否被设置为有效，如果无效则会在下一帧被删除
    protected double x;       // x坐标，采用double为了计算速度和碰撞更加精确，下同
    protected double y;       // y坐标
    protected int width;
    protected int height;
    protected BufferedImage image;   //图片

    GameObject(ShootGame game) {
        this.game = game;
    }

    /**
     * 在游戏主循环里，每一帧对每个游戏物体调用此方法，来更新游戏的状态
     * 因此对于新的游戏物体要表现不同行为的话，只需重载此方法即可
     */
    public void update() {
        // 故意留空
    }

    /**
     * 对游戏的画布的paint()方法会对每一个游戏物体调用此函数
     * 这个函数默认会画上一张图片
     * 如果要绘制更多特效的话请重载此函数
     *
     * @param g 用来绘图的Graphics对象
     */
    public void render(Graphics g) {
        g.drawImage(image, getX(), getY(), null);
    }

    /**
     * 物理引擎在检测到两个物体存在碰撞后，会调用这两个物体的onCollision函数
     * 重载这个函数如果你希望两个物体碰撞会发生一些游戏事件
     *
     * @param other
     */
    public void onCollision(GameObject other) {
        // 故意留空
    }

    public boolean isEnabled() { return enabled; }

    public int getX() {  
        return (int)x;
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return (int)y;
    }  
  
    public void setY(int y) {  
        this.y = y;
    }  
  
    public int getWidth() {  
        return width;  
    }  
  
    public void setWidth(int width) {  
        this.width = width;  
    }  
  
    public int getHeight() {  
        return height;  
    }  
  
    public void setHeight(int height) {  
        this.height = height;  
    }  
  
    public BufferedImage getImage() {  
        return image;  
    }  
  
    public void setImage(BufferedImage image) {  
        this.image = image;  
    }
  
}
