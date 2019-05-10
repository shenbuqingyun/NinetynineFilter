package com.ninety_nine_filter.FilterList;

/**
 * 油画效果
 * 油彩 油画效果
 */
public class OilPaintFilter extends FIlterBase implements IImageFilter{

	public int Model = 3;
    
    //@Override
    public Image process(Image imageIn) {
       int width = imageIn.getWidth();
       int height = imageIn.getHeight();
       Image clone = imageIn.clone();
       int r = 0, g = 0, b = 0, xx = 0, yy = 0;
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	  int pos = NoiseFilter.getRandomInt(1, 10000) % Model;
            	  xx = (x + pos) < width ? (x + pos) : (x - pos) >= 0 ? (x - pos) : x;
            	  yy = (y + pos) < height ? (y + pos) : (y - pos) >= 0 ? (y - pos) : y;  
            	  r = clone.getRComponent(xx, yy);
            	  g = clone.getGComponent(xx, yy);
            	  b = clone.getBComponent(xx, yy);
	        	  imageIn.setPixelColor(x, y, r, g, b);
             }
        } 
        return imageIn;     
    }
}