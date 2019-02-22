package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.layout.Border;
import javafx.util.Duration;
import javax.swing.*;
import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;


public class Main extends Application{

    static MediaPlayer mp = null;
    static ListView<File> playlist;
    static int song = 0;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Music Player");
        primaryStage.setResizable(false);


        VBox layout = new VBox();
        ObservableList<File> songs = FXCollections.observableArrayList(Main.getSongs());
        playlist = new ListView<>(songs);
        playlist.setStyle("-fx-pref-height: 650px;");
        playlist.setOnMouseClicked(e -> {

            song = playlist.getSelectionModel().getSelectedIndex();

            playNextMusic();



        });

        Button play = new Button("Play");
        play.setOnAction(e -> playMusic());

        Button pause = new Button("Pause");
        pause.setOnAction(e -> pauseMusic());

        Button stop = new Button("Stop");
        stop.setOnAction(e -> stopMusic());

        Button previous = new Button("Previous");
        previous.setOnAction(e -> previousSong());

        Button next = new Button("Next");
        next.setOnAction(e -> nextSong());


        Slider slider = new Slider();
        slider.setMin(0);


        VBox playerLayout = new VBox();
        HBox playButtons = new HBox();
        playButtons.getChildren().addAll(previous,play,pause,stop,next);



        playerLayout.getChildren().addAll(playButtons,slider);
        layout.getChildren().addAll(playlist, playerLayout);



        Scene s = new Scene(layout, 1000,800);
        s.getStylesheets().add("style.css");
        primaryStage.setScene(s);
        primaryStage.show();
        System.out.println();

    }


    public static ArrayList<File> getSongs() {

        ArrayList<File> songs = new ArrayList<>();

        File musicFolder = new File("C:/Users/kamui/Music");

        File[] songs2 = musicFolder.listFiles();

        for(File f: songs2) {
            if(f.getName().endsWith(".mp3")) {
                String name = f.getName();
                songs.add(f);
            }
        }
        return songs;
    }


    public static void stopMusic() {
        mp.stop();
    }

    public static void playMusic() {
        mp.play();
    }

    public static void pauseMusic() {
        mp.pause();
    }

    public static void previousSong() {
        mp.stop();
        mp = null;
        song-=1;
        Media m = new Media(playlist.getItems().get(song).toURI().toString());
        mp = new MediaPlayer(m);
        mp.play();

    }

    public static void nextSong() {
        mp.stop();
        mp = null;
        song+=1;
        Media m = new Media(playlist.getItems().get(song).toURI().toString());
        mp = new MediaPlayer(m);
        mp.play();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void playNextMusic() {

        if (mp == null) {
            mp = new MediaPlayer(new Media(playlist.getItems().get(song).toURI().toString()));
        }
        else{
            mp.stop();
            mp = null;
            mp = new MediaPlayer(new Media(playlist.getItems().get(song).toURI().toString()));
        }

        mp.play();

        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                playNextMusic();
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {

                }
            }
        });


        t.start();
        song+=1;

    }


}

