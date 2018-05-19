/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;
import java.awt.image.BufferedImage;
/**
 *
 * @author qinyuxuan
 */
public abstract class FlyingObject {  
    public int x;    //x坐标  
    public int y;    //y坐标  
    public int width;    //宽  
    public int height;   //高  
    public BufferedImage image;   //图片  
  
    public int getX() {  
        return x;  
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return y;  
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
  
    /** 
     * 检查是否出界 
     * @return true 出界与否 
     */  
    public abstract boolean outOfBounds();  
      
    /** 
     * 飞行物移动一步 
     */  
    public abstract void step();  
      
    /** 
     * 检查当前飞行物体是否被子弹(x,y)击(shoot)中 
     * @param Bullet 子弹对象 
     * @return true表示被击中了 
     */  
    public boolean shootBy(Bullet bullet){  
        int x = bullet.x;  //子弹横坐标  
        int y = bullet.y;  //子弹纵坐标  
        return this.x<x && x<this.x+width && this.y<y && y<this.y+height;  
    }  
  
}
