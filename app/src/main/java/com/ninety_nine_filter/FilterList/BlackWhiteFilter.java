package com.ninety_nine_filter.FilterList;

/**
 * black and white
 */
public class BlackWhiteFilter extends FIlterBase  implements IImageFilter {

    //@Override
    public Image process(Image imageIn) {
        int r,g,b,corfinal;
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
                    r = imageIn.getRComponent(x, y);
                    g = imageIn.getGComponent(x, y);
                    b = imageIn.getBComponent(x, y);
                    corfinal = (int)((r*0.3)+(b*0.59)+(g*0.11));
                    imageIn.setPixelColor(x,y,corfinal,corfinal,corfinal);
            }
        }
        return imageIn;
    }

}

