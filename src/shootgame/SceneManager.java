package shootgame;

/**
 * 控制整个场景中敌人的生成，做这块的同学可以根据游戏类信息，设计成更多丰富多彩的敌人生成方式
 *
 * @author hehao
 */
public class SceneManager {
    private ShootGame game;

    public SceneManager(ShootGame game) {
        this.game = game;
    }

    private long lastSpawnedTime = 0;

    /**
     * 每帧此函数会被游戏调用，可以用来控制敌人的生成
     * 干一些控制敌人生成一类的事情
     */
    void update() {
        /*
         * 按理说敌人的位置也应该在这里控制生成的
         * 但是这部分代码目前在敌人类里
         * 请写敌人代码的同学决定应该如何做
         */
        Enemy[] enemies = new Enemy[1];
        if (game.currentTime - lastSpawnedTime > 500) {
            lastSpawnedTime = game.currentTime;
            enemies[0] = new Enemy(game);
            game.addEnemies(enemies);
        }
    }
}
