
package imclient;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;

/**
 * IPInputWindow class is the first window displayed that is used to get the 
 * server IP address the client wished to communicate with. 
 * @author shane
 */
public class IPInputWindow {
    private static BorderPane pane;
    private static TextField input;
    private static Label label;
    
    public static String serverAddr; // used to store the user inputed server IP address
    
    public static void display(Stage primaryStage)
    {
        pane = new BorderPane();
        input = new TextField();
        label = new Label("Enter server IP Address");

        pane.setTop(label);
        pane.setCenter(input);
        pane.setPadding(new Insets(5, 5, 5, 5));
        
        // Uses a lamba expression to handle the user input. 
        // Event executed once user hits Enter key
        input.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                if(input.getText().isEmpty()) {
                    label.setText("No IP entered");
                    return;
                }
                
                serverAddr = input.getText(); // Non-empty input entered
                
                Window.display(new Stage()); // Execution handed to the Window class
                primaryStage.close();  
            }
        });
        
        primaryStage.setTitle("Instant Messenger");
        primaryStage.setScene(new Scene(pane, 300, 100));
        primaryStage.show();
    }
}
