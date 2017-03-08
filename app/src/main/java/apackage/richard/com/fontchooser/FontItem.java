package apackage.richard.com.fontchooser;

import android.graphics.Typeface;

import java.io.Serializable;

/**
 * Created by Richard on 3/6/2017.
 */

public class FontItem {

    private TextColor color = new TextColor();
    private int style = Typeface.NORMAL;
    private int textSize = 20;
    private Typeface type = Typeface.DEFAULT;

    public Typeface getType() {
        return type;
    }

    public void setType(Typeface type) {
        this.type = type;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public TextColor getTextColor() {
        return color;
    }

    public int getStyle() {
        return style;
    }

}
