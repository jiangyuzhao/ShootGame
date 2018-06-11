package shootgame;
import shootgame.gameobjects.enemies.*;

import java.util.Random;
/**
 * 控制整个场景中敌人的生成，做这块的同学可以根据游戏类信息，设计成更多丰富多彩的敌人生成方式
 *
 * @author hehao panxinglu
 */
public class SceneManager {
    private ShootGame game;

    public SceneManager(ShootGame game) {
        this.game = game;
    }

    private long lastSpawnedTime = 0;
    private long timeToSeeBoss = 5000;//离boss出现的时间
    /**
     * 每帧此函数会被游戏调用，可以用来控制敌人的生成
     * 干一些控制敌人生成一类的事情
     */
    boolean isFinal=false;//是否到了和boss对决的时候

    public void init() {
        isFinal = false;
        lastSpawnedTime = 0;
        timeToSeeBoss = 5000;
    }
    
    /*
     * 目前先随机生成enemy1,enemy2,enemy3
     * 一定时间后生成boss，不再生成其他敌人
     * 做场景设计的后续可以加以修改
     */
    public void update() {
    	if(isFinal)return;
        Enemy[] enemies = new Enemy[1];
        timeToSeeBoss--;
        if(timeToSeeBoss<=0){
        	isFinal=true;
            enemies[0] = new Boss(game);
            game.addEnemies(enemies);
            return;
        }
        
        if (game.currentTime - lastSpawnedTime > 1500) {
            lastSpawnedTime = game.currentTime;
            Random rand = new Random();
            int type = 5000 + rand.nextInt(10000) - (int)timeToSeeBoss;
            if(type<5000){
            	enemies[0] = new Enemy1(game);
            }else if(type<10000){
            	enemies[0] = new Enemy2(game);
            }else if(type<15000){
            	enemies[0] = new Enemy3(game);
            }
            game.addEnemies(enemies);
        }
    }
}
