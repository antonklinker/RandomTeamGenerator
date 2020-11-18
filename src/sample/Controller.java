package sample;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    //public AnchorPane potbackground;
    public Button resetbutton;
    public Button addToEveryone;
    public Slider teamsamountSlider;
    public Label numberofteams;
    public AnchorPane teamsbackground;
    public Canvas background;
    public GraphicsContext gc;
    public Button exitButton;
    public AnchorPane dragBar;
    public double currentX, currentY;


    //AnchorPane[] teamBackgrounds = new AnchorPane[4];
    AnchorPane[] teamBackgrounds;
    //Label[] playerDisplays = new Label[8];
    Label[] playerDisplays;
    TextField[] amountOfPlayers;
    ArrayList<String> everyone = new ArrayList<>();
    ArrayList<String> addedPeople;
    //List[] teams = new List[100];
    List[] teams;
    int amountOfPeople;

    public void initialize() {
        gc = background.getGraphicsContext2D();
        playersamounttext.setText("2");
        mybackground.getChildren().remove(team1background);
        mybackground.getChildren().remove(team2background);
        gc.setFill(Color.DARKBLUE);
        gc.setGlobalAlpha(0.3);
        for (int i=0; i<(int)background.getHeight(); i++) {
            gc.fillRect(0, i+(i*10), background.getWidth(), 1);
            gc.fillRect(i+(i*10), 0, 1, background.getHeight());
        }

    }

    /*public void generateTeams(ActionEvent actionEvent) throws Exception {
        if (everyone.size()>1) {
            realGenerate();
            everyone.clear();
        } else {
            System.out.println("You need to have at least 2 people to generate teams");
        }
    }*/

    public void realGenerate() {
        try {
            teamsbackground.getChildren().clear();
            addToEveryone.setText("NEW TEAMS");
            int amountOfTeams = (int)teamsamountSlider.getValue();


            while ((double)everyone.size()%amountOfTeams!=0) {
                everyone.add("--");
                amountOfPeople++;
            }
            if (everyone.size()%amountOfTeams==0) {
                teamBackgrounds = new AnchorPane[amountOfTeams];
                playerDisplays = new Label[amountOfPeople];
                teams = new List[amountOfTeams];
                for (int i = 0; i < amountOfTeams; i++) {
                    teamBackgrounds[i] = new AnchorPane();
                    teamBackgrounds[i].setPrefSize(92, 246);
                    if (i%2==0) {
                        teamBackgrounds[i].setStyle("-fx-border-color:BLUE; -fx-background-color: #090364");
                    } else {
                        teamBackgrounds[i].setStyle("-fx-border-color:BLUE; -fx-background-color: #090541");
                    }
                    if (i<5) {
                        teamBackgrounds[i].setLayoutX(0 + (100 * i));
                        teamBackgrounds[i].setLayoutY(0);
                    } else {
                        teamBackgrounds[i].setLayoutX(0 + (100 * (i-5)));
                        teamBackgrounds[i].setLayoutY(252);
                    }
                    teamsbackground.getChildren().add(teamBackgrounds[i]);
                    teams[i] = new ArrayList<String>();
                }


                for (int i = 0; i < amountOfTeams; i++) {
                    Label header = new Label();
                    Label underscore = new Label();
                    header.setText("Team " + (i + 1));
                    header.setLayoutY(6);
                    header.setLayoutX(27);
                    if (i==9) {
                        header.setLayoutX(26);
                    }
                    underscore.setText("__________________");
                    underscore.setLayoutY(12);
                    underscore.setLayoutX(2);
                    underscore.setStyle("-fx-text-fill: #1696ff;");
                    header.setStyle("-fx-text-fill: #1696ff;");
                    teamBackgrounds[i].getChildren().addAll(header, underscore);
                    for (int j = 0; j < amountOfPeople / amountOfTeams; j++) {
                        int randNumber = (int) (Math.random() * everyone.size());
                        teams[i].add(everyone.get(randNumber));
                        //addedPeople.add("everyone.get(0)");
                        everyone.remove(randNumber);
                        playerDisplays[j] = new Label();
                        playerDisplays[j].setLayoutX(10);
                        playerDisplays[j].setLayoutY(30 + (20 * j));
                        //playerDisplays[j].setTextFill(Color.BLUE);
                        playerDisplays[j].setStyle("-fx-text-fill: #1696ff");
                        playerDisplays[j].setText((String) teams[i].get(j));
                        teamBackgrounds[i].getChildren().add(playerDisplays[j]);
                    }
                }
                everyone.clear();
            }
            else {
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    public void addPeople(ActionEvent actionEvent) {
        try {
            //potbackground.getChildren().clear();
            for (int i = 0; i < amountOfPlayers.length; i++) {
                try {

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
            /*for (int i = 0; i < everyone.size(); i++) {
                Label potman = new Label();
                potman.setLayoutX(56);
                potman.setLayoutY(30 + (15*i));
                potman.setText(everyone.get(i));
                potbackground.getChildren().add(potman);
            }*/
            if (everyone.size()>1) {
                realGenerate();
            } else {
                System.out.println("You need to have at least 2 people to generate teams");
            }
        }
        catch (NullPointerException e) {
            System.out.println(e);

        }
    }



    public void setAmountText(MouseEvent mouseEvent) {
        playersamounttext.setText(String.valueOf((int)playersamount.getValue()));
        add_TextFields();
    }




    public void add_TextFields() {
        mybackground.getChildren().clear();


        amountOfPlayers = new TextField[(int)playersamount.getValue()];


        for (int i=0; i<(int)playersamount.getValue(); i++) {
            amountOfPlayers[i] = new TextField();
            amountOfPlayers[i].setStyle("-fx-border-color: BLUE; -fx-control-inner-background: #090364; -fx-text-fill: #1696ff");
            amountOfPlayers[i].setLayoutX(14);
            amountOfPlayers[i].setLayoutY(20 + (30*i));
            mybackground.getChildren().add(amountOfPlayers[i]);
        }

        try {


            addToEveryone = new Button();
            addToEveryone.setStyle("-fx-border-color: BLUE; -fx-background-color: #020139; -fx-text-fill: #1696ff");
            //addToEveryone.setTextFill(Color.BLUE);
            addToEveryone.setLayoutX(14);
            addToEveryone.setLayoutY(20 + (30 * amountOfPlayers.length));
            addToEveryone.setPrefWidth(150);
            addToEveryone.setText("GENERATE");
            addToEveryone.setOnAction(this::addPeople);
            mybackground.getChildren().add(addToEveryone);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    public void setTeamsAmount(MouseEvent mouseEvent) {
        numberofteams.setText(String.valueOf((int)teamsamountSlider.getValue()));
    }

    public void closeApp(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    public void moveApp(MouseEvent mouseEvent) {
        Stage stage = (Stage) dragBar.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX()-currentX);
        stage.setY(mouseEvent.getScreenY()-currentY);
    }

    public void recordMousePosition(MouseEvent mouseEvent) {
        currentX=mouseEvent.getX();
        currentY=mouseEvent.getY();

    }
}
