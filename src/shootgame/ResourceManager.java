package shootgame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * ResourceManager扮演游戏引擎与文件系统的中间层，管理图片和声音文件
 * 由于我们需要的资源文件较少，因此在游戏启动时读取所有的资源文件进内存
 * 即使这样，添加这一中间层有如下的好处：
 * 1. 避免代码其他部分出现文件IO有关的代码
 * 2. 即使其他代码错误调用了不存在的资源，也不会由于null使得游戏崩溃，因为本类可以返回一个占位符资源（纯黑图片等），增强了鲁棒性
 * 3. 所有人都可以在这个类的构造器中添加代码加载新的资源，不用触动其他底层代码
 * 4. 即使图片文件更改了，只要不更改tag，就不用改动游戏顶层的代码
 *
 * 所以，要增加新资源（比如图片），请：
 * 1. 修改static构造器中载入新图片，添加一个String作为唯一标签，加入images这个hashMap中
 * 2. 调用ResourceManager.getImage(String)方法获取BufferedImage图片
 *
 * @author hehao
 */
public class ResourceManager {

    private static HashMap<String, BufferedImage> images = new HashMap<>();

    static {
        // 读取图片资源
        try {
            BufferedImage nothing = ImageIO.read(ShootGame.class.getResource("/resources/nothing.png"));
            images.put("nothing", nothing);

            BufferedImage background1 = ImageIO.read(ShootGame.class.getResource("/resources/background1.png"));
            images.put("background1", background1);

            BufferedImage background2 = ImageIO.read(ShootGame.class.getResource("/resources/background2.png"));
            images.put("background2", background2);

            BufferedImage background3 = ImageIO.read(ShootGame.class.getResource("/resources/background3.png"));
            images.put("background3", background3);

            BufferedImage enemy1 = ImageIO.read(ShootGame.class.getResource("/resources/enemy1.png"));
            images.put("enemy1", enemy1);

            BufferedImage enemy1Other = ImageIO.read(ShootGame.class.getResource("/resources/enemy1_other.png"));
            images.put("enemy1_other", enemy1Other);

            BufferedImage enemy2 = ImageIO.read(ShootGame.class.getResource("/resources/enemy2.png"));
            images.put("enemy2", enemy2);

            BufferedImage enemy3 = ImageIO.read(ShootGame.class.getResource("/resources/enemy3.png"));
            images.put("enemy3", enemy3);

            BufferedImage boss = ImageIO.read(ShootGame.class.getResource("/resources/boss.png"));
            images.put("boss", boss);

            BufferedImage bullet = ImageIO.read(ShootGame.class.getResource("/resources/bullet1.png"));
            images.put("bullet", bullet);

            BufferedImage bullet0 = ImageIO.read(ShootGame.class.getResource("/resources/bullet2.png"));
            images.put("bullet0", bullet0);

            BufferedImage bullet1 = ImageIO.read(ShootGame.class.getResource("/resources/bullet3.png"));
            images.put("bullet1", bullet1);

            BufferedImage player = ImageIO.read(ShootGame.class.getResource("/resources/player.png"));
            images.put("player", player);

            BufferedImage explosion1 = ImageIO.read(ShootGame.class.getResource("/resources/explosion1.png"));
            images.put("explosion1", explosion1);

            BufferedImage explosion2 = ImageIO.read(ShootGame.class.getResource("/resources/explosion2.png"));
            images.put("explosion2", explosion2);

        } catch (IOException exec) {
            exec.printStackTrace();
        }
    }

    /**
     * 根据字符串tag获取图片资源，如果tag不存在，返回一个默认图片占位符
     *
     * @param tag 用来获取图片的字符串tag
     *
     * @return 图片资源，保证不是null
     */
    public static BufferedImage getImage(String tag) {
        BufferedImage image = images.get(tag);
        if (image == null)
            return images.get("nothing");
        return image;
    }

}
