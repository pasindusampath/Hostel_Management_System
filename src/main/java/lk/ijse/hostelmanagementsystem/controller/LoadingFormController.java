package lk.ijse.hostelmanagementsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.ViewFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ViewType;
import lk.ijse.hostelmanagementsystem.util.task.ApplicationStartTask;

public class LoadingFormController {
    public Rectangle mainRectangle;
    public Rectangle smallRectangle;
    public Label lblDescription;
    public Label lblPercentage;


    public void initialize() {
        animation();
    }

    public void animation() {
        ApplicationStartTask task = new ApplicationStartTask();
        task.progressProperty().addListener((a,b,c)->{
            smallRectangle.setWidth(mainRectangle.getWidth()*c.doubleValue());
            lblPercentage.setText((c.doubleValue()*100)+"%");
        });

        task.valueProperty().addListener((a,b,c)->{
            if(c.equals("Starting Application...")){
                ((Stage)lblDescription.getScene().getWindow()).close();
                Stage stage = new Stage();
                stage.setScene(ViewFactory.getInstance().getScene(ViewType.LOGIN));
                stage.show();
            }
            lblDescription.setText(c);

        });

        Thread t1 = new Thread(task);
        t1.start();
    }
}
