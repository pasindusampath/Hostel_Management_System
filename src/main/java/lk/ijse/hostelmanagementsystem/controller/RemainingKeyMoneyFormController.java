package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.StudentRoomDTO;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.JoinServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RemainingKeyMoneyTM;
import lk.ijse.hostelmanagementsystem.util.factory.ServiceFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ServiceType;

import java.util.List;

public class RemainingKeyMoneyFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtStartDate;
    public JFXTextField txtEndDate;
    public JFXTextField txtRemainingKeyMoney;
    public JFXTextField txtPayingAmount;
    public TableView<RemainingKeyMoneyTM> tblRemainingKeyMoney;
    public TableColumn<RemainingKeyMoneyTM,String> colRoomId;
    public TableColumn<RemainingKeyMoneyTM,String> colRoomType;
    public TableColumn<RemainingKeyMoneyTM,String> colStudent;
    public TableColumn<RemainingKeyMoneyTM,Double> colFullKeyMoney;
    public TableColumn<RemainingKeyMoneyTM,Double> colPaidAmount;

    private final JoinService joinService = ServiceFactory.getInstance().getService(ServiceType.JOIN);;
    private final StudentRoomService studentRoomService = ServiceFactory.getInstance().getService(ServiceType.STUDENT_ROOM);

    public void initialize(){
        visualize();
        getData();
        txtPayingAmount.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPayingAmount.setText(newValue.replaceAll("\\D", "")); // force the text field to be numeric only
            }
        });
    }
    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colFullKeyMoney.setCellValueFactory(new PropertyValueFactory<>("fullKeyMoney"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
    }

    public void getData(){
        List<RemainingKeyMoneyTM> details = joinService.getRemainingKeyMoneyDetails();
        ObservableList<RemainingKeyMoneyTM> data = FXCollections.observableArrayList(details);
        tblRemainingKeyMoney.setItems(data);
    }

    public void tblRemainingKeyMoneyOnClickAction(MouseEvent mouseEvent) {
        RemainingKeyMoneyTM selectedItem = tblRemainingKeyMoney.getSelectionModel().getSelectedItem();
        if(selectedItem!=null)setData(selectedItem);
    }

    public void setData(RemainingKeyMoneyTM data){
        StudentRoomDTO studentRoom = data.getStudentRoom();
        StudentDTO student = studentRoom.getStudentDTO();
        txtStudentId.setText(student.getId());
        txtStudentName.setText(student.getName());
        txtAddress.setText(student.getAddress());
        txtContactNo.setText(student.getContact());
        txtStartDate.setText(studentRoom.getFrom().toString());
        txtEndDate.setText(studentRoom.getTo().toString());
        txtRemainingKeyMoney.setText(String.valueOf(data.getFullKeyMoney()-data.getPaidAmount()));
    }

    public void btnPaidOnAction(ActionEvent actionEvent) {
        RemainingKeyMoneyTM item = tblRemainingKeyMoney.getSelectionModel().getSelectedItem();
        double amount = item.getPaidAmount() + Double.parseDouble(txtPayingAmount.getText());
        item.setPaidAmount(amount);
        item.getStudentRoom().setAdvance(amount);
        boolean b = studentRoomService.updateKeyMoney(item.getStudentRoom());
        if(b){
            new Alert(Alert.AlertType.INFORMATION,"Operation Success").show();
            getData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Operation Failed").show();
        }


    }

    public void clear(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtStartDate.clear();
        txtEndDate.clear();
        txtRemainingKeyMoney.clear();
        txtPayingAmount.clear();
    }
}
