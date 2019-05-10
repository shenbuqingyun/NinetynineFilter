package com.ninety_nine_filter.FilterList;

/**
 * 浮雕特效
 */
public class ReliefFilter extends FIlterBase implements IImageFilter{

    //@Override
    public Image process(Image imageIn) {
       for (int x = 0; x < (imageIn.getWidth() - 1); x++) {
	        for (int y = 0; y < imageIn.getHeight(); y++) {
		        int rr = imageIn.getRComponent(x, y) - imageIn.getRComponent(x + 1, y) + 128;
		        int gg = imageIn.getGComponent(x, y) - imageIn.getGComponent(x + 1, y) + 128; 
		        int bb = imageIn.getBComponent(x, y) - imageIn.getBComponent(x + 1, y) + 128;
		        //�������
		        if (rr > 255) rr = 255; 
		        if (rr < 0) rr = 0; 
		        if (gg > 255) gg = 255;
		        if (gg < 0) gg = 0;
		        if (bb > 255) bb = 255;
		        if (bb < 0) bb = 0;  
		        
		        imageIn.setPixelColor(x, y, rr, gg, bb);
	         }
        } 
        return imageIn;
    }
}
