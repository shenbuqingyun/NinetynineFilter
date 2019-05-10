package com.ninety_nine_filter.FilterList;

/**
 * 版画效果
 * 油画
 */
public class BlockPrintFilter extends FIlterBase  implements IImageFilter {
	//@Override
    public Image process(Image imageIn) {
    	ParamEdgeDetectFilter pde = new ParamEdgeDetectFilter();
    	pde.K00 = 1;
    	pde.K01 = 2;
    	pde.K02 = 1;
    	pde.Threshold = 0.25f;
        pde.DoGrayConversion = false;
        ImageBlender ib = new ImageBlender();
        ib.Mode = (int) ImageBlender.BlendMode.Multiply;
        return ib.Blend(imageIn.clone(), pde.process(imageIn));
    }
}
