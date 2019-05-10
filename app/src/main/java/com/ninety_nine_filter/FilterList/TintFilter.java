

package com.ninety_nine_filter.FilterList;

import android.graphics.Color;

/**
 * 色度特效
 */
public class TintFilter extends FIlterBase implements IImageFilter {

	public Image process(Image imageIn)
    {
       int tr = Color.RED;
       int tg = Color.GREEN;
       int tb = Color.BLUE;
       int r,g,b;
       for (int x = 0; x < imageIn.getWidth(); x++) {
           for (int y = 0; y < imageIn.getHeight(); y++) {
                r = (255-imageIn.getRComponent(x, y));
                g = (255-imageIn.getGComponent(x, y));
                b = (255-imageIn.getBComponent(x, y));

                // Convert to gray with constant factors 0.2126, 0.7152, 0.0722
                int gray = (r *  6966 + g * 23436 + b *  2366) >> 15;

                // Apply Tint color
                r = (byte)((gray * tr) >> 8);
                g = (byte)((gray * tg) >> 8);
                b = (byte)((gray * tb) >> 8);

                imageIn.setPixelColor(x,y,r,g,b);
            }
       } 
       return imageIn;
    }
}
