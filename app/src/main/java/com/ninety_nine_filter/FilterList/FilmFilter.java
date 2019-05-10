package com.ninety_nine_filter.FilterList;

/**
 * 电影院效果
 */
public class FilmFilter extends FIlterBase implements IImageFilter{
	private GradientFilter gradient;
    private SaturationModifyFilter saturationFx;

    public FilmFilter(float angle)
    {
        gradient = new GradientFilter();
        gradient.Gradient = Gradient.Fade();
        gradient.OriginAngleDegree = angle;

        saturationFx = new SaturationModifyFilter();
        saturationFx.SaturationFactor = -0.6f;
    }

    //@Override
    public Image process(Image imageIn)
    {
        Image clone = imageIn.clone();
        imageIn = gradient.process(imageIn);
        ImageBlender blender = new ImageBlender();
        blender.Mode = ImageBlender.BlendMode.Multiply;
        return saturationFx.process(blender.Blend(clone, imageIn));
        //return imageIn;// saturationFx.process(imageIn);
    }

}
