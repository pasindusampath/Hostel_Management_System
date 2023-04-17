package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;
import lk.ijse.hostelmanagementsystem.util.Regex;
import lk.ijse.hostelmanagementsystem.util.TextFields;
import lk.ijse.hostelmanagementsystem.util.factory.ServiceFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ServiceType;

import java.time.LocalDate;

public class AddStudentFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXButton btnSaveStudents;
    public JFXTextField txtCity;
    public JFXTextField txtGmail;
    private ManageStudentsFormController manageStudentsFormController;

    private StudentService studentService;

    public void initialize(){
        studentService= ServiceFactory.getInstance().getService(ServiceType.STUDENT);
    }

    public void btnSaveStudentsOnAction(ActionEvent actionEvent) {
        if(!validate()){
            new Alert(Alert.AlertType.ERROR,"Check Data And Try Again").show();
            return;
        }
        StudentDTO studentDTO = collectData();
        StudentDTO save = studentService.save(studentDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Student Added Success").show();
            manageStudentsFormController.getDataToTable();
            clear();
        }else {
            new Alert(Alert.AlertType.ERROR,"Student Adding Failed").show();
        }
    }

    public StudentDTO collectData(){
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContactNo.getText();
        String city = txtCity.getText();
        String gmail = txtGmail.getText();

        StudentDTO student = new StudentDTO(id,name,address,city,contact,gmail,
                null);
        return student;
    }

    public boolean validate(){
        boolean b = true;
        if(!Regex.setTextColor(TextFields.EMAIL,txtGmail))b=false;
        if(!Regex.setTextColor(TextFields.PHONE,txtContactNo))b=false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtCity))b=false;
        if(!Regex.setTextColor(TextFields.ADDRESS,txtAddress))b=false;
        if(!Regex.setTextColor(TextFields.NAME,txtName))b=false;
        return b;
    }

    public void setManageStudentsFormController(ManageStudentsFormController manageStudentsFormController){
        this.manageStudentsFormController=manageStudentsFormController;
    }

    public void clear(){
        txtId.clear();
        txtName.clear();
        txtContactNo.clear();
        txtCity.clear();
        txtAddress.clear();
        txtGmail.clear();

    }

    public void setData(StudentTM student) {
        txtId.setText(student.getId());
        txtName.setText(student.getName());
        txtGmail.setText(student.getGmail());
        txtAddress.setText(student.getAddress());
        txtCity.setText(student.getCity());
        txtContactNo.setText(student.getContact());
    }
}
