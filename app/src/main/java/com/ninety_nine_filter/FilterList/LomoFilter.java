package com.ninety_nine_filter.FilterList;


/**
 * lomo效果
 */
public class LomoFilter extends FIlterBase implements IImageFilter{
	 private BrightContrastFilter contrastFx = new BrightContrastFilter();
     private GradientMapFilter gradientMapFx  = new GradientMapFilter();
     private ImageBlender blender = new ImageBlender();
     private VignetteFilter vignetteFx = new VignetteFilter();
     private NoiseFilter noiseFx = new NoiseFilter();

     public LomoFilter()
     {
         contrastFx.BrightnessFactor = 0.05f;
         contrastFx.ContrastFactor = 0.5f;
      
         blender.Mixture = 0.5f;
         blender.Mode = ImageBlender.BlendMode.Multiply;
     
         vignetteFx.Size = 0.6f;

         noiseFx.Intensity = 0.02f;
     }

     public Image process(Image imageIn)
     {
         Image tempImg = contrastFx.process(imageIn);
         tempImg = noiseFx.process(tempImg);
         imageIn = gradientMapFx.process(tempImg);
         imageIn = blender.Blend(imageIn, tempImg);
         imageIn = vignetteFx.process(imageIn);
         return imageIn;
     }
}

