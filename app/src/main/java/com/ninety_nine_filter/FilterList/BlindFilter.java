package com.ninety_nine_filter.FilterList;

public class BlindFilter  extends FIlterBase implements IImageFilter{

	boolean _direct;//horizontal: true,  vertical: false
	int _color;
	int _opacity;
    int _width;
    

    public BlindFilter(boolean direct, int width, int opacity, int blindColor)
    {
        _direct = direct ;
        _width = (width >= 2) ? width : 2;
        _opacity = Function.FClamp (opacity, 1, 100);
		_color = blindColor;
    };
  
    //@Override
    public Image process(Image imageIn) {
    	 int r, g, b, a;
		  for(int x = 0 ; x < (imageIn.getWidth() - 1) ; x++){
			  for(int y = 0 ; y < (imageIn.getHeight() - 1) ; y++){
				r = imageIn.getRComponent(x, y);
				g = imageIn.getGComponent(x, y);
				b = imageIn.getBComponent(x, y);
				int  nMod = 0 ;
				if (_direct) // horizontal direction
					nMod = y % _width ;
				else if (_direct == false) // vertical direction
					nMod = x % _width ;

				double fDelta = 255.0 * (_opacity/100.0) / (_width-1.0);
				a = Function.FClamp0255(nMod * fDelta) ;
				int colorR = _color & 0xFF0000 >> 16;
			    int colorG = _color & 0x00FF00 >> 8;
			    int colorB = _color & 0x0000FF;
				if (_color == 0xFF)
				{
					imageIn.setPixelColor(x, y, colorR, colorG, colorB);
					continue ;
				}
				if (a == 0)
					continue ;

				int t = 0xFF - a ;
				imageIn.setPixelColor(x, y, (colorR * a + r * t) / 0xFF, (colorG * a + g * t) / 0xFF, (colorB * a + b * t) / 0xFF);
			  }
		  }
        return imageIn;     
    }
}