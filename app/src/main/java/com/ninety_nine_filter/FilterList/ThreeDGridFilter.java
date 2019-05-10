package com.ninety_nine_filter.FilterList;


public class ThreeDGridFilter extends FIlterBase implements IImageFilter{
	int   _size ;
    int   _depth ;
    
    public ThreeDGridFilter(int size, int depth)
    {
        _size = ((size >= 1) ? size : 1) ;
        _depth = depth ;
    }

    //@Override
    public Image process(Image imageIn) {
    	 int r, g, b;
		  for(int x = 0 ; x < imageIn.getWidth() ; x++){
			  for(int y = 0 ; y < imageIn.getHeight() ; y++){
					r = imageIn.getRComponent(x, y);
					g = imageIn.getGComponent(x, y);
					b = imageIn.getBComponent(x, y);

				    int  d = 0 ;
					if (((y-1) % _size == 0) && (x % _size>0) && ((x+1) % _size>0))
						d = -_depth ; // top
					else if (((y+2) % _size == 0) && (x % _size > 0) && ((x+1) % _size > 0))
						d = _depth ; // bottom
					else if (((x-1) % _size == 0) && (y % _size > 0) && ((y+1) % _size) > 0)
						d = _depth ; // left
					else if (((x+2) % _size == 0) && (y % _size > 0) && ((y+1) % _size) > 0)
						d = -_depth ; // right

				   imageIn.setPixelColor(x, y, Image.SAFECOLOR(r+d),  Image.SAFECOLOR(g+d),  Image.SAFECOLOR(b+d));
			  }
		  }
        return imageIn;
    }

}
