
package imclient;


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
public class Window 
{ 
    private static BorderPane outerPane; // outer outerPane; holds userInput and the messagePane
    private static BorderPane messagePane; // outerPane within outer outerPane; holds text area and scroll bar
    private static TextField userInput; // Where the user types a message
    private static TextArea dialog; // area where messages between clients and server is displayed
    private static String input;
    
    /**
     * Start the client and display the window interface
     * @param primaryStage Stage object
     */
    public static void display(Stage primaryStage)
    {
        outerPane = new BorderPane();
        outerPane.setPadding(new Insets(5, 5, 5, 5));
        
        messagePane = new BorderPane();
        messagePane.setPadding(new Insets(5, 5, 5, 5));
        
        userInput = new TextField();
        
        dialog = new TextArea();
        dialog.setEditable(false); // displayed messages are not editable
        
        messagePane.setCenter(dialog);
        
        outerPane.setTop(userInput);
        outerPane.setCenter(messagePane);
        
        // Lamba expression used to handle input action event.
        // When user hits Enter key, the sendPacket method of IMClient class
        // is used to send a message. 
        userInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                input = userInput.getText();
                IMClient.sendPacket(input);
                userInput.clear();
            }
        });
        
        // starts the client and displays the window
        IMClient.startClient(); 
        primaryStage.setTitle("Instant Messenger");
        primaryStage.setScene(new Scene(outerPane, 400, 400));
        primaryStage.show(); 
    }
    
    /**
     * This method is used to display content on the dialog board. 
     * @param message Text to be displayed. 
     */
    public static void handleDialogEvent(String message)
    {
        dialog.appendText(message + "\n");  
    }
}
