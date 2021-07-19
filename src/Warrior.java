import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Warrior {
    protected int arrayX;
    protected int arrayY;
    protected double mapX;
    protected double mapY;
    protected String imageBlue;
    protected String imageRed;
    protected ImageView imageView;
    protected boolean isAlive = true;


    public int getArrayY() {
        return arrayY;
    }

    public int getArrayX() {
        return arrayX;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setArrayX(int arrayX) {
        this.arrayX = arrayX;
    }

    public void setArrayY(int arrayY) {
        this.arrayY = arrayY;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void buildImageView(String color) {
        if(color.equals("blue"))
            imageView.setImage(new Image(imageBlue));
        if(color.equals("red"))
            imageView.setImage(new Image(imageRed));
        mapX = arrayX * 17.44 + 17.44;
        mapY = arrayY * 14.29 + 220;
        imageView.setX(mapX);
        imageView.setY(mapY);
    }


}
