package lk.ijse.hostelmanagementsystem.util.task;

import javafx.concurrent.Task;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.ViewFactory;

public class ApplicationStartTask extends Task<String> {
    @Override
    protected String call() throws Exception {
        updateProgress(20, 100);
        updateValue("Establishing Hibernate Framework....");
        FactoryConfiguration.getInstance();
        updateProgress(40, 100);
        updateValue("Loading User Interfaces....");
        ViewFactory.getInstance();
        updateProgress(60, 100);
        Thread.sleep(500);
        updateValue("Welcome");
        updateProgress(70, 100);
        Thread.sleep(500);
        updateValue("Welcome To");
        updateProgress(80, 100);
        Thread.sleep(500);
        updateValue("Welcome To D24");
        Thread.sleep(500);
        updateValue("Welcome To D24 Hostel");
        updateProgress(90, 100);
        Thread.sleep(500);
        updateValue("Welcome To D24 Hostel Management");
        Thread.sleep(500);
        updateValue("Welcome To D24 Hostel Management System");
        updateProgress(99, 100);
        Thread.sleep(500);
        updateValue("Starting Application.");
        Thread.sleep(500);
        updateValue("Starting Application..");
        Thread.sleep(500);
        updateValue("Starting Application...");
        updateProgress(100, 100);
        return "Start";
    }
}
