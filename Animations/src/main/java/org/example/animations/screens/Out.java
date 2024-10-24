package org.example.animations.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.example.animations.model.Player;


public class Out {
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;

    public Out(Canvas canvas) {
        this.canvas = canvas;
        this.gc=canvas.getGraphicsContext2D();
        this.player=new Player(this.canvas);
    }

    public void paint(){
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        player.paint();
    }

    public void onKeyPressed(KeyEvent e){
        this.player.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent event) {
       this.player.onKeyReleased(event);
    }
}
