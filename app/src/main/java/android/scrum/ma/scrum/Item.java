package android.scrum.ma.scrum;

import android.graphics.Bitmap;

/**
 * Created by HP on 28/12/2017.
 */

public class Item {
    String title;
    Bitmap Image;

    public Item( Bitmap image,String title) {
        this.title = title;
        Image = image;
    }

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }
}
