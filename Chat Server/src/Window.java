
package server;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;

/**
 * Window class is used to create an interface for the IM service
 * @author shane
 */
public class Window {
    private static BorderPane outerPane;
    private static BorderPane messagePane;
    private static TextField userInput;
    private static TextArea dialog;
    private static String input;
    
    /**
     * Sets up the window interface
     * @param primaryStage 
     */
    public static void display(Stage primaryStage) {
        outerPane = new BorderPane();
        outerPane.setPadding(new Insets(5, 5, 5, 5));
        
        messagePane = new BorderPane();
        messagePane.setPadding(new Insets(5, 5, 5, 5));
        
        userInput = new TextField();
        
        dialog = new TextArea();
        dialog.setEditable(false);

        messagePane.setCenter(dialog);
   
        outerPane.setTop(userInput);
        outerPane.setCenter(messagePane);
        
        // each time the user hits the Enter key, 
        // the message is sent using IMServer's sendPacket method
        userInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                input = userInput.getText();
                IMServer.sendPacket(input);
                userInput.clear();
            }
        });
        
        primaryStage.setTitle("Instant Messenger");
        primaryStage.setScene(new Scene(outerPane, 400, 400));
        primaryStage.show();
    }
    
    /**
     * Used to display content on the dialog board.
     * @param message message to be displayed
     */
    public static void handleDialogEvent(String message) {
        dialog.appendText(message + "\n");
    }
}

