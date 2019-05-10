package com.ninety_nine_filter.FilterList;

import android.app.Activity;

public class FillPatternFilter extends FIlterBase  implements IImageFilter{
	
	private Image pattern;
	
	private float Mixture = 0.2f;//0.5f
	
    public FillPatternFilter(Activity activity, int resourceId)
    {
        this.pattern = Image.LoadImage(activity, resourceId);
    }
    
    public FillPatternFilter(Activity activity, int resourceId, int Mixture)
    {
    	this.pattern = Image.LoadImage(activity, resourceId);
        this.Mixture = Mixture;
    }

    public Image process(Image imageIn)
    {
    	int mix1 = (int) (Mixture * 255f);
	    int mix2 = 255 - mix1;
        int r, g, b, r1, g1, b1;
        for (int x = 0; x < imageIn.getWidth(); x++)
        {
            for (int y = 0; y < imageIn.getHeight(); y++)
            {
                int xx = x % pattern.getWidth();
                int yy = y % pattern.getHeight();

                r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y); 
                r1 = Image.SAFECOLOR(r + pattern.getRComponent(xx, yy));
                g1 = Image.SAFECOLOR(g + pattern.getGComponent(xx, yy));
                b1 = Image.SAFECOLOR(b + pattern.getBComponent(xx, yy));
                r = (r * mix2) + (r1 * mix1);
		        g = (g * mix2) + (g1 * mix1);
		        b = (b * mix2) + (b1 * mix1); 
		        imageIn.setPixelColor(x, y, r >> 8, g >> 8, b >> 8);
           }
        }
        return imageIn;
    }
}
