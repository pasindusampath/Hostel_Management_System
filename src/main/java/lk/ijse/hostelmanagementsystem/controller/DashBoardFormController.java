package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostelmanagementsystem.util.factory.ViewFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ViewType;
import lk.ijse.hostelmanagementsystem.util.task.TimeTask;

import java.io.IOException;
import java.time.LocalTime;

public class DashBoardFormController {
    public JFXButton btnHome;
    public JFXButton btnManageStudents;
    public JFXButton btnManageRooms;
    public JFXButton btnReserveRooms;
    public JFXButton btnDetails;
    public JFXButton btnRemaining;
    public AnchorPane dashBoardContext;
    public JFXButton btnLogout;
    public AnchorPane dashBoardMainContext;
    public ImageView imgHome;
    public ImageView imgManageStudent;
    public ImageView imgManageRooms;
    public ImageView imgReserveRoom;
    public ImageView imgRoomReservationDetails;
    public ImageView imgRemainingKeyMoney;
    public Label lblTime;

    public void initialize() throws IOException {
        setTime();
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/HomeForm.fxml")));
    }

    public void setTime() {

        TimeTask a = new TimeTask();
        a.valueProperty().addListener((e,b,c)->{
            lblTime.setText(c);
        });
        Thread t1 = new Thread(a);
        t1.setDaemon(true);
        t1.start();

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        ((Stage) dashBoardMainContext.getScene().getWindow()).setScene(ViewFactory.getInstance().getScene(ViewType.LOGIN));
    }

    public void btnRemainingOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.REMAINING_KEY_MONEY));
    }

    public void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.ROOM_RESERVATION_DETAIL));
    }

    public void btnReserveRoomsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.RESERVE_ROOM));
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.MANAGE_ROOM));
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.MANAGE_STUDENT));
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        dashBoardContext.getChildren().clear();
        dashBoardContext.getChildren().add(ViewFactory.getInstance().getView(ViewType.HOME));
    }

    public void btnForgetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnHome.getScene().getWindow());
        stage.setScene(ViewFactory.getInstance().getScene(ViewType.FORGET_PASSWORD));
        stage.show();
    }

    public void btnHomeOnMouseEnteredAction(MouseEvent mouseEvent) throws InterruptedException {
        Timeline t = new Timeline();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgHome);
        transition.setToX(-50);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
       /* KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {
                */
        btnHome.setContentDisplay(ContentDisplay.CENTER);
        /*    }
        });

        t.getKeyFrames().addAll(keyFrame2);
        t.play();*/

    }


    public void btnHomeOnMouseExitAction(MouseEvent mouseEvent) {
        btnHome.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgHome);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();

    }

    public void btnManageStudentsOnMouseEntered(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgManageStudent);
        transition.setToX(-80);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
       /* Timeline t = new Timeline();
        KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {*/
        btnManageStudents.setContentDisplay(ContentDisplay.CENTER);
        /*    }
        });
        t.getKeyFrames().add(k);
        t.play();*/


    }

    public void btnManageRoomsOnMouseEntered(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgManageRooms);
        transition.setToX(-80);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        /*Timeline t = new Timeline();
        KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {*/
        btnManageRooms.setContentDisplay(ContentDisplay.CENTER);
       /*     }
        });
        t.getKeyFrames().add(k);
        t.play();*/
    }

    public void btnReserveRoomsOnMouseEntered(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgReserveRoom);
        transition.setToX(-80);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        /*Timeline t = new Timeline();
        KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {*/

        btnReserveRooms.setContentDisplay(ContentDisplay.CENTER);
        /*    }
        });
        t.getKeyFrames().add(k);
        t.play();*/

    }

    public void btnReserveationDetailsOnMouseEntered(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgRoomReservationDetails);
        transition.setToX(-90);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        /*Timeline t = new Timeline();
        KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {*/
        btnDetails.setContentDisplay(ContentDisplay.CENTER);
        /*    }
        });
        t.getKeyFrames().add(k);
        t.play();*/

    }

    public void btnRemainingKeyMoneyOnMouseEntered(MouseEvent mouseEvent) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgRemainingKeyMoney);
        transition.setToX(-100);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
        /*Timeline t = new Timeline();
        KeyFrame k = new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public synchronized void handle(ActionEvent event) {*/
        btnRemaining.setContentDisplay(ContentDisplay.CENTER);
       /*     }
        });
        t.getKeyFrames().add(k);
        t.play();*/

    }

    public void btnManageStudentsOnMouseExited(MouseEvent mouseEvent) {
        btnManageStudents.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgManageStudent);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }


    public void btnManageRoomsOnMouseExited(MouseEvent mouseEvent) {
        btnManageRooms.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgManageRooms);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }


    public void btnReserveRoomsOnMouseExited(MouseEvent mouseEvent) {
        btnReserveRooms.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgReserveRoom);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }


    public void btnReserveationDetailsOnMouseExited(MouseEvent mouseEvent) {
        btnDetails.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgRoomReservationDetails);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }


    public void btnRemainingKeyMoneyOnMouseExited(MouseEvent mouseEvent) {
        btnRemaining.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(imgRemainingKeyMoney);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(0.5));
        transition.play();
    }
}
