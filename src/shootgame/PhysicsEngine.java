package shootgame;

import shootgame.gameobjects.GameObject;

import java.util.ArrayList;

/**
 * 整个游戏的物理引擎类，负责在每个游戏循环中检测碰撞，如果发生碰撞则调用每个游戏物件的OnCollision函数
 *
 * @author hehao
 */
public class PhysicsEngine {

    public static abstract class Collider {
        public GameObject parent;

        public abstract void update();
    }

    public static class BoxCollider extends Collider {
        public double minX;
        public double minY;
        public double width;
        public double height;

        public BoxCollider(GameObject parent, double minX, double minY, double width, double height) {
            this.parent = parent;
            this.minX = minX;
            this.minY = minY;
            this.width = width;
            this.height = height;
        }

        public void update()
        {
            minX = parent.x;
            minY = parent.y;
        }

    }

    private ShootGame game;

    public PhysicsEngine(ShootGame game) {
        this.game = game;
    }

    /**
     * 游戏引擎调用此函数来计算碰撞
     */
    public void detectCollision() {
        ArrayList<Collider> colliders = new ArrayList<Collider>();
        GameObject[] objects = game.getGameObjects();

        for (GameObject obj : objects) {
            if (obj.collider != null) {
                colliders.add(obj.collider);
            }
        }

        // Update collider data
        for (Collider c : colliders) {
            c.update();
        }

        for (int i = 0; i < colliders.size(); i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                // Update collider data
                Collider c1 = colliders.get(i);
                Collider c2 = colliders.get(j);

                if (hasCollision(c1, c2)) {
                    GameObject obj1 = c1.parent;
                    GameObject obj2 = c2.parent;
                    obj1.onCollision(obj2);
                    obj2.onCollision(obj1);
                }
            }
        }
    }

    private boolean hasCollision(Collider c1, Collider c2) {
        if (c1 instanceof BoxCollider && c2 instanceof BoxCollider) {
            double minX1 = ((BoxCollider) c1).minX;
            double minY1 = ((BoxCollider) c1).minY;
            double maxX1 = ((BoxCollider) c1).width  + minX1;
            double maxY1 = ((BoxCollider) c1).height + minY1;

            double minX2 = ((BoxCollider) c2).minX;
            double minY2 = ((BoxCollider) c2).minY;
            double maxX2 = ((BoxCollider) c2).width  + minX2;
            double maxY2 = ((BoxCollider) c2).height + minY2;

            // Exit with no intersection if found separated along an axis
            if (maxX1 < minX2 || minX1 > maxX2) return false;
            if (maxY1 < minY2 || minY1 > maxY2) return false;

            // No separating axis found, therefor there is at least one overlapping axis
            return true;

        }
        // unhandled situation, return false
        return false;
    }

}
