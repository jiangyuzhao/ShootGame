/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shootgame;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.math.*;
import java.util.Random;
/**
 *
 * @author qinyuxuan
 */
public class Airplane extends FlyingObject implements Enemy {
    
    private int xSpeed = 2;   //x坐标移动速度  
    private int ySpeed = 2;   //y坐标移动速度  
    private int _random;
    
    private BufferedImage[] _images = {};
    /** 初始化数据 */
    public Airplane(){
        _images = new BufferedImage[]{ShootGame.airplane, ShootGame.airplane0,ShootGame.airplane1,ShootGame.airplane2,ShootGame.airplane3,ShootGame.airplane4}; //英雄机图片数组 
        Random random = new Random();
        int s = random.nextInt(_images.length);
        this.image = _images[s];
        width = image.getWidth();
        height = image.getHeight();
        y = -height;          
        Random rand = new Random();
        x = rand.nextInt(ShootGame.WIDTH - width);
        _random = (Math.random() > 0.5 ? -1 : 1);
    }
    
    /** 获取分数 */
    @Override
    public int getScore() {  
        return 5;
    }

    /** //越界处理 */
    @Override
    public  boolean outOfBounds() {   
        return y>ShootGame.HEIGHT;
    }

    /** 移动 */
    @Override
    public void step() {   
        
        x += xSpeed * _random;  
        y += ySpeed;  
        if(x > ShootGame.WIDTH-width){    
            xSpeed = -2 * _random;  
        }  
        if(x < 0){  
            xSpeed = 2 * _random;  
        }  
    }

}
