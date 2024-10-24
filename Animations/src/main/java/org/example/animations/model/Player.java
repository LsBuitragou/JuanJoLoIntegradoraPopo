package org.example.animations.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Player {

    //ELEMENTOS GRÁFICOS
    private Canvas canvas;
    private final GraphicsContext graphicsContexts;
    private ArrayList<Image> idleFront;
    private ArrayList<Image> idleRight;
    private ArrayList<Image> idleLeft;
    private ArrayList<Image> idleBack;
    private ArrayList<Image> runFront;
    private ArrayList<Image> runBack;
    private ArrayList<Image> runRight;
    private ArrayList<Image> runLeft;
    private State state;
    private int frame;
    private boolean upPressed=false;
    private boolean downPressed=false;
    private boolean rightPressed=false;
    private boolean leftPressed=false;

    private Position position;

    public Player(Canvas canvas) {
        //Canvas
        this.canvas = canvas;
        this.graphicsContexts = this.canvas.getGraphicsContext2D();
        //Estado
        this.state = State.IdleFront;
        this.frame = 0;
        //Arreglos de todas las animaciones posibles
        this.idleFront = new ArrayList<>();
        this.idleRight = new ArrayList<>();
        this.idleLeft = new ArrayList<>();
        this.idleBack = new ArrayList<>();
        this.runFront = new ArrayList<>();
        this.runBack = new ArrayList<>();
        this.runRight = new ArrayList<>();
        this.runLeft = new ArrayList<>();

        this.position = new Position(100, 100);

        for(int i=1;i<=6;i++){
            Image front=new Image(getClass().getResourceAsStream("/animations.player/idle/Front/"+i+".png"),30,45,false,false);
            idleFront.add(front);
            Image back=new Image(getClass().getResourceAsStream("/animations.player/idle/Back/"+i+".png"),30,45,false,false);
            idleBack.add(back);
            Image right=new Image(getClass().getResourceAsStream("/animations.player/idle/Right/"+i+".png"),30,45,false,false);
            idleRight.add(right);
            Image left=new Image(getClass().getResourceAsStream("/animations.player/idle/Left/"+i+".png"),30,45,false,false);
            idleLeft.add(left);
        }
        for(int i=1;i<=6;i++){
            Image front=new Image(getClass().getResourceAsStream("/animations.player/run/Front/"+i+".png"),30,45,false,false);
            runFront.add(front);
            Image back=new Image(getClass().getResourceAsStream("/animations.player/run/Back/"+i+".png"),30,45,false,false);
            runBack.add(back);
            Image right=new Image(getClass().getResourceAsStream("/animations.player/run/Right/"+i+".png"),30,45,false,false);
            runRight.add(right);
            Image left=new Image(getClass().getResourceAsStream("/animations.player/run/Left/"+i+".png"),30,45,false,false);
            runLeft.add(left);
        }
    }

    public void paint(){
        onMove();
        switch (state){
            case IdleFront:
                graphicsContexts.drawImage(idleFront.get(frame%6), position.getX(), position.getY());
                frame++;
                break;
                case IdleRight:
                    graphicsContexts.drawImage(idleRight.get(frame%6), position.getX(), position.getY());
                    frame++;
                    break;
                    case IdleLeft:
                        graphicsContexts.drawImage(idleLeft.get(frame%6), position.getX(), position.getY());
                        frame++;
                        break;
                        case RunFront:
                            graphicsContexts.drawImage(runFront.get(frame%6), position.getX(), position.getY());
                            frame++;
                            break;
                            case RunBack:
                                graphicsContexts.drawImage(runBack.get(frame%6), position.getX(), position.getY());
                                frame++;
                                break;
                                case RunRight:
                                    graphicsContexts.drawImage(runRight.get(frame%6), position.getX(), position.getY());
                                    frame++;
                                    break;
                                    case RunLeft:
                                        graphicsContexts.drawImage(runLeft.get(frame%6), position.getX(), position.getY());
                                        frame++;
                                        break;
                                        case IdleBack:
                                                graphicsContexts.drawImage(idleBack.get(frame%6), position.getX(), position.getY());
                                                frame++;
                                                break;

        }
    }

    public void onKeyPressed(KeyEvent event){
        switch(event.getCode()){
            case W->{
                state=State.RunBack;
                upPressed=true;
            }
            case S->{
                state=State.RunFront;
                downPressed=true;
            }
            case D->{
                state=State.RunRight;
                rightPressed=true;
            }
            case A->{
                state=State.RunLeft;
                leftPressed=true;
            }
        }
    }

    public void onMove(){
        if(upPressed&&position.getY()>0){
            position.setY(position.getY()-8);
        }else if(downPressed&&position.getY()<canvas.getHeight()-45){
            position.setY(position.getY()+8);
        }else if(rightPressed&&position.getX()<canvas.getWidth()-30){
            position.setX(position.getX()+8);
        }else if(leftPressed&&position.getX()>0){
            position.setX(position.getX()-8);
        }
    }

    public void onKeyReleased(KeyEvent event){
        switch (event.getCode()) {
            case W->{
                state=State.IdleBack;
                upPressed=false;
            }
            case S->{
                state=State.IdleFront;
                downPressed=false;
            }
            case D->{
                state=State.IdleRight;
                rightPressed=false;
            }
            case A->{
                state=State.IdleLeft;
                leftPressed=false;
            }
        }
    }
}
