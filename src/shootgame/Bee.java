/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;
import java.util.Random;  
/**
 *
 * @author qinyuxuan
 */
public class Bee extends FlyingObject implements Award{  
    private int speed = 2;  //移动步骤 
    private int awardType;    //奖励类型  
      
    /** 初始化数据 */  
    public Bee(){  
        this.image = ShootGame.bee;  
        width = image.getWidth();  
        height = image.getHeight();  
        y = -height;  
        Random rand = new Random();  
        x = rand.nextInt(ShootGame.WIDTH - width);  
        awardType = rand.nextInt(2);   //初始化时给奖励  
    }  
      
    /** 获得奖励类型 */  
    public int getType(){  
        return awardType;  
    }  
  
    /** 越界处理 */  
    @Override  
    public boolean outOfBounds() {  
        return y>ShootGame.HEIGHT;  
    }  
  
    /** 移动，可斜着飞 */  
    @Override  
    public void step() {        
        y += speed;
    }  
}
