package com.ninety_nine_filter.FilterList;

import java.util.List;

public class RainBowFilter extends FIlterBase implements IImageFilter{

	public ImageBlender blender = new ImageBlender();
	public boolean IsDoubleRainbow = false;
	private GradientFilter gradientFx;
	public float gradAngleDegree = 40f; 
	
	public RainBowFilter(){
		    blender.Mixture = 0.25f;
		    blender.Mode = ImageBlender.BlendMode.Additive;
	
		    IsDoubleRainbow = true;
		   
            List<Integer> rainbowColors = Gradient.RainBow().MapColors;
            if (this.IsDoubleRainbow)
            {
                rainbowColors.remove(rainbowColors.size() - 1);//remove red
                rainbowColors.addAll(Gradient.RainBow().MapColors);
            }  
		    gradientFx = new GradientFilter();
		    gradientFx.OriginAngleDegree = gradAngleDegree;
		    gradientFx.Gradient = new Gradient(rainbowColors);
    }
	
	 //@Override
    public Image process(Image imageIn) {
    	Image clone =  gradientFx.process(imageIn.clone());
        return blender.Blend(imageIn, clone);
    }
}
