import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.hostelmanagementsystem.util.FactoryConfiguration;
import lk.ijse.hostelmanagementsystem.util.factory.ViewFactory;
import lk.ijse.hostelmanagementsystem.util.factory.types.ViewType;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/LoadingForm.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(load));
        primaryStage.show();
    }
}
