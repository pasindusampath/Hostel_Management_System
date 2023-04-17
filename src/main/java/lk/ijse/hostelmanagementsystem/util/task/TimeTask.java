package lk.ijse.hostelmanagementsystem.util.task;

import javafx.concurrent.Task;

import java.time.LocalTime;

public class TimeTask extends Task<String> {


    @Override
    protected String call() throws Exception {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //lblTime.setText(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
            updateValue(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond());
        }

    }
}
