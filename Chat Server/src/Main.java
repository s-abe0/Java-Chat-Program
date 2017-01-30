
package server;

import javafx.stage.Stage;
import javafx.application.Application;

/**
 * 
 * @author shane
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Window.display(primaryStage);
        IMServer.startServer();
    }
    
    public static void main(String args[])
    {
        Application.launch(args);
    }
    
}
