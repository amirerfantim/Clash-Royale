import javafx.scene.image.ImageView;

public class Cannon extends Building {
    public Cannon(User user, int x, int y) {
        this.arrayX = x;
        this.arrayY = y;
        switch (user.getLevel()) {
            case 1:
                hp = 380;
                damage = 60;
                break;
            case 2:
                hp = 418;
                damage = 66;
                break;
            case 3:
                hp = 459;
                damage = 72;
                break;
            case 4:
                hp = 505;
                damage = 79;
                break;
            case 5:
                hp = 554;
                damage = 87;
        }
        hitSpeed = 0.8;
        target = 0;
        range = 5.5;
        lifetime = 30;
        image = "sprites/CannonBlue.png";

        imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        mapX = arrayX * 17.44 + 25;
        mapY = arrayY * 14.29 + 240 - 10;
        imageView.setX(mapX);
        imageView.setY(mapY);
    }

    @Override
    public String toString() {
        return "Cannon";
    }
}
