/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metricconverterfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

/**This program convert kilometers to miles and meters to yards
 *
 * @author prince
 */
public class FXMLDocumentController implements Initializable {
    
   
    //Variables 
    
    @FXML
    private Label outputLabel;

    @FXML
    private TextField textFieldKm;

    @FXML
    private Label fixedTextLabel;
    
    @FXML
    private Button button;
    
    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem milesConverter;

    @FXML
    private MenuItem metersConverter;
    
    //True for Miles to KM and False for Meters to Yards
    boolean kmToMiles = true;
   
    
    /**
     * This method Handle the menu button
     * */
    @FXML
    private void menuButtonAction(ActionEvent event) {
        
            //Change UI based on what is being convert
           if(event.getSource() == milesConverter) {
            menu.setText("Kilometers to Miles");
            fixedTextLabel.setText("Kilometers");
            textFieldKm.setPromptText("Enter Kilometers");
            outputLabel.setText("Miles");
            kmToMiles = true;
            textFieldKm.clear();
           }else {
            menu.setText("Meters to Yards");
            fixedTextLabel.setText("Meters");
            textFieldKm.setPromptText("Enter Meters");
            outputLabel.setText("Yards");  
            textFieldKm.clear();
            kmToMiles = false;
        }
    }
    /**
     * This method handle the convert button
     * */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //Get the convert button action and do the following conversion
        if(event.getSource() == button){
             try {
                float input = Float.parseFloat(textFieldKm.getText()); //Get the TextFiel Value
                outputLabel.setTextFill(Color.BLACK);
                
                if(kmToMiles) {//If converting from KM to Miles
                     float totalMiles = (float)0.621371 * input; //Multiply user input by the formula
                    outputLabel.setText(String.valueOf(totalMiles) + " Miles");
                    textFieldKm.requestFocus(); //Request focus to the textfield
                }else { //If converting from Meters to Yards
                    float totalMeters = (float)1.09361 * input; //Multiply user input by the formula
                    outputLabel.setText(String.valueOf(totalMeters) + " Yards");
                    textFieldKm.requestFocus(); //Request focus to the textfield
                }
                
            }catch(Exception e){ //Throw an Exception for Invalid input
                outputLabel.setTextFill(Color.RED);
                outputLabel.setText("Invalid Input");
                textFieldKm.setText("Enter Number");
                textFieldKm.requestFocus(); //Request focus to the textfield
            }
        }
        
        
       
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
