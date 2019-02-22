package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller{

    @FXML
    ObservableList<File> songs = FXCollections.observableArrayList(Main.getSongs());
    @FXML
    ListView<File> SongList = new ListView<>(songs);


}
