package com.ninety_nine_filter.FilterList;

/**
 * 自动校正效果
 */
public class AutoAdjustFilter extends FIlterBase implements IImageFilter{

    public Image process(Image imageIn) {
     	HistogramEqualFilter hee = new HistogramEqualFilter();
    	hee.ContrastIntensity = 0.5f;
    	imageIn = hee.process(imageIn);
    	
    	AutoLevelFilter ale = new AutoLevelFilter();
    	ale.Intensity = 0.5f;
    	return ale.process(imageIn);	
    }
}
