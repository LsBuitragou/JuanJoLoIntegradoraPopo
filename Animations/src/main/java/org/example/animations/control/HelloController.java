package org.example.animations.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.animations.screens.Out;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private Out screenOut;
    private boolean isWindowOpen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isWindowOpen = true;
        this.gc = canvas.getGraphicsContext2D();
        this.screenOut=new Out(this.canvas);

        this.canvas.setOnKeyPressed(event -> {
            screenOut.onKeyPressed(event);
        });
        this.canvas.setOnKeyReleased(event -> {
            screenOut.onKeyReleased(event); // Acción cuando se suelta la tecla
        });

        if (!isWindowOpen) {
           isWindowOpen = false;
        }

        canvas.widthProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
            draw();
        });
        // Listener para ajustar el tamaño del canvas al alto del contenedor
        canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
            draw();
        });

        //Hilo
        new Thread(
                ()->{
                    while(isWindowOpen){
                        Platform.runLater(
                                ()->{
                                    screenOut.paint();
                                });
                        try{
                            Thread.sleep(30);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
    public void setStageAndSetupListeners(Stage stage) {
        stage.setOnCloseRequest(event -> {
            isWindowOpen = false;
        });
    }
    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.strokeText("Canvas adaptado", canvas.getWidth() / 2, canvas.getHeight() / 2);
    }
}