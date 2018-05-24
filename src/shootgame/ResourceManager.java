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

            BufferedImage background = ImageIO.read(ShootGame.class.getResource("/resources/background_41.jpg"));
            images.put("background", background);

            BufferedImage start = ImageIO.read(ShootGame.class.getResource("/resources/start_4.png"));
            images.put("start", start);

            BufferedImage airplane = ImageIO.read(ShootGame.class.getResource("/resources/airplane_40.png"));
            images.put("airplane", airplane);

            BufferedImage airplane0 = ImageIO.read(ShootGame.class.getResource("/resources/airplane_41.png"));
            images.put("airplane0", airplane0);

            BufferedImage airplane1 = ImageIO.read(ShootGame.class.getResource("/resources/airplane_42.png"));
            images.put("airplane1", airplane1);

            BufferedImage airplane2 = ImageIO.read(ShootGame.class.getResource("/resources/airplane_43.png"));
            images.put("airplane2", airplane2);

            BufferedImage airplane3 = ImageIO.read(ShootGame.class.getResource("/resources/airplane_44.png"));
            images.put("airplane3", airplane3);

            BufferedImage airplane4 = ImageIO.read(ShootGame.class.getResource("/resources/airplane_46.png"));
            images.put("airplane4", airplane4);

            BufferedImage bee = ImageIO.read(ShootGame.class.getResource("/resources/airplane_45.png"));
            images.put("bee", bee);

            BufferedImage bullet = ImageIO.read(ShootGame.class.getResource("/resources/bullet_40.png"));
            images.put("bullet", bullet);

            BufferedImage bullet0 = ImageIO.read(ShootGame.class.getResource("/resources/bullet_40.png"));
            images.put("bullet0", bullet0);

            BufferedImage bullet1 = ImageIO.read(ShootGame.class.getResource("/resources/bullet_41.png"));
            images.put("bullet1", bullet1);

            BufferedImage hero0 = ImageIO.read(ShootGame.class.getResource("/resources/hero_40.jpg"));
            images.put("hero0", hero0);

            BufferedImage hero1 = ImageIO.read(ShootGame.class.getResource("/resources/hero_41.png"));
            images.put("hero1", hero1);

            BufferedImage pause = ImageIO.read(ShootGame.class.getResource("/resources/pause_4.jpeg"));
            images.put("pause", pause);

            BufferedImage gameover = ImageIO
                    .read(ShootGame.class.getResource("/resources/gameover_4.jpg"));
            images.put("gameover", gameover);
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
