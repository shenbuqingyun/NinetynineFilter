package com.ninety_nine_filter.FilterList;

/**
 * 投影效果
 */
public class ReflectionFilter  extends FIlterBase implements IImageFilter{

	/**
	 * ˮƽͶӰ?
	 */
	public boolean IsHorizontal = true;
	public float Offset = 0.5f;//0.8
	
	public ReflectionFilter(boolean IsHorizontal){
		this.IsHorizontal = IsHorizontal;
	}

    //@Override
    public Image process(Image imageIn) {
        int r, g, b;
        
        int height = imageIn.getHeight();
        int width = imageIn.getWidth();
        int start;
        int limit;
        Image clone = imageIn.clone();
        
        if (this.IsHorizontal)
        {
            int y_offset = (int) (this.Offset * height);
            if (this.Offset > 0.5f) {
                start = y_offset - (height - y_offset);
                limit = y_offset;
            }
            else{
                start = y_offset;
                limit = y_offset + y_offset;
            }
            if (start < 0) {
                start = 0;
            }
            for (int y = start; (y < limit) && (y < height); y++)
            {
                int y_pos = (-y + (2 * y_offset)) - 1;
                y_pos = (y_pos < 0) ? 0 : (y_pos >= height ? height - 1 : y_pos) ;    
                for (int x = 0; x < width; x++)
                {
                    r = clone.getRComponent(x, y);
                    g = clone.getGComponent(x, y);
                    b = clone.getBComponent(x, y);
                    imageIn.setPixelColor(x, y_pos, r, g, b);
                }
            }
        }
        else {
	        int x_offset = (int) (this.Offset * width);
	        if (this.Offset > 0.5f){
	            start = x_offset - (width - x_offset);
	            limit = x_offset;
	        }
	        else {
	        	start = x_offset;
	            limit = x_offset + x_offset;
	        }
	        if (start < 0){
	        	start = 0;
	        }
	        for (int x = start; (x < limit) && (x < width); x++)
	        {
	            int x_pos = (-x + (2 * x_offset)) - 1;
	            x_pos = x_pos < 0 ? 0 : (x_pos >= width ? width - 1:  x_pos);
	            for (int y = 0; y < height; y++)
	            {
	                r = clone.getRComponent(x, y);
	                g = clone.getGComponent(x, y);
	                b = clone.getBComponent(x, y);
	                imageIn.setPixelColor(x_pos, y, r, g, b);
	            }
	        }
        }
        return imageIn;
    }
}
