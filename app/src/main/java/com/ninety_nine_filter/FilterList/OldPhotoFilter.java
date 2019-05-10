package com.ninety_nine_filter.FilterList;

public class OldPhotoFilter extends FIlterBase implements IImageFilter{

	private GaussianBlurFilter blurFx;
	private NoiseFilter noiseFx;
	private VignetteFilter vignetteFx;
	private GradientMapFilter gradientFx;
	public OldPhotoFilter(){
		blurFx = new GaussianBlurFilter();
		blurFx.Sigma = 0.3f;
		  
		noiseFx = new NoiseFilter();
		noiseFx.Intensity = 0.03f;
		
		vignetteFx = new VignetteFilter();
		vignetteFx.Size = 0.6f;
		
		gradientFx = new GradientMapFilter();
		gradientFx.ContrastFactor = 0.3f;
	}
	
    //@Override
    public Image process(Image imageIn) {
    	imageIn = this.noiseFx.process(this.blurFx.process(imageIn));
    	imageIn = this.gradientFx.process(imageIn);
    	return this.vignetteFx.process(imageIn);
    }
}
