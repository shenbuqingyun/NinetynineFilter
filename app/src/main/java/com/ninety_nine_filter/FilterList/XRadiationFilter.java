

package com.ninety_nine_filter.FilterList;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * X光效果
 */
public class XRadiationFilter extends FIlterBase implements IImageFilter{

	private GradientMapFilter gradientMapFx = new GradientMapFilter();
	private ImageBlender blender = new ImageBlender();

	public XRadiationFilter(){
	    List<Integer> colors = new ArrayList<Integer>();
	    colors.add(Gradient.TintColors.LightCyan());
	    colors.add(Color.BLACK);
	    gradientMapFx.Map = new Gradient(colors);
	    blender.Mode = ImageBlender.BlendMode.ColorBurn;
	    blender.Mixture = 0.8f;
	}

	 //@Override
    public Image process(Image imageIn) {
    	imageIn = this.gradientMapFx.process(imageIn);
    	imageIn = this.blender.Blend(imageIn, imageIn);
    	return imageIn;
    }
}
