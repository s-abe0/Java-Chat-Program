
package imclient;

import javafx.stage.Stage;
import javafx.application.Application;

/**
 *
 * @author shane
 */
public class Main extends Application 
{
    @Override
    public void start(Stage primaryStage)
    {        
        IPInputWindow.display(primaryStage); 
    }
    
    public static void main(String args[])
    {
        Application.launch(args);
    }
}
