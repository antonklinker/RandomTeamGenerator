package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public TextField teamsamount;
    public Button teamsbutton;
    public Button playersbutton;
    public Slider playersamount;
    public Label playersamounttext;
    public AnchorPane mybackground;
    public AnchorPane team1background;
    public AnchorPane team2background;
    public AnchorPane potbackground;
    public Button resetbutton;
    public Button addToEveryone;


    //AnchorPane[] teamBackgrounds = new AnchorPane[4];
    AnchorPane[] teamBackgrounds;
    //Label[] playerDisplays = new Label[8];
    Label[] playerDisplays;
    TextField[] amountOfPlayers;
    ArrayList<String> everyone = new ArrayList<>();
    //List[] teams = new List[100];
    List[] teams;
    int amountOfPeople;

    public void initialize() {
        playersamounttext.setText("2");
        mybackground.getChildren().remove(team1background);
        mybackground.getChildren().remove(team2background);
    }

    public void generateTeams(ActionEvent actionEvent) throws Exception {
        if (everyone.size()>1) {
            realGenerate();
            everyone.clear();
        } else {
            System.out.println("You need to have at least 2 people to generate teams");
        }
    }

    public void realGenerate() {
        try {
            int amountOfTeams = Integer.parseInt(teamsamount.getText());
            teamBackgrounds = new AnchorPane[amountOfTeams];
            playerDisplays = new Label[amountOfPeople];
            teams = new List[amountOfTeams];
            if (everyone.size()%amountOfTeams==0) {
                for (int i = 0; i < amountOfTeams; i++) {
                    teamBackgrounds[i] = new AnchorPane();
                    teamBackgrounds[i].setLayoutX(230 + (100 * i));
                    teamBackgrounds[i].setLayoutY(290);
                    teamBackgrounds[i].setPrefSize(92, 246);
                    teamBackgrounds[i].setStyle("-fx-border-color:#9361; -fx-background-color: #666;");
                    mybackground.getChildren().add(teamBackgrounds[i]);
                    teams[i] = new ArrayList<String>();
                }


                for (int i = 0; i < amountOfTeams; i++) {
                    Label header = new Label();
                    header.setText("Team " + (i + 1));
                    header.setLayoutY(15);
                    header.setLayoutX(15);
                    teamBackgrounds[i].getChildren().add(header);
                    for (int j = 0; j < amountOfPeople / amountOfTeams; j++) {
                        int randNumber = (int) (Math.random() * everyone.size());
                        teams[i].add(everyone.get(randNumber));
                        everyone.remove(randNumber);
                        System.out.println("Team " + i + " has " + teams[i].get(j));
                        playerDisplays[j] = new Label();
                        playerDisplays[j].setLayoutX(17);
                        playerDisplays[j].setLayoutY(30 + (20 * j));
                        playerDisplays[j].setText((String) teams[i].get(j));
                        teamBackgrounds[i].getChildren().add(playerDisplays[j]);
                    }
                }
            }
            else {
                System.out.println("The teams need to be equal");
            }
        } catch (NumberFormatException e){
            System.out.println("You need to input a number");
        }
    }



    public void addTextFields(ActionEvent actionEvent) {

        amountOfPlayers = new TextField[(int)playersamount.getValue()];
        for (int i=0; i<(int)playersamount.getValue(); i++) {
            amountOfPlayers[i] = new TextField();
            amountOfPlayers[i].setLayoutX(40);
            amountOfPlayers[i].setLayoutY(160 + (30*i));
            mybackground.getChildren().add(amountOfPlayers[i]);
        }

        try {


            addToEveryone = new Button();
            addToEveryone.setLayoutX(40);
            addToEveryone.setLayoutY(160 + (30 * amountOfPlayers.length));
            addToEveryone.setPrefWidth(150);
            addToEveryone.setText("Add the people");
            addToEveryone.setOnAction(this::addPeople);
            mybackground.getChildren().add(addToEveryone);
        }
        catch (Exception e ){
            System.out.println("Something went wrong");
        }

    }

    public void addPeople(ActionEvent actionEvent) {
        try {
            potbackground.getChildren().clear();
            for (int i = 0; i < amountOfPlayers.length; i++) {
                try {

                    //System.out.println(amountOfPlayers[i].getText());
                    if (!amountOfPlayers[i].getText().equals("")) {
                        //System.out.println(i + " was empty and has been removed");
                        //everyone.remove(i);
                        everyone.add(amountOfPlayers[i].getText());
                    }
                } catch (NullPointerException | IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
            amountOfPeople = everyone.size();
            for (int i = 0; i < everyone.size(); i++) {
                /*potpeople[i] = new Label();
                potpeople[i].setLayoutX(56);
                potpeople[i].setLayoutY(30 + (15*i));
                potpeople[i].setText(everyone.get(i));
                potbackground.getChildren().add(potpeople[i]);
                System.out.println(everyone.get(i));*/
                Label potman = new Label();
                potman.setLayoutX(56);
                potman.setLayoutY(30 + (15*i));
                potman.setText(everyone.get(i));
                potbackground.getChildren().add(potman);
            }
        }
        catch (NullPointerException e) {
            System.out.println(e);

        }
    }



    public void setAmountText(MouseEvent mouseEvent) {
        playersamounttext.setText(String.valueOf((int)playersamount.getValue()));
    }

    public void resetprogram(ActionEvent actionEvent) {
        try {
            addToEveryone.setVisible(false);
        for (int i=0; i<amountOfPlayers.length; i++) {
            mybackground.getChildren().remove(amountOfPlayers[i]);
        }

        System.out.println("NOT WORKING");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
