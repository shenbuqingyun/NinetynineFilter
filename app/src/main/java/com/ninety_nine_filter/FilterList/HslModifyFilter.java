package com.ninety_nine_filter.FilterList;

public class HslModifyFilter extends FIlterBase implements IImageFilter{
	private float HueFactor; // 色调
    
    /// <summary>
    /// initial value setting reference to http://blog.csdn.net/yacper/article/details/4743014
    /// </summary>
    /// <param name="HueFactor"></param>
    public HslModifyFilter(float HueFactor)
    {
        this.HueFactor = Math.max(0, Math.min(359, HueFactor));
    }

  

    public Image process(Image imageIn)
    {
        int r, g, b;
        HslColor hsl = new HslColor(HueFactor, 0, 0);
      
        for (int x = 0; x < imageIn.getWidth(); x++) {
            for (int y = 0; y < imageIn.getHeight(); y++) {
         	    r = imageIn.getRComponent(x, y);
                g = imageIn.getGComponent(x, y);
                b = imageIn.getBComponent(x, y);

                HslColor.RgbToHsl(r, g, b, hsl);
                hsl.h = this.HueFactor;
                int color = HslColor.HslToRgb(hsl);
                imageIn.setPixelColor(x, y, color);
            }
        }
        return imageIn;
    }
}
