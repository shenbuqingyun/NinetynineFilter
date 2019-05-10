package com.ninety_nine_filter.FilterList;

/**
 * 反色效果
 */
public class InvertFilter extends FIlterBase implements IImageFilter{

    //@Override
    public Image process(Image imageIn) {
        int r, g, b;
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
                 r = (255-imageIn.getRComponent(x, y));
                 g = (255-imageIn.getGComponent(x, y));
                 b = (255-imageIn.getBComponent(x, y));

                 imageIn.setPixelColor(x,y,r,g,b);
             }
        } 
        return imageIn;
    }

}
