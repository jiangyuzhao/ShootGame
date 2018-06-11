# Shoot! Game
这是一个纵版飞行射击游戏，玩家操控飞机射击并且消灭敌人，最后消灭boss整个关卡就结束了。

## 1. 游戏名称

## Shoot! Game

## 2. 团队信息

### 2.1 成员

秦雨轩 何昊 李天翼 缪舜 潘兴禄 王乐强  

### 2.2 分工

何昊：游戏的底层实现和架构设计，代码的评审、整合和重构

李天翼：开始，结束和暂停界面的设计，保存和读取游戏功能的实现

王乐强：场景管理器的设计，设计整个游戏关卡

秦雨轩、缪舜、潘兴禄：玩家和敌人类的设计，包括投射物的设计


负责人：何昊

![](architecture.png)

考虑我们游戏的实现需求，我们将游戏划分成上图所示的模块。每个模块可以由一个人或多个人维护，模块与模块之间耦合度低，可以由不同人完成。这样我们就能很好地完成分工。游戏分为以下部分：核心类`ShootGame`，开始和结束游戏的窗体类，还有游戏上层场景。在场景中存在各种各样的`GameObject`来实现整个游戏的功能。游戏核心类`ShootGame`还负责和其他类进行通讯。

### 3.1 游戏物件设计

对所有的游戏物件，我们都为其设计了三个方法供子类重载，类似如下：

```java
public abstract class GameObject {
    public void update() {}
    public void render(Graphics g) {}
    public void onCollision(Collider other) {}
}
```

`update()`在游戏主循环里调用，负责更新这个物件在下一帧的状态，例如，是该移动，还是该射击，还是该死亡，等等。如果要处理用户输入，则可以在`update()`中调用`InputManager.getInput()`方法。

`render()`在`JPanel`的`paint()`方法里被调用，负责这个游戏物件的渲染。比如绘制图片，绘制血条，绘制动画，等等。如果要使用各种图片，则调用`ResourceManager.getImage()`方法。

`onCollision()`则是在这个游戏物件的`Collider`不为`null`，在物理引擎检测碰撞时发现了碰撞而调用，可以用来处理物体与物体之间的交互关系，例如子弹命中敌人该怎么做，玩家和敌人碰撞该怎么做，等等。一个典型的游戏物件设计类似如下：

``` java
public class SomeObject extends GameObject {
    // Some Private Fields Here
    // Some Initalization Here
    public void update() {
        HandlePlayerInput();
        Move();
        ShootBullet();
        ShootMissile();
    }
    public void render(Graphics g) {
    	g.DrawImage(image, ..., ..., ..., ...);
    }
    public void onCollision(Collider other) {
        if (other instanceof Projectile) {
            // Do Something
        } else if (other instanceof Enemy) {
            // Do Something
        }
    }
}
```

游戏物件的继承树如下：

![](inheritance.png)

这样，对于负责编写游戏物件的人，就不用触碰游戏底层的代码，只用编写和扩展以上三个函数就能实现绝大多数的游戏功能。

### 3.2 游戏主循环

任何游戏都需要一个主循环，在主循环中按固定的间隔刷新游戏，在不同的模块中调用上述的`update()，render()`和`onCollision()`方法，让物体移动，子弹爆炸， 敌人攻击……这样一帧一帧下去，整个游戏便运转了起来。我们的游戏主循环形如

```java
timer.schedule(new TimerTask() {
    if (state == RUNNING) {
        updateTimeInformation();
        handleUserInput();
        for (object : gameObjects)
            object.update();
        physicsEngine.detectCollision();
        garbageCollection();
        checkGameOver();
    }
    repaint();
}, interval, interval);
```

主循环中，如果游戏正在运行，每一帧先更新时序信息，然后处理用户输入，然后对所有游戏物件更新游戏状态，然后处理物理引擎，然后回收这一帧被摧毁的游戏物体，然后检查游戏是否结束，最后调用渲染线程对画面进行渲染。

### 3.3 关于成员变量的可访问性

一般来讲，在面向对象的软件设计中，为了良好的封装，都会将成员变量设为`private`并提供`getter()`和`setter()`方法，但是游戏程序中有时并不会这么做，原因如下：

1. 游戏的模块与模块之间耦合度高，常常出现一个模块大量访问另一个模块的情况，这时使用`getter()`和`setter()`方法会使得代码没有必要地冗长。
2. 由于这种调用非常多，`getter()`和`setter()`会产生可观的函数调用开销，其中尤其致命的是大量地址波动较大的内存访问，是对程序局部性的破坏，降低缓存性能。对于一般应用这种性能损失可以忽略不计，但对性能要求较高的游戏程序则不一定可以忽略。
3. 在Unity等成熟商业游戏引擎中，也存在大量模块直接提供大量`public`成员供程序员直接访问，体现出这是业界的最佳实践。
4. 有的成员变量，即使在外部做了任意修改也不一定会破坏整个游戏的运行正确性。例如，这个变量会在每次游戏主循环中被一段固定代码刷新。

综上所述，本游戏程序的设计中，如果某些变量需要大量修改并且对其修改不会破坏游戏的状态，则会将其暴露为`public`，不提供`getter()`和`setter()`。而对于对游戏状态的正确性确实很敏感的变量，设置为`private`，并且也不提供`getter()`和`setter()`，只允许其他`public`方法更新其值。



此外，为了让所有人都可以方便地添加、删除各种图片，我们设计了一个`ResourceManager`类，在这个类中可以包装一个接口，在程序开始时预读取所有图片文件，每个图片文件标记一个唯一的`tag`，各个`GameObject`获取资源时直接根据`tag`读取指针即可。如果`GameObject`请求不到资源，可以返回一个占位符图片而不至于使得整个游戏因为缺失资源而崩溃。

伪代码如下

```java
public class ResourceManager {
    private static HashMap<String, BufferedImage> images = new HashMap<>();
    static {
        imgA = ImageIO.read....;
        images.put("some tag", imgA);
        ....
    }
    public static BufferedImage getImage(String tag) {
        BufferedImage image = images.get(tag);
        if (image == null)
            return images.get("nothing");
        return image;
    }
}
```



1. Unity Game Engine.
2. Robert Nystrom. Game Programming Patterns. China Posts & Telecom Press. 2016.
3. Jason Gregory. Game Engine Architecture. Publishing House of Electronic Industry. 2015.
