package com.ninety_nine_filter.FilterList;

public class GradientFilter extends FIlterBase  implements IImageFilter{
	
    private Palette palette = null;
    public Gradient Gradient;
    public float OriginAngleDegree;
  
    // Methods
    public GradientFilter()
    {
        this.OriginAngleDegree = 0f;
        this.Gradient = new Gradient();
    }

    public void ClearCache()
    {
        this.palette = null;
    }

    //@Override
    public Image process(Image imageIn) {
    	int width = imageIn.getWidth();
    	int height = imageIn.getHeight();
        double d = this.OriginAngleDegree * 0.0174532925;
        float cos = (float)Math.cos(d);
        float sin = (float)Math.sin(d);
        float radio = (cos * width) + (sin * height);
        float dcos = cos * radio;
        float dsin = sin * radio;
        int dist = (int)Math.sqrt((double)((dcos * dcos) + (dsin * dsin)));
        dist = Math.max(Math.max(dist, width), height);
   
        if ((this.palette == null) || (dist != this.palette.Length)) {
            this.palette = this.Gradient.CreatePalette(dist);
        }
        int[] red = this.palette.Red;
        int[] green = this.palette.Green;
        int[] blue = this.palette.Blue;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
            	radio = (cos * j) + (sin * i);
                dcos = cos * radio;
                dsin = sin * radio;
                dist = (int)Math.sqrt((double)((dcos * dcos) + (dsin * dsin)));
                imageIn.setPixelColor(j, i, red[dist], green[dist], blue[dist]);
            }
        }
        return imageIn;
    } 
}

