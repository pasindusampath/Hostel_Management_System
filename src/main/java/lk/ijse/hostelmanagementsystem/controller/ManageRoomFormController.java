package lk.ijse.hostelmanagementsystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomDTO;
import lk.ijse.hostelmanagementsystem.dto.custom.RoomTypeDTO;
import lk.ijse.hostelmanagementsystem.service.custom.RoomService;
import lk.ijse.hostelmanagementsystem.service.custom.RoomTypeService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.RoomTypeServiceImpl;
import lk.ijse.hostelmanagementsystem.tm.RoomTM;
import lk.ijse.hostelmanagementsystem.util.factory.ServiceFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ServiceType;

import java.util.List;

public class ManageRoomFormController {
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtRoomTypeDescription;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomId;
    public JFXComboBox<RoomTypeDTO> cbRoomType;
    public TableView<RoomTM> tblRoom;
    public TableColumn<RoomTM,String> colRoomId;
    public TableColumn<RoomTM,String> colRoomType;
    public TableColumn<RoomTM,Double> colKeyMoney;


    private RoomTypeService roomTypeService;
    private RoomService roomService;

    public void initialize(){
        roomService= ServiceFactory.getInstance().getService(ServiceType.ROOM);;
        roomTypeService=ServiceFactory.getInstance().getService(ServiceType.ROOM_TYPE);;
        setComboBox();
        visualizeComboBox();
        visualize();
        getDataToTable();
    }

    public void setComboBox(){
        List<RoomTypeDTO> all = roomTypeService.getAll();
        cbRoomType.setItems(FXCollections.observableArrayList(all));
    }

    public void visualizeComboBox(){
        cbRoomType.setConverter(new StringConverter<RoomTypeDTO>() {
            @Override
            public String toString(RoomTypeDTO object) {
                if(object==null)return null;
                return object.getDescription();
            }

            @Override
            public RoomTypeDTO fromString(String string) {
                return null;
            }
        });
    }

    public void btnAddRoomTypeOnAction(ActionEvent actionEvent) {
        RoomTypeDTO roomTypeDTO = collectRoomTypeDto();
        RoomTypeDTO save = roomTypeService.save(roomTypeDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            setComboBox();
        }else {
            new Alert(Alert.AlertType.ERROR,"Data Adding Failed").show();
        }
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = collectRoomData();
        RoomDTO save = roomService.save(roomDTO);
        if(save!=null){
            new Alert(Alert.AlertType.INFORMATION,"Room Added Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Details Adding Failed").show();
        }
    }

    public RoomTypeDTO collectRoomTypeDto(){
        String roomTypeId = txtRoomTypeId.getText();
        String description = txtRoomTypeDescription.getText();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());

        return new RoomTypeDTO(roomTypeId,description,keyMoney,null);
    }

    public RoomDTO collectRoomData(){
        String id = txtRoomId.getText();
        String availability = "Available";
        RoomTypeDTO roomType = cbRoomType.getSelectionModel().getSelectedItem();

        return new RoomDTO(id,availability,roomType,null);
    }

    public void visualize(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
    }

    public void getDataToTable(){
        List<RoomTM> data = roomService.getRoomDataToTable();
        if(data==null)return;
        ObservableList<RoomTM> tblData = FXCollections.observableArrayList(data);
        tblRoom.setItems(tblData);
    }

    public void txtRoomTypeIdOnAction(ActionEvent actionEvent) {
        RoomTypeDTO search = roomTypeService.search(txtRoomTypeId.getText());
        if(search!=null){
            setData(search);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "Check Id And Search Again");
        alert.show();
    }

    private void setData(RoomTypeDTO search) {
        txtRoomTypeDescription.setText(search.getDescription());
        txtKeyMoney.setText(String.valueOf(search.getPrice()));
    }

    public void clear(){
        txtRoomId.clear();
        cbRoomType.getSelectionModel().select(null);
        txtRoomTypeId.clear();
        txtRoomTypeDescription.clear();
        txtKeyMoney.clear();
    }
}
