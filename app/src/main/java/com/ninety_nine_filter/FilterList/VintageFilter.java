

package com.ninety_nine_filter.FilterList;


public class VintageFilter extends FIlterBase  implements IImageFilter{

    //@Override
    public Image process(Image imageIn) {
    	GradientMapFilter gmf = new GradientMapFilter(Gradient.BlackSepia());
    	gmf.ContrastFactor = 0.15f;

    	ImageBlender ib = new ImageBlender();
    	ib.Mixture = 0.7f;
    	ib.Mode = ImageBlender.BlendMode.Overlay;
    	imageIn = ib.Blend(imageIn.clone(), gmf.process(imageIn));
    	
    	VignetteFilter vigette = new VignetteFilter();
    	vigette.Size = 0.7f;
    	return vigette.process(imageIn);	
    }
}
