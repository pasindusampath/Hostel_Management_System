package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.hostelmanagementsystem.dto.custom.UserDTO;
import lk.ijse.hostelmanagementsystem.service.custom.UserService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.UserServiceImpl;

public class ForgetPasswordFormController {
    public JFXPasswordField pwfOldPassword;
    public JFXTextField txtOldPassword;
    public ImageView oldPasswordEyeImage;
    public JFXPasswordField pwfNewPassword;
    public JFXTextField txtNewPassword;
    public ImageView newPasswordEyeImage;
    public JFXPasswordField pwfConfirmNewPassword;
    public JFXTextField txtConfirmNewPassword;
    public ImageView confirmPasswordEyeImage;
    public JFXButton btnChangePassword;

    private UserService service = new UserServiceImpl();

    public void initialize(){
        btnChangePassword.setDisable(true);
    }

    public void pwfOldPasswordOnKeyReleasedAction(KeyEvent keyEvent) {
        txtOldPassword.setText(pwfOldPassword.getText());
    }

    public void txtOldPasswordOnKeyReleasedAction(KeyEvent keyEvent) {
        pwfOldPassword.setText(txtOldPassword.getText());
    }

    public void pwfNewPasswordOnKeyReleaseAction(KeyEvent keyEvent) {
        txtNewPassword.setText(pwfNewPassword.getText());
        comparePasswords();
    }

    public void txtNewPasswordOnKeyReleasedAction(KeyEvent keyEvent) {
        pwfNewPassword.setText(txtNewPassword.getText());
        comparePasswords();
    }


    public void pwfConfirmNewPasswordOKeyReleasedAction(KeyEvent keyEvent) {
        txtConfirmNewPassword.setText(pwfConfirmNewPassword.getText());
        comparePasswords();
    }

    public void txtConfirmNewPasswordOKeyReleasedAction(KeyEvent keyEvent) {
        pwfConfirmNewPassword.setText(txtConfirmNewPassword.getText());
        comparePasswords();
    }

    public void comparePasswords(){
        boolean equals = pwfNewPassword.getText().equals(pwfConfirmNewPassword.getText());
        if(equals){
            btnChangePassword.setDisable(false);
            pwfConfirmNewPassword.setUnFocusColor(Paint.valueOf("#21840e"));
            pwfConfirmNewPassword.setFocusColor(Paint.valueOf("#21840e"));
            txtConfirmNewPassword.setUnFocusColor(Paint.valueOf("#21840e"));
            txtConfirmNewPassword.setFocusColor(Paint.valueOf("#21840e"));
        }else {
            btnChangePassword.setDisable(true);
            pwfConfirmNewPassword.setUnFocusColor(Paint.valueOf("RED"));
            pwfConfirmNewPassword.setFocusColor(Paint.valueOf("RED"));
            txtConfirmNewPassword.setUnFocusColor(Paint.valueOf("RED"));
            txtConfirmNewPassword.setFocusColor(Paint.valueOf("RED"));
        }
    }

    public void btnVisibleCofirmNewPasswordOnAction(ActionEvent actionEvent) {
        setVisible(txtConfirmNewPassword,pwfConfirmNewPassword,confirmPasswordEyeImage);
    }

    public void btnVisibleOldPasswordOnAction(ActionEvent actionEvent) {
        setVisible(txtOldPassword,pwfOldPassword,oldPasswordEyeImage);
    }

    public void btnVisibleNewPasswordOnAction(ActionEvent actionEvent) {
        setVisible(txtNewPassword,pwfNewPassword,newPasswordEyeImage);
    }

    public void setVisible(JFXTextField text,JFXPasswordField pwd,ImageView img){
        if(text.isVisible()){
            text.setVisible(false);
            pwd.setVisible(true);
            img.setImage(new Image("/assets/img/icons8-eye-90.png"));
        }else {
            text.setVisible(true);
            pwd.setVisible(false);
            img.setImage(new Image("/assets/img/icons8-hide-96.png"));
        }
    }

    public void btnChangePasswordOnAction(ActionEvent actionEvent) {
        boolean isCorrect = isOldPasswordCorrect();
        if(isCorrect){
            UserDTO user = new UserDTO("user", pwfNewPassword.getText());
            boolean update = service.update(user);
            if(update){
                new Alert(Alert.AlertType.INFORMATION,"Password Update Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Password Update Failed-Unknown Error").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Incorrect Old Password").show();
        }

    }

    public boolean isOldPasswordCorrect(){
        UserDTO user = service.search("user");
        return user.getPassword().equals(pwfOldPassword.getText());
    }

    public void clear() {
        txtNewPassword.clear();
        txtOldPassword.clear();
        txtConfirmNewPassword.clear();

        pwfNewPassword.clear();
        pwfOldPassword.clear();
        pwfConfirmNewPassword.clear();
    }
}
