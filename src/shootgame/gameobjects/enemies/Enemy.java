package shootgame.gameobjects.enemies;

import shootgame.gameobjects.GameObject;
import shootgame.ShootGame;

/**
 * 敌人类目前只有一个类，但是计划将其拓展成多种敌人类
 * 请负责这块的同学思考怎么拓展
 *
 * @author qinyuxuan, hehao
 */
public class Enemy extends GameObject {
    
    protected double velocityX ;   //x坐标移动速度
    protected double velocityY ;   //y坐标移动速度
    protected int random;

    /** 初始化数据 */
    public Enemy(ShootGame game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(GameObject other) {
    	
    }
    
    /** 获取分数 */
    public int getScore() {  
        return 5;
    }
}

