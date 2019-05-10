package com.ninety_nine_filter.Distort;

public class RippleFilter extends BilinearDistort{
	int _waveLength ;
    int _amplitude ;
    int _sinType ;
	 
    public RippleFilter(int waveLength, int amplitude){
    	this(waveLength, amplitude, true);
    }
    
    public RippleFilter (int waveLength, int amplitude, boolean sinType)
    {
        _waveLength = ((waveLength >= 1) ? waveLength : 1) ;
        _amplitude = ((amplitude >= 1) ? amplitude : 1) ;
        _sinType = (sinType ? 1 : 0) ;
    }
    
    public double[] calc_undistorted_coord (int x, int y, double un_x, double un_y)
    {
    	double   w = clone.getWidth() ;
        un_x = (x + w + shift_amount(y)) % w;//fmod (x + w + shift_amount(y), w) ;
        un_x = Function.FClampDouble(un_x, 0.0, w-1) ;
        un_y = y ;
        return new double[]{un_x, un_y};
    }
    
    double shift_amount (int nPos)
    {
        if (_sinType > 0)
            return _amplitude * Math.sin(nPos*2*LIB_PI/_waveLength) ;
        else
            return Math.floor (_amplitude * (Math.abs((((nPos % _waveLength) / (double)_waveLength) * 4) - 2) - 1)) ;
    }
}
