package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.UserServiceImpl;
import lk.ijse.hostelmanagementsystem.util.factory.ViewFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ViewType;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane LoginFormContext;
    public Label lblWrong;
    public ImageView eyeIcon;
    public Label lblVisiblePassword;
    public JFXTextField txtVisiblePassword;

    private UserService service = new UserServiceImpl();

    public void btnLoginOnAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnLogin) {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            boolean b = checkLogin(new UserDTO(username, password));
            if (b) {
                System.out.println("login success");
                Stage window = (Stage) LoginFormContext.getScene().getWindow();
                window.setScene(ViewFactory.getInstance().getScene(ViewType.DASHBOARD));
                window.centerOnScreen();
            } else if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                lblWrong.setText("Please enter your data.");
            }
        }
    }

    public boolean checkLogin(UserDTO user){
        UserDTO search = service.search(user.getUserName());
        if(search==null){
            lblWrong.setText("Invalid User Name");
            lblWrong.setVisible(true);
            return false;
        }
        if(!user.getPassword().equals(search.getPassword())){
            lblWrong.setText("Invalid Password");
            lblWrong.setVisible(true);
            return false;
        }
        return true;
        //if(user.getPassword().equals())
    }

    public void btnVisiblePasswordOnAction(ActionEvent actionEvent) {
        if(txtVisiblePassword.isVisible()){
            eyeIcon.setImage(new Image("/assets/img/icons8-hide-96.png"));
            txtVisiblePassword.setVisible(false);
            txtPassword.setVisible(true);
        }else {
            eyeIcon.setImage(new Image("/assets/img/icons8-eye-90.png"));
            txtVisiblePassword.setVisible(true);
            txtPassword.setVisible(false);
        }


    }

    public void txtPasswordKeyReleaseOnAction(KeyEvent keyEvent) {
        txtVisiblePassword.setText(txtPassword.getText());
    }

    public void txtVisiblePasswordOnKeyReleaseAction(KeyEvent keyEvent) {
        txtPassword.setText(txtVisiblePassword.getText());
    }

    public void clear() {
        txtPassword.clear();
        txtVisiblePassword.clear();
        txtUserName.clear();
    }
}
