/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;

/**
 *
 * @author qinyuxuan
 */
public class Bullet extends FlyingObject {  
    private int speed = 3;  //移动的速度  
      
    /** 初始化数据 */  
    public Bullet(int x,int y){  
        this.x = x;  
        this.y = y;  
        this.image = ShootGame.bullet;  
    } 
    public Bullet(int x,int y, boolean b){  
        this.x = x;  
        this.y = y;  
        if(b==true)
            this.image = ShootGame.bullet1;  
    } 
  
    /** 移动 */  
    @Override  
    public void step(){     
        y-=speed;  
    }  
  
    /** 越界处理 */  
    @Override  
    public boolean outOfBounds() {  
        return y<-height;  
    }  
  
}
