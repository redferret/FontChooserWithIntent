package apackage.richard.com.fontchooser;

/**
 * Created by Richard on 3/6/2017.
 */

public class TextColor {

    private int red, green, blue, alpha;

    public TextColor(){
        red = green = blue = 0;
        alpha = 255;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getColor(){
        int color = alpha;
        color <<= 8;  color += red;
        color <<= 8;  color += green;
        color <<= 8;  color += blue;
        return color;
    }
}
