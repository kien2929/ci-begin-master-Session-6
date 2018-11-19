package base.game;

import base.Background;
import base.GameObject;
import base.enemy.Enemy;
import base.enemy.EnemyBullet;
import base.player.Player;
import base.player.PlayerBullet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameCanvas extends JPanel {
    public GameObject background;
    public GameObject player;

    public GameCanvas() {
        //super
        this.background = GameObject.recycle(Background.class); //new Background()
        this.player = GameObject.recycle(Player.class); //new Player()
        Enemy enemy = GameObject.recycle(Enemy.class); // new Enemy()
    }

    public void gameLoop() {
        int delay = 1000 / 60;
        long lastRun = 0;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastRun > delay) {
                this.runAll();
                this.renderAll();
                lastRun = currentTime;
            }
        }
    }

    public void runAll() {
//        for(GameObject gameObject : GameObject.gameObjects) {
        for(int i = 0; i < GameObject.gameObjects.size(); i++) {
            GameObject gameObject = GameObject.gameObjects.get(i);
            if(gameObject.isActive) {
                gameObject.run();
            }
        }
        System.out.println(GameObject.gameObjects.size());
    }

    @Override
    protected void paintComponent(Graphics g) {
//        for(GameObject gameObject : GameObject.gameObjects) {
        for(int i = 0; i < GameObject.gameObjects.size(); i++) {
            GameObject gameObject = GameObject.gameObjects.get(i);
            if(gameObject.isActive) {
                gameObject.render(g);
            }
        }
    }

    public void renderAll() {
        this.repaint();
    }
}
