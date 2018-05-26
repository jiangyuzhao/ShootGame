package shootgame;

import java.awt.Font;  
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.lang.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 这是整个游戏的核心类
 *
 * @author qinyuxuan, hehao
 */
public class ShootGame extends JPanel {
    /**
     * 窗体的大小
     */
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    /**
     * 游戏的当前状态: START RUNNING PAUSE GAME_OVER
     */
    private static final int START = 0;  
    private static final int RUNNING = 1;  
    private static final int PAUSE = 2;  
    private static final int GAME_OVER = 3;

    // 资源图片
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    static {
        start = ResourceManager.getImage("start");
        pause = ResourceManager.getImage("pause");
        gameover = ResourceManager.getImage("gameover");
    }

    private int state;
    public int score = 0; // 得分

    private Timer timer; // 定时器
    private int interval = 10; // 时间间隔(毫秒)
    public long lastUpdatedTime;
    public long currentTime;
    public long deltaTime;


    public Background background = new Background(this);

    // 管理敌人的生成，道具的生成等操作
    public SceneManager sceneManager = new SceneManager(this);

    // 负责处理用户的输入
    public InputManager inputManager = new InputManager(this);

    // 负责处理物理
    public PhysicsEngine physicsEngine = new PhysicsEngine(this);

    public Projectile[] projectiles = new Projectile[1000]; // 子弹数组
    public int projectilesLastEmptyPosition; //记录数组留空的位置
    public Enemy[] enemies = new Enemy[100]; // 敌人数组
    public int enemiesLastEmptyPosition; //记录敌人数组留空的位置
    public Player player = new Player(this, 0); // 玩家

    public static void main(String[] args) {
        JFrame frame = new JFrame("Come on! Avangers!");
        ShootGame game = new ShootGame(); // 面板对象
        frame.add(game); // 将面板添加到JFrame中
        frame.setSize(WIDTH, HEIGHT); // 设置大小
        frame.requestFocus(); // 让键盘事件有效
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 默认关闭操作
        frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
        frame.setLocationRelativeTo(null); // 设置窗体初始位置
        frame.setVisible(true); // 尽快调用paint

        game.start(); // 启动执行
    }

    /** 启动执行代码 */
    public void start() {

        // 鼠标监听事件
        MouseAdapter l = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) { // 鼠标进入
                if (state == PAUSE) { // 暂停状态下运行
                    state = RUNNING;
                    ShootGame.this.requestFocus();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) { // 鼠标退出
                if (state == RUNNING) { // 游戏未结束，则设置其为暂停
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) { // 鼠标点击
                switch (state) {
                    case START:
                        state = RUNNING; // 启动状态下运行
                        ShootGame.this.requestFocus();
                        break;
                    case GAME_OVER: // 游戏结束，清理现场
                        enemies = new Enemy[0]; // 清空飞行物
                        projectiles = new Projectile[0]; // 清空子弹
                        player = new Player(ShootGame.this, 0); // 重新创建英雄机
                        score = 0; // 清空成绩
                        state = START; // 状态设置为启动
                        break;
                }
            }
        };

        this.addMouseListener(l); // 处理鼠标点击操作
        this.addMouseMotionListener(l); // 处理鼠标滑动操作

        timer = new Timer(); // 主流程控制
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 更新时序信息
                lastUpdatedTime = currentTime;
                currentTime = new Date().getTime();
                deltaTime = currentTime - lastUpdatedTime;

                if (state == RUNNING) { // 运行状态
                    // 移动背景
                    background.update();

                    // 先刷新敌人
                    sceneManager.update();

                    // 依次更新投射物，敌人和玩家的状态
                    for (Projectile proj : projectiles)
                        if (proj != null) proj.update();
                    for (Enemy enemy : enemies)
                        if (enemy != null) enemy.update();
                    player.update();

                    physicsEngine.detectCollision(); // 物理引擎检测碰撞

                    garbageCollection(); // 删除越界飞行物，死亡飞行物及子弹

                    checkGameOver(); // 检查游戏结束
                }
                repaint(); // 重绘，调用paint()方法
            }

        }, interval, interval);
    }

    /** 画 */  
    @Override  
    public void paint(Graphics g) {
        background.render(g);

        // 依次绘制投射物，敌人和玩家
        for (Projectile proj : projectiles) {
            if (proj != null && proj.enabled) proj.render(g);
        }
        for (Enemy enemy : enemies) {
            if (enemy != null && enemy.enabled) enemy.render(g);
        }
        player.render(g);

        paintScore(g); // 画分数  
        paintState(g); // 画游戏状态  
    }

    /** 画分数 */  
    private void paintScore(Graphics g) {
        int x = 10; // x坐标  
        int y = 25; // y坐标  
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // 字体  
        g.setColor(new Color(0xFF0000));  
        g.setFont(font); // 设置字体  
        g.drawString("SCORE:" + score, x, y); // 画分数  
        y=y+20; // y坐标增20  
        g.drawString("LIFE:" + player.getLife(), x, y); // 画命
    }  
  
    /** 画游戏状态 */  
    private void paintState(Graphics g) {
        switch (state) {  
        case START: // 启动状态  
            g.drawImage(start, 0, 0, null);  
            break;  
        case PAUSE: // 暂停状态  
            g.drawImage(pause, 0, 0, null);  
            break;  
        case GAME_OVER: // 游戏终止状态  
            g.drawImage(gameover, 0, 0, null);  
            break;  
        }  
    }

    /**
     * 在场景中增加一些子弹，但是可能出现数组满的罕见情况，此时返回false
     * @param newProjectiles 要增加的子弹数组
     * @return 如果添加成功返回true, 否则false
     */
    public boolean addProjectiles(Projectile[] newProjectiles) {
        // 对每个子弹，寻找一个数组空位加进去
        int successfullyAdded = 0;

        OuterLoop:
        for (Projectile projectile : newProjectiles) {
            for (int j = projectilesLastEmptyPosition, cnt = 0;
                 cnt < projectiles.length;
                 j = (j + 1) % projectiles.length, cnt++) {
                if (projectiles[j] == null) {
                    projectiles[j] = projectile;
                    projectilesLastEmptyPosition = j;
                    successfullyAdded++;
                    continue OuterLoop;
                }
            }
        }

        return successfullyAdded == newProjectiles.length;
    }

    /**
     * 在场景中增加一些敌人，但是可能出现数组满的罕见情况，此时返回false
     * @param newEnemies 要增加的敌人数组
     * @return 如果添加成功返回true, 否则false
     */
    public boolean addEnemies(Enemy[] newEnemies) {
        // 对每个新敌人，寻找一个数组空位加进去
        int successfullyAdded = 0;

        OuterLoop:
        for (Enemy newEnemy : newEnemies) {
            for (int j = enemiesLastEmptyPosition, cnt = 0;
                 cnt < enemies.length;
                 j = (j + 1) % enemies.length, cnt++) {
                if (enemies[j] == null) {
                    enemies[j] = newEnemy;
                    enemiesLastEmptyPosition = j;
                    successfullyAdded++;
                    continue OuterLoop;
                }
            }
        }

        return successfullyAdded == newEnemies.length;
    }

    /**
     * 返回游戏内所有物体
     * @return 游戏内所有物体的数组
     */
    public GameObject[] getGameObjects() {
        GameObject[] objects = new GameObject[enemies.length + projectiles.length + 1];
        int i = 0;
        for (Enemy enemy : enemies) {
            if (enemy != null && enemy.enabled) {
                objects[i++] = enemy;
            }
        }
        for (Projectile projectile : projectiles) {
            if (projectile != null && projectile.enabled) {
                objects[i++] = projectile;
            }
        }
        objects[i++] = player;

        GameObject[] result = new GameObject[i];
        for (int j = 0; j < i; j++) {
            result[j] = objects[j];
        }
        return result;
    }

    /** 删除越界飞行物，死亡飞行物及子弹 */
    private void garbageCollection() {

        for (int i = 0; i < projectiles.length; i++) {
            if (projectiles[i] == null) continue;
            if (!projectiles[i].enabled || outOfBounds(projectiles[i])) {
                projectiles[i] = null;
                projectilesLastEmptyPosition = i;
            }
        }

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) continue;
            if (!enemies[i].enabled || outOfBounds((enemies[i]))) {
                enemies[i] = null;
                enemiesLastEmptyPosition = i;
            }
        }
    }

    private boolean outOfBounds(GameObject object) {
        return (object.getX() < -object.getWidth()
                || object.getX() > WIDTH + object.getWidth()
                || object.getY() < -object.getHeight()
                || object.getY() > HEIGHT + object.getHeight());
    }
  
    /** 检查游戏结束 */
    private void checkGameOver() {
        if (isGameOver()) {
            state = GAME_OVER; // 改变状态
        }
    }

    private boolean isGameOver() {
        return player.getLife() <= 0;
    }

    private void printInfo() {
        for (Projectile projectile : projectiles) {
            if (projectile == null) continue;
            System.out.println(projectile.getX() + " " + projectile.getY());
        }
    }

}
