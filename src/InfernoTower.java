import javafx.scene.image.ImageView;

public class InfernoTower extends Building {
    public InfernoTower(User user, int x, int y) {
        this.arrayX = x;
        this.arrayY = y;
        switch (user.getLevel()) {
            case 1:
                hp = 800;
                damage = 20;
                break;
            case 2:
                hp = 880;
                damage = 22;
                break;
            case 3:
                hp = 968;
                damage = 24;
                break;
            case 4:
                hp = 1064;
                damage = 26;
                break;
            case 5:
                hp = 1168;
                damage = 29;
        }
        hitSpeed = 0.4;
        target = 2;
        range = 6;
        lifetime = 40;
        imageBlue = "sprites/InfernoTowerBlue.png";
        imageRed = "sprites/InfernoTowerRed.png";
        imageView.setFitWidth(50);
        imageView.setFitHeight(75);
    }

    @Override
    public String toString() {
        return "InfernoTower";
    }
}
