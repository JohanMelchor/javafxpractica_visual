package co.edu.uniquindio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mensajeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEnviar;

    @FXML
    private ChoiceBox<String> chMensaje;

    @FXML
    private Label textoEstado;

    @FXML
    private TextField txtMensaje;

    @FXML
    void OnEnviar(ActionEvent event) {
        String mensaje = txtMensaje.getText();
        String opcion = chMensaje.getValue();
        confirmarMensaje(mensaje,opcion);
    }

    private void confirmarMensaje(String mensaje,String opcion){
        if (opcion == null || opcion.isEmpty()) {
            textoEstado.setText("No ha seleccionado una opción");
            return; 
        }
        if(mensaje == null || mensaje.isEmpty()){
            textoEstado.setText("No se ha escrito un mensaje");
            return;
        }

        if(opcion.equalsIgnoreCase("Email")){
            textoEstado.setText("Tipo de notificacion: "+opcion+"\nmensaje: "+mensaje);
            }else{
            textoEstado.setText("Tipo de notificacion: "+opcion+"\nmensaje: "+mensaje);
        }
    }

    @FXML
    void initialize() {
        chMensaje.getItems().addAll("Email","Sms");
    }

}

