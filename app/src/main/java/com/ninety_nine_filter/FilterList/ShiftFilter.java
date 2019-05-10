package com.ninety_nine_filter.FilterList;


public class ShiftFilter extends FIlterBase implements IImageFilter{

	int   _amount ; // max shift pixel
	
	/**
    Constructor 
    amount >= 2.
	*/
	public ShiftFilter(int amount)
	{
	    _amount = ((amount >= 2) ? amount : 2) ;
	}
  
    //@Override
    public Image process(Image imageIn) {
    	int r, g, b, m_current = 0;
		  int width = imageIn.getWidth();
		  int height = imageIn.getHeight();
		  Image clone = imageIn.clone();
		  for(int y = 0 ; y < height ; y++){
			  for(int x = 0 ; x < width ; x++){
				   if (x == 0) {
					   m_current = (NoiseFilter.getRandomInt(-255, 0xff) % _amount) * ((NoiseFilter.getRandomInt(-255, 0xff) % 2 > 0) ? 1 : -1) ;
				   }
				   int sx = (int)Function.FClamp(x+m_current, 0, width-1);
			       r = clone.getRComponent(sx, y);
				   g = clone.getGComponent(sx, y);
				   b = clone.getBComponent(sx, y);
				   imageIn.setPixelColor(x, y, r, g, b);
			  }
		  }
        return imageIn;     
    }
}