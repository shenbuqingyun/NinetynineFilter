package com.ninety_nine_filter.FilterList;

public class LUTFilter extends FIlterBase implements IImageFilter{

	protected int[]  m_LUT = new int[256] ;
	
	protected int InitLUTtable (int nLUTIndex){ 
		return nLUTIndex;
	}
	 
	public Image process(Image imageIn)
	{
		for (int i=0 ; i <= 0xFF ; i++)
			m_LUT[i] = InitLUTtable (i) ;
		int r, g, b;
		for(int x = 0 ; x < (imageIn.getWidth() - 1) ; x++){
			for(int y = 0 ; y < (imageIn.getHeight() - 1) ; y++){
				r = imageIn.getRComponent(x, y);
				g = imageIn.getGComponent(x, y);
				b = imageIn.getBComponent(x, y);
				
				imageIn.setPixelColor(x, y, Image.SAFECOLOR(m_LUT[r]), Image.SAFECOLOR(m_LUT[g]), Image.SAFECOLOR(m_LUT[b]));
			}
		}
		return imageIn;
	}
}