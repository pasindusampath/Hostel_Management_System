package lk.ijse.hostelmanagementsystem.controller;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import lk.ijse.hostelmanagementsystem.service.custom.JoinService;
import lk.ijse.hostelmanagementsystem.service.custom.StudentRoomService;
import lk.ijse.hostelmanagementsystem.service.custom.impl.JoinServiceImpl;
import lk.ijse.hostelmanagementsystem.service.custom.impl.StudentRoomServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HomeFormController {

    public Label lbl1RoomCount;
    public Label lbl2RoomCount;
    public Label lbl3RoomCount;
    public Label lbl4RoomCount;
    public LineChart lineChart;



    private JoinService joinService;
    private final StudentRoomService studentRoomService=new StudentRoomServiceImpl();
    public void initialize(){
        joinService = new JoinServiceImpl();
        HashMap<String, Integer> list = joinService.getAvailableRoomCount();
        lbl1RoomCount.setText(String.valueOf(list.get("RT-001")));
        lbl2RoomCount.setText(String.valueOf(list.get("RT-002")));
        lbl3RoomCount.setText(String.valueOf(list.get("RT-003")));
        lbl4RoomCount.setText(String.valueOf(list.get("RT-004")));

        setChart();
    }

    public void setChart(){
        HashMap<String, Double> monthlyIncome = studentRoomService.getMonthlyIncome();
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(String.valueOf(LocalDate.now().getYear()));

        for(int i = 0; i <12 ; i++){
            String ar = Month.of(i + 1).getDisplayName(TextStyle.FULL, new Locale("en"));
            Double aDouble = monthlyIncome.get(String.valueOf(i+1));
            if(aDouble!=null){
                dataSeries1.getData().add(new XYChart.Data( ar, aDouble));
            }else {
                dataSeries1.getData().add(new XYChart.Data( ar, 0.0));
            }
        }

        /*dataSeries1.getData().add(new XYChart.Data( "1", 567));
        dataSeries1.getData().add(new XYChart.Data( "2", 612));
        dataSeries1.getData().add(new XYChart.Data("3", 800));
        dataSeries1.getData().add(new XYChart.Data("4", 780));
        dataSeries1.getData().add(new XYChart.Data("5", 810));
        dataSeries1.getData().add(new XYChart.Data("6", 850));*/

        lineChart.getData().add(dataSeries1);
    }
}
