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

    void spawnEnemies() {
        Enemy[] enemies = new Enemy[1];
        if (game.currentTime - lastSpawnedTime > 500) {
            lastSpawnedTime = game.currentTime;
            enemies[0] = new Enemy(game);
            game.addEnemies(enemies);
        }
    }
}
