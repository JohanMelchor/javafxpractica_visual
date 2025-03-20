package co.edu.uniquindio.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.model.Habitacion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class crudHabitacionController {
    ObservableList<Habitacion> listaHabitaciones = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> ChDisponibilidad;

    @FXML
    private ChoiceBox<String> ChTipoHabitacion;

    @FXML
    private TableView<Habitacion> tableHabitaciones;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Habitacion, String> tbDescripcion;

    @FXML
    private TableColumn<Habitacion, String> tbDisponibilidad;

    @FXML
    private TableColumn<Habitacion, String> tbNumero;

    @FXML
    private TableColumn<Habitacion, String> tbPrecio;

    @FXML
    private TableColumn<Habitacion, String> tbTipoHabitacion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtPrecio;

    @FXML
    void OnActualizar(ActionEvent event) {
        actualizarHabitacion();
    }

    private void actualizarHabitacion() {
        if (!validarCampos()) {
            return;
        }
        String numero = txtNumero.getText();
        Habitacion habitacion = crearHabitacion(txtNumero.getText(), ChTipoHabitacion.getValue(), txtPrecio.getText(), ChDisponibilidad.getValue(), txtDescripcion.getText());
        for(Habitacion h : listaHabitaciones){
            if(h.getNumero().equals(numero)){
            listaHabitaciones.set(listaHabitaciones.indexOf(h), habitacion);
            }
        }
    }

    @FXML
    void OnAgregar(ActionEvent event) {
        agregarHabitacion();
    }

    private void agregarHabitacion(){
        if (!validarCampos()) {
            return;
        }
        String numero = txtNumero.getText();
        if (habitacionExistente(numero)) {
            mostrarAlerta("Error", "Ya hay una habitacion con ese número, no se puede agregar");
            return;
        }
        Habitacion habitacion = crearHabitacion(txtNumero.getText(), ChTipoHabitacion.getValue(), txtPrecio.getText(), ChDisponibilidad.getValue(), txtDescripcion.getText());
        listaHabitaciones.add(habitacion);
    }

    private Habitacion crearHabitacion(String numero, String tipoHabitacion, String precio, String disponibilidad, String descripcion){
        return new Habitacion(numero, tipoHabitacion, precio, disponibilidad, descripcion);
    }

    private boolean habitacionExistente(String numero) {
        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getNumero().equalsIgnoreCase(numero)) {
                return true;
            }
        }
        return false;
    }
    @FXML
    void OnEliminar(ActionEvent event) {
        eliminarHabitacion();
    }

    private void eliminarHabitacion(){
        if (!validarCampos()) {
            return;
        }
        String numero = txtNumero.getText();
        for(Habitacion habitacion : listaHabitaciones){
            if(habitacion.getNumero().equalsIgnoreCase(numero)){
                listaHabitaciones.remove(habitacion);
                break;
            }
        }
    }

    @FXML
    void initialize() {
        initDataBinding();
        Habitacion habitacion1 = new Habitacion();
        Habitacion habitacion2 = new Habitacion();
        Habitacion habitacion3 = new Habitacion();

        habitacion1.setNumero("101");
        habitacion1.setDescripcion("Habitacion 1");
        habitacion1.setPrecio("100000");
        habitacion1.setDisponibilidad("Disponible");
        habitacion1.setTipoHabitacion("Sencilla");

        habitacion2.setNumero("102");
        habitacion2.setDescripcion("Habitacion 2");
        habitacion2.setPrecio("200000");
        habitacion2.setDisponibilidad("Reservada");
        habitacion2.setTipoHabitacion("Doble");

        habitacion3.setNumero("103");
        habitacion3.setDescripcion("Habitacion 3");
        habitacion3.setPrecio("300000");
        habitacion3.setDisponibilidad("Ocupada");
        habitacion3.setTipoHabitacion("Triple");

        listaHabitaciones.add(habitacion1);
        listaHabitaciones.add(habitacion2);
        listaHabitaciones.add(habitacion3);

        tableHabitaciones.getItems().clear();
        tableHabitaciones.setItems(listaHabitaciones);

        ChDisponibilidad.getItems().addAll("Disponible","Reservada","Ocupada");
        ChTipoHabitacion.getItems().addAll("Sencilla","Doble","Triple","Suite");
    }

    private void initDataBinding() {
        tbNumero.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNumero()));
        tbDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        tbPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrecio()));
        tbDisponibilidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisponibilidad()));
        tbTipoHabitacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoHabitacion()));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
    }

    private boolean validarCampos() {
        if (txtNumero.getText() == null || txtNumero.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "El número de habitación no puede estar vacío.");
            return false;
        }
        try {
            Integer.parseInt(txtNumero.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El número de habitación debe ser un número válido.");
            return false;
        }
        if (Integer.parseInt(txtNumero.getText()) <= 0) {
            mostrarAlerta("Error", "El número de habitación no puede ser negativo");
            return false;
        }
        if (txtNumero.getText().length() != 3) {
            mostrarAlerta("Error", "El número de habitación debe tener exactamente 3 dígitos.");
            return false;
        }
        if (txtDescripcion.getText() == null || txtDescripcion.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "La descripción no puede estar vacía.");
            return false;
        }
        if (txtDescripcion.getText() == null || txtDescripcion.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "La descripción no puede estar vacía.");
            return false;
        }
        if (txtPrecio.getText() == null || txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "El precio no puede estar vacío.");
            return false;
        }
        try {
            Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio debe ser un número válido.");
            return false;
        }
        if (Integer.parseInt(txtPrecio.getText()) <= 0) {
            mostrarAlerta("Error", "El precio no puede ser negativo");
            return false;
        }
        if (ChTipoHabitacion.getValue() == null || ChTipoHabitacion.getValue().trim().isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de habitación.");
            return false;
        }
        if (ChDisponibilidad.getValue() == null || ChDisponibilidad.getValue().trim().isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar una disponibilidad.");
            return false;
        }
        return true;
    }

}