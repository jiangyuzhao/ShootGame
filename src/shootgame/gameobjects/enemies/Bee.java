package shootgame.gameobjects.enemies;

import shootgame.gameobjects.GameObject;
import shootgame.ResourceManager;
import shootgame.ShootGame;

import java.util.Random;

/**
 *
 * @author qinyuxuan, hehao
 */
public class Bee extends GameObject {
    private int speed = 2;  //移动步骤 
    private int awardType;    //奖励类型  
      
    /** 初始化数据 */  
    public Bee(ShootGame game) {
        super(game);
        this.image = ResourceManager.getImage("bee");
        width = image.getWidth();  
        height = image.getHeight();  
        y = -height;
        Random rand = new Random();  
        x = rand.nextInt(ShootGame.WIDTH - width);  
        awardType = rand.nextInt(2);   //初始化时给奖励  
    }
      
    /** 获得奖励类型 */
    public int getType() {
        return awardType;  
    }
  
    /** 移动，可斜着飞 */  
    @Override  
    public void update() {
        y += speed;
    }  
}
