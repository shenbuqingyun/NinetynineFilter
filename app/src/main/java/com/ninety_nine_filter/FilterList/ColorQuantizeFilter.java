package com.ninety_nine_filter.FilterList;

/**
 * 颜色量化特效
 * 类油画
 */
public class ColorQuantizeFilter extends FIlterBase implements IImageFilter{

	private float levels = 5f;

    //@Override
    public Image process(Image imageIn) {
        int r, g, b, a;
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
                 r = imageIn.getRComponent(x, y);
                 g = imageIn.getGComponent(x, y);
                 b = imageIn.getBComponent(x, y);
                 float quanR = (((float) ((int) (r * 0.003921569f * levels))) / levels) * 255f;
                 float quanG = (((float) ((int) (g * 0.003921569f * levels))) / levels) * 255f;
                 float quanB = (((float) ((int) (b * 0.003921569f * levels))) / levels) * 255f;
                 r = (quanR > 255f) ? 255 : ((quanR < 0f) ? 0 : ((int)quanR));
                 g = (quanG > 255f) ? 255 : ((quanG < 0f) ? 0 : ((int) quanG));
                 b = (quanB > 255f) ? 255 : ((quanB < 0f) ? 0: ((int) quanB));
                 imageIn.setPixelColor(x,y,r,g,b);
             }
        } 
        return imageIn;
    }

}
