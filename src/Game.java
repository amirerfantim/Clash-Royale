import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Game {
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane middlePane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private GridPane cardsPane;
    @FXML
    private Button card0;
    @FXML
    private Button card1;
    @FXML
    private Button card2;
    @FXML
    private Button card3;
    @FXML
    private Button next;
    @FXML
    private Label nextLabel;
    //    @FXML
//    private FlowPane flowPaneU;
//    @FXML
//    private FlowPane flowPaneO;
    @FXML
    private ProgressBar elixirBar;

    private User user;
    private double x, y;
    private double elixir;
    private Warrior[][] map;
    //    private Warrior[][] map1;
//    private Warrior[][] map2;
//    private Warrior[][] map3;
//    private Warrior[][] map4;
    private boolean map2IsValid;
    private boolean map3IsValid;
    //    private ImageView[][] imageViews;
    private Card nextCard;
    private int selectedCardIndex;
    private ArrayList<Card> availableCards;
    private ArrayList<Warrior> warriors;

    public void construct(User user) {
        elixir = 4;
//        map1 = new Warrior[18][14];
//        for (int i = 0; i < 18; i++) {
//            for (int j = 0; j < 14; j++) {
//                map1[i][j] = null;
//            }
//        }
//        map2 = new Warrior[9][4];
//        map3 = new Warrior[9][4];
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 4; j++) {
//                map2[i][j] = map3[i][j] = null;
//            }
//        }
//        map4 = new Warrior[18][10];
//        for (int i = 0; i < 18; i++) {
//            for (int j = 0; j < 10; j++) {
//                map1[i][j] = null;
//            }
//        }
        map = new Warrior[18][28];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 28; j++) {
                map[i][j] = null;
            }
        }
        map2IsValid = true;
        map3IsValid = false;
//        imageViews = new ImageView[18][28];
//        for (int i = 0; i < 18; i++) {
//            for (int j = 0; j < 14; j++) {
//                imageViews[i][j] = (ImageView) flowPaneO.getChildren().get(j * 18 + i);
//            }
//            for (int j = 14; j < 28; j++) {
//                imageViews[i][j] = (ImageView) flowPaneU.getChildren().get((j - 14) * 18 + i);
//            }
//        }
        warriors = new ArrayList<>();
        this.user = user;
        availableCards = new ArrayList<>();
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        nextCard = getNextCard();
        selectedCardIndex = -1;
        new Thread(new Updater(this)).start();
    }

    public void update() {
        card0.setStyle("-fx-background-image: url('" + availableCards.get(0).getImage() + "');");
        card1.setStyle("-fx-background-image: url('" + availableCards.get(1).getImage() + "');");
        card2.setStyle("-fx-background-image: url('" + availableCards.get(2).getImage() + "');");
        card3.setStyle("-fx-background-image: url('" + availableCards.get(3).getImage() + "');");
        next.setStyle("-fx-background-image: url('" + nextCard.getImage() + "');");

        if (elixir < 10) elixir += 0.125;
        elixirBar.setProgress(((int) elixir) / 10.0);

//        for (int i = 0; i < 18; i++) {
//            for (int j = 0; j < 28; j++) {
//                if (map[i][j] != null) {
//                    imageViews[i][j].setImage(new Image(map[i][j].getImage()));
//                }
//            }
//        }

        for(Warrior warrior:warriors){
            ImageView imageView=new ImageView(warrior.getImage());
            middlePane.getChildren().add(imageView);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setX(243+warrior.getX()*17.44);
            imageView.setY(warrior.getY()*14.29);
        }
    }

    public void select(ActionEvent event) {
        Button selected = (Button) event.getSource();
        if (selected == card0 && elixir >= availableCards.get(0).getCost()) selectedCardIndex = 0;
        if (selected == card1 && elixir >= availableCards.get(1).getCost()) selectedCardIndex = 1;
        if (selected == card2 && elixir >= availableCards.get(2).getCost()) selectedCardIndex = 2;
        if (selected == card3 && elixir >= availableCards.get(3).getCost()) selectedCardIndex = 3;
    }

    public void click(double x, double y) {
        if (selectedCardIndex == -1) return;
        if (243 <= x && x <= 557 && 240 <= y && y <= 440) {
            Card card = availableCards.get(selectedCardIndex);
            int X = (int) ((x - 243) / 17.44);
            int Y = (int) ((y - 240) / 14.29);
            Warrior warrior = card.getWarrior(user, X, Y);
            map[X][Y + 14] = warrior;
            warriors.add(warrior);
            System.out.println(map[X][Y + 14].getClass().getName() + " at (" + X + "," + (Y + 14) + ")");
            setNextCard(selectedCardIndex);
            elixir -= card.getCost();
            selectedCardIndex = -1;
        }
        if (map2IsValid && 243 <= x && x < 400 && 143 <= y && y <= 200) {
            Card card = availableCards.get(selectedCardIndex);
            int X = (int) ((x - 243) / 17.44);
            int Y = (int) ((y - 143) / 14.29);
            Warrior warrior = card.getWarrior(user, X, Y);
            map[X][Y + 10] = warrior;
            warriors.add(warrior);
            System.out.println(map[X][Y + 10].getClass().getName() + " at (" + X + "," + (Y + 10) + ")");
            setNextCard(selectedCardIndex);
            elixir -= card.getCost();
            selectedCardIndex = -1;
        }
        if (map3IsValid && 400 <= x && x <= 557 && 143 <= y && y <= 200) {
            Card card = availableCards.get(selectedCardIndex);
            int X = (int) ((x - 400) / 17.44);
            int Y = (int) ((y - 143) / 14.29);
            Warrior warrior = card.getWarrior(user, X, Y);
            map[X + 9][Y + 10] = warrior;
            warriors.add(warrior);
            System.out.println(map[X + 9][Y + 10].getClass().getName() + " at (" + (X + 9) + "," + (Y + 10) + ")");
            setNextCard(selectedCardIndex);
            elixir -= card.getCost();
            selectedCardIndex = -1;
        }
//        if (243 <= x && x <= 557 && 240 <= y && y <= 440) {
//            Card card = availableCards.get(selectedCardIndex);
//            int X = (int) ((x - 243) / 17.44);
//            int Y = (int) ((y - 240) / 14.29);
//            map1[X][Y] = card.getWarrior(user);
//            System.out.println(map1[X][Y].getClass().getName() + " at (" + X + "," + Y + ") [1]");
//            setNextCard(selectedCardIndex);
//            selectedCardIndex = -1;
//            elixir -= card.getCost();
//        }
//        if (map2IsValid && 243 <= x && x < 400 && 143 <= y && y <= 200) {
//            Card card = availableCards.get(selectedCardIndex);
//            int X = (int) ((x - 243) / 17.44);
//            int Y = (int) ((y - 143) / 14.29);
//            map2[X][Y] = card.getWarrior(user);
//            System.out.println(map2[X][Y].getClass().getName() + " at (" + X + "," + Y + ") [2]");
//            setNextCard(selectedCardIndex);
//            selectedCardIndex = -1;
//            elixir -= card.getCost();
//        }
//        if (map3IsValid && 400 <= x && x <= 557 && 143 <= y && y <= 200) {
//            Card card = availableCards.get(selectedCardIndex);
//            int X = (int) ((x - 400) / 17.44);
//            int Y = (int) ((y - 143) / 14.29);
//            map3[X][Y] = card.getWarrior(user);
//            System.out.println(map3[X][Y].getClass().getName() + " at (" + X + "," + Y + ") [3]");
//            setNextCard(selectedCardIndex);
//            selectedCardIndex = -1;
//            elixir -= card.getCost();
//        }
    }

    private void setNextCard(int index) {
        availableCards.set(index, nextCard);
        nextCard = getNextCard();
    }

    private Card getNextCard() {
        Card card = user.getRandomCard();
        while (availableCards.contains(card)) {
            card = user.getRandomCard();
        }
        return card;
    }
}
