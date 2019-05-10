package com.ninety_nine_filter.FilterList;

public class GammaFilter extends LUTFilter{

	double   _fInvGamma ;
	public int InitLUTtable (int nLUTIndex)
    {
        double   fMax = Math.pow (255.0, _fInvGamma) / 255.0 ;
        double   d = Math.pow((double)nLUTIndex, _fInvGamma) ;
        return Function.FClamp0255(d / fMax) ;
    }
	 
	public GammaFilter (int gamma)
    {
        gamma = ((gamma >= 1) ? gamma : 1) ;
        _fInvGamma = 1.0 / (gamma / 100.0) ;
    }
}