package com.ninety_nine_filter.FilterList;


/**
 * 情景效果（用于自然景色渲染）
 */
public class SceneFilter extends FIlterBase implements IImageFilter{
	private GradientFilter gradientFx;
    private SaturationModifyFilter saturationFx;

    public SceneFilter(float angle, Gradient gradient)
    {
        gradientFx = new GradientFilter();
        gradientFx.Gradient = gradient;
        gradientFx.OriginAngleDegree = angle;

        saturationFx = new SaturationModifyFilter();
        saturationFx.SaturationFactor = -0.6f;
    }

    //@Override
    public Image process(Image imageIn)
    {
        Image clone = imageIn.clone();
        imageIn = gradientFx.process(imageIn);
        ImageBlender blender = new ImageBlender();
        blender.Mode = ImageBlender.BlendMode.Subractive;
        return saturationFx.process(blender.Blend(clone, imageIn));
        //return imageIn;// saturationFx.process(imageIn);
    }
}
