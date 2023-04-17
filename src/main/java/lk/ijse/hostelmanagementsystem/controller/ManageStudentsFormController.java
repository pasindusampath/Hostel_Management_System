package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.entity.custom.Student;
import lk.ijse.hostelmanagementsystem.service.custom.StudentService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.StudentTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ManageStudentsFormController {
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public AnchorPane addStudentPane;
    public TableView<StudentTM> tblStudents;
    public TableColumn<StudentTM,String> colName;
    public TableColumn<StudentTM,String> colAddress;
    public TableColumn<StudentTM,String> colContact;
    public TableColumn<StudentTM,String> colCity;
    public TableColumn<StudentTM,String> colGmail;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtCity;
    public JFXTextField txtGmail;
    AddStudentFormController controller ;

    private final StudentService  studentService = new StudentServiceImpl();


    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddStudentForm.fxml"));
        Parent load = loader.load();
        controller = loader.getController();
        controller.setManageStudentsFormController(this);
        addStudentPane.getChildren().add(load);
        visualize();
        getDataToTable();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StudentTM studentTM = collectData();
        StudentDTO student = covertToDto(studentTM);
            boolean save = studentService.update(student);
            if(save){
                new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                getDataToTable();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
            }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        StudentTM studentTM = collectData();
        StudentDTO student = covertToDto(studentTM);

            boolean save = studentService.delete(student);
            if(save){
                new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
                getDataToTable();
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Clear data in textFields"
                        , ButtonType.YES, ButtonType.NO).showAndWait();
                if(buttonType.isPresent()){
                    if(buttonType.get().equals(ButtonType.YES)){
                        clear();
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
            }

    }

    private StudentDTO covertToDto(StudentTM student){
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getCity(),
                student.getContact(),student.getGmail(),null);
    }

    private StudentTM convertToTM(StudentDTO student){
        return new StudentTM(student.getId(),student.getName(),student.getAddress(),
                student.getContact(),student.getCity(),student.getGmail());
    }

    private StudentTM collectData(){
        return new StudentTM(txtStudentId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),
                txtCity.getText(),txtGmail.getText());
    }

    public void getDataToTable(){
        List<StudentDTO> all = studentService.getAll();
        ObservableList<StudentTM> list = FXCollections.observableArrayList();
        for (StudentDTO ob:all){
            list.add(convertToTM(ob));
        }
        tblStudents.setItems(list);
    }

    public void visualize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
    }

    public void tblStudentOnClickAction(MouseEvent mouseEvent) {
        StudentTM student = tblStudents.getSelectionModel().getSelectedItem();
        if(student!=null)setTextFields(covertToDto(student));
    }

    public void setTextFields(StudentDTO student){
        txtStudentId.setText(student.getId());
        txtName.setText(student.getName());
        txtContact.setText(student.getContact());
        txtAddress.setText(student.getAddress());
        txtCity.setText(student.getCity());
        txtGmail.setText(student.getGmail());
    }

    public void clear(){
        txtStudentId.clear();
        txtName.clear();
        txtContact.clear();
        txtCity.clear();
        txtAddress.clear();
        txtGmail.clear();
    }


    public void btnSendDataOnAction(ActionEvent actionEvent) {
        controller.setData(collectData());
        clear();
    }
}
