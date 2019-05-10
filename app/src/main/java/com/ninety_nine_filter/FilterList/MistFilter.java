package com.ninety_nine_filter.FilterList;

/**
 * 雾化效果
 */
public class MistFilter extends FIlterBase implements IImageFilter{
	 
    //@Override
    public Image process(Image imageIn) {
       int width = imageIn.getWidth();
       int height = imageIn.getHeight();
       Image clone = imageIn.clone();
       int r = 0, g = 0, b = 0;
       for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	  int k = NoiseFilter.getRandomInt(1, 123456);
            	  //���ؿ��С
            	  int dx = x + k % 19;
            	  int dy = y + k % 19;
            	  if(dx >= width) {
            		  dx = width -1;   				  
            	  }
            	  if(dy >= height) {
            		  dy = height -1;   				  
            	  }
            	  r = clone.getRComponent(dx, dy);
            	  g = clone.getGComponent(dx, dy);
            	  b = clone.getBComponent(dx, dy);
            	  imageIn.setPixelColor(x, y, r, g, b);
             }
        } 
        return imageIn;     
    }
}