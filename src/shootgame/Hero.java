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
public class Hero extends FlyingObject{  
    
    public int xspeed = 2;
    public int yspeed = 1;
    public double xdirection = 0; //向右 -1向左
    public double ydirection = 0; //向上 1向下
    private BufferedImage[] images = {};  //英雄机图片  
    private int index = 0;                //英雄机图片切换索引  
      
    private int doubleFire;   //双倍火力  
    private int fourfire; //四倍火力
    
    private int life;   //命  
      
    /** 初始化数据 */  
    public Hero(){  
        life = 10;   //初始3条命  
        doubleFire = 0;   //初始火力为0  
        fourfire = 0; //初始没放大招
        //images = new BufferedImage[]{ShootGame.hero0, ShootGame.hero1}; //英雄机图片数组  
        image = ShootGame.hero0;   //初始为hero0图片  
        width = image.getWidth();  
        height = image.getHeight();  
        x = 300;  
        y = 500;  
    }  
      
    /** 获取双倍火力 */  
    public int isDoubleFire() {  
        return doubleFire;  
    }  
  
    /** 设置双倍火力 */  
    public void setDoubleFire(int doubleFire) {  
        this.doubleFire = doubleFire;  
    }  
      
    /** 获取4倍火力 */  
    public int isfourFire() {  
        return fourfire;  
    }  
  
    /** 设置4倍火力 */  
    public void setfourFire(int fourFire) {  
        this.fourfire = fourFire;  
    }
    
    /** 增加火力 */  
    public void addDoubleFire(){  
        doubleFire = 40;  
    }  
      
    /** 增命 */  
    public void addLife(){  //增命  
        life++;  
    }  
      
    /** 减命 */  
    public void subtractLife(){   //减命  
        life--;  
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
  
    /** 越界处理 */  
    @Override  
    public boolean outOfBounds() {  
        return false;    
    }  
  
    /** 发射子弹 */  
    public Bullet[] shoot(){     
        int xStep = width/4;      //4半  
        int yStep = 20;  //步  
        if(fourfire>0){
            Bullet[] bullets = new Bullet[20]; 
            int width = ShootGame.WIDTH;
            for(int i = 0; i < 20; ++i){
                bullets[i] = new Bullet(width*(i+1)/22, y-yStep, true);
            }
            return bullets;
        }
        else if(doubleFire>0){  //双倍火力  
            Bullet[] bullets = new Bullet[2];  
            bullets[0] = new Bullet(x+xStep,y-yStep);  //y-yStep(子弹距飞机的位置)  
            bullets[1] = new Bullet(x+3*xStep,y-yStep);  
            return bullets;  
        }else{      //单倍火力  
            Bullet[] bullets = new Bullet[1];  
            bullets[0] = new Bullet(x+2*xStep,y-yStep);    
            return bullets;  
        }  
    }  
  
    /** 移动 */  
    @Override  
    public void step() {  
        x += (int)((double)xspeed * xdirection);
        y += (int)((double)yspeed * ydirection);
        if(x<=0)
            x = 0;
        else if((x+width)>=ShootGame.WIDTH)
            x = ShootGame.WIDTH - width;
        if(y<=(ShootGame.HEIGHT>>1))
            y = (ShootGame.HEIGHT>>1);
        else if(y>=600)
            y = 600;
    }  
      
    /** 碰撞算法 */  
    public boolean hit(FlyingObject other){  
          
        int x1 = other.x - this.width/2;                 //x坐标最小距离  
        int x2 = other.x + this.width/2 + other.width;   //x坐标最大距离  
        int y1 = other.y - this.height/2;                //y坐标最小距离  
        int y2 = other.y + this.height/2 + other.height; //y坐标最大距离  
      
        int herox = this.x + this.width/2;               //英雄机x坐标中心点距离  
        int heroy = this.y + this.height/2;              //英雄机y坐标中心点距离  
          
        return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;   //区间范围内为撞上了  
    }  
      
}
