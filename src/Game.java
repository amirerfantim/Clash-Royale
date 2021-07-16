import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private FlowPane flowPaneU;
    @FXML
    private FlowPane flowPaneO;

    private User user;
    private double x, y;
    private Warrior[][] map1;
    private Warrior[][] map2;
    private Warrior[][] map3;
    private Warrior[][] map4;
    private ImageView[][] imageViews;
    private Card nextCard;
    private int selectedCardIndex;
    private ArrayList<Card> availableCards;

    public void construct(User user) {
        map1 = new Warrior[18][14];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 14; j++) {
                map1[i][j] = null;
            }
        }
        map2 = new Warrior[9][4];
        map3 = new Warrior[9][4];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                map2[i][j] = map3[i][j] = null;
            }
        }
        map4 = new Warrior[18][10];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 10; j++) {
                map1[i][j] = null;
            }
        }
        imageViews = new ImageView[18][28];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 14; j++) {
                imageViews[i][j] = (ImageView) flowPaneO.getChildren().get(i * 14 + j);
            }
            for (int j = 14; j < 28; j++) {
                imageViews[i][j] = (ImageView) flowPaneU.getChildren().get(i * 14 + j - 14);
            }
        }
        this.user = user;
        availableCards = new ArrayList<>();
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        availableCards.add(getNextCard());
        nextCard = getNextCard();
        selectedCardIndex = -1;
        update();
    }

    public void update() {
        card0.setStyle("-fx-background-image: url('" + availableCards.get(0).getImage() + "');");
        card1.setStyle("-fx-background-image: url('" + availableCards.get(1).getImage() + "');");
        card2.setStyle("-fx-background-image: url('" + availableCards.get(2).getImage() + "');");
        card3.setStyle("-fx-background-image: url('" + availableCards.get(3).getImage() + "');");
        next.setStyle("-fx-background-image: url('" + nextCard.getImage() + "');");

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 14; j++) {
                if (map1[i][j] != null) {
                    imageViews[i][j].setImage(new Image(map1[i][j].getImage()));
                }
            }
        }
    }

    public void select(ActionEvent event) {
        Button selected = (Button) event.getSource();
        if (selected == card0) selectedCardIndex = 0;
        if (selected == card1) selectedCardIndex = 1;
        if (selected == card2) selectedCardIndex = 2;
        if (selected == card3) selectedCardIndex = 3;
    }

    public void click(double x, double y) {
        if (selectedCardIndex != -1 && 243 <= x && x <= 557 && 240 <= y && y <= 440) {
            Card card = availableCards.get(selectedCardIndex);
            int X = (int) ((x - 243) / 17.44);
            int Y = (int) ((y - 240) / 14.29);
            map1[X][Y] = card.getWarrior(user);
            System.out.println(map1[X][Y].getClass().getName() + " at (" + X + "," + Y + ")");
            setNextCard(selectedCardIndex);
        }
        update();
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
