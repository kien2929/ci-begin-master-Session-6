package base.player;

import base.FrameCounter;
import base.game.GameCanvas;
import base.GameObject;
import base.KeyEventPress;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    BoxCollider boxCollider;
    FrameCounter fireCounter;
    public Player() {
        super();
        this.createRenderer();
        this.position.set(200, 300);
        this.fireCounter = new FrameCounter(20);
        this.boxCollider = new BoxCollider(this.position, 28, 28);
    }

    private void createRenderer() {
        //ArrayList<BufferedImage> images
        ArrayList<BufferedImage> images = new ArrayList<>();
        images.add(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/1.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/2.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/3.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/4.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/5.png"));
        images.add(SpriteUtils.loadImage("assets/images/players/straight/6.png"));
        //AnimationRenderer(images)
        this.renderer = new AnimationRenderer(images);
    }

    @Override
    public void run() {
        this.move(); //change velocity
        if(this.fireCounter.run() && KeyEventPress.isFirePress) {
            this.fire();
        }
        super.run(); //this.position.addThis(this.velocity)
    }

    private void move() {
        int vx = 0;
        int vy = 0;
        if(KeyEventPress.isUpPress) {
            vy -= 1;
        }
        if(KeyEventPress.isDownPress) {
            vy += 1;
        }
        if(KeyEventPress.isLeftPress) {
            vx -= 1;
        }
        if(KeyEventPress.isRightPress) {
            vx += 1;
        }
        this.velocity.set(vx, vy);
    }

    private void fire() {
        PlayerBulletType1 bullet = GameObject.recycle(PlayerBulletType1.class);
        bullet.position.set(this.position);
//        bullet.velocity.set(0, -5);

        PlayerBulletType2 bullet2 = GameObject.recycle(PlayerBulletType2.class);
        bullet2.position.set(this.position.add(-25, 0));
        bullet2.velocity.set(-5, -5);

        PlayerBulletType2 bullet3 = GameObject.recycle(PlayerBulletType2.class);
        bullet3.position.set(this.position.add(25, 0));
        bullet3.velocity.set(5, -5);

        this.fireCounter.reset();
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
    @Override
    public void destroy(){
        super.destroy();
        PlayerExplosion playerExplosion = GameObject.recycle(PlayerExplosion.class);
        playerExplosion.position.set(this.position);
    }
}
