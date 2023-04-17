package lk.ijse.hostelmanagementsystem.util.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lk.ijse.hostelmanagementsystem.controller.*;
import lk.ijse.hostelmanagementsystem.util.factory.types.ViewType;

import java.io.IOException;

public class ViewFactory {
    private static ViewFactory viewFactory;
    private FXMLLoader[] loaders = new FXMLLoader[10];
    private Parent[] parents = new Parent[10];
    private Object[] controllers = new Object[10];
    private Scene[] scenes = new Scene[10];

    private ViewFactory() throws IOException {
        String[] ar = {
                "/view/AddStudentForm.fxml",
                "/view/DashBoardForm.fxml",
                "/view/ForgetPasswordForm.fxml",
                "/view/HomeForm.fxml",
                "/view/LoginForm.fxml",
                "/view/ManageRoomForm.fxml",
                "/view/ManageStudentsForm.fxml",
                "/view/RemainingKeyMoneyForm.fxml",
                "/view/ReserveRoomsForm.fxml",
                "/view/RoomReservationDetailsForm.fxml"
        };

        for (int i = 0; i < ar.length; i++) {
            loaders[i] = new FXMLLoader(getClass().getResource(ar[i]));
            parents[i] = loaders[i].load();
            controllers[i] = loaders[i].getController();
            scenes[i] = new Scene(parents[i]);
        }

    }

    public <T> T getView(ViewType type) {
        switch (type) {
            case ADD_STUDENT:
                ((AddStudentFormController)controllers[0]).clear();
                return (T) parents[0];
            case DASHBOARD:
                return (T) parents[1];
            case FORGET_PASSWORD:
                return (T) parents[2];
            case HOME:
                return (T) parents[3];
            case LOGIN:
                return (T) parents[4];
            case MANAGE_ROOM:
                ((ManageRoomFormController)controllers[5]).clear();
                return (T) parents[5];
            case MANAGE_STUDENT:
                ((ManageStudentsFormController)controllers[6]).clear();
                return (T) parents[6];
            case REMAINING_KEY_MONEY:
                ((RemainingKeyMoneyFormController)controllers[7]).clear();
                return (T) parents[7];
            case RESERVE_ROOM:
                ((ReserveRoomsFormController)controllers[8]).clear();
                return (T) parents[8];
            case ROOM_RESERVATION_DETAIL:
                ((RoomReservationDetailsFormController)controllers[9]).clear();
                return (T) parents[9];
        }
        return null;
    }

    public <T> T getController(ViewType type) {
        switch (type) {
            case ADD_STUDENT:
                return (T) controllers[0];
            case DASHBOARD:
                return (T) controllers[1];
            case FORGET_PASSWORD:
                return (T) controllers[2];
            case HOME:
                return (T) controllers[3];
            case LOGIN:
                return (T) controllers[4];
            case MANAGE_ROOM:
                return (T) controllers[5];
            case MANAGE_STUDENT:
                return (T) controllers[6];
            case REMAINING_KEY_MONEY:
                return (T) controllers[7];
            case RESERVE_ROOM:
                return (T) controllers[8];
            case ROOM_RESERVATION_DETAIL:
                return (T) controllers[9];
        }
        return null;
    }

    public <T> T getScene(ViewType type) {
        switch (type) {
            case ADD_STUDENT:
                return (T) scenes[0];
            case DASHBOARD:
                return (T) scenes[1];
            case FORGET_PASSWORD:
                ((ForgetPasswordFormController)controllers[2]).clear();
                return (T) scenes[2];
            case HOME:
                return (T) scenes[3];
            case LOGIN:
                ((LoginFormController)controllers[4]).clear();
                return (T) scenes[4];
            case MANAGE_ROOM:
                return (T) scenes[5];
            case MANAGE_STUDENT:
                return (T) scenes[6];
            case REMAINING_KEY_MONEY:
                return (T) scenes[7];
            case RESERVE_ROOM:
                return (T) scenes[8];
            case ROOM_RESERVATION_DETAIL:
                return (T) scenes[9];
        }
        return null;
    }

    public static ViewFactory getInstance() {
        if (viewFactory == null) {
            try {
                viewFactory = new ViewFactory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return viewFactory;
    }

}
