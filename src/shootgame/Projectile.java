package shootgame;


/**
 * Projectile 是所有游戏内投射物的父类(例如Bullet, Missile, etc)
 *
 * @author hehao
 */
public abstract class Projectile extends GameObject {
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected double damage = 0;

    Projectile(ShootGame game) {
        super(game);
    }
}
