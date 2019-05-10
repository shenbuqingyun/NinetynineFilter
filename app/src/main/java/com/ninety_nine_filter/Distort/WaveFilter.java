package com.ninety_nine_filter.Distort;

public class WaveFilter extends BilinearDistort{
	double _phase;
    double _amplitude ;
    double _waveLength ;
	 
    public WaveFilter(int wavelength, int amplitude){
    	this(wavelength, amplitude, 0);
    }
    public WaveFilter(int wavelength, int amplitude, double phase)
    {
        _waveLength = 2 * ((wavelength >= 1) ? wavelength : 1) ;
        _amplitude = ((amplitude >= 1) ? amplitude : 1) ;
		_phase = phase ;
    }
    
    public double[] calc_undistorted_coord (int x, int y, double un_x, double un_y)
    {
        double width = clone.getWidth();
        double height = clone.getHeight() ;
        double fScaleX = 1.0;
		double fScaleY = 1.0 ;
        if (width < height)
            fScaleX = height / width ;
        else if (width > height)
            fScaleY = width / height ;

        // distances to center, scaled
        double cen_x = width / 2.0;
        double cen_y = height / 2.0;
        double dx = (x - cen_x) * fScaleX;
        double dy = (y - cen_y) * fScaleY;
        double amnt = _amplitude * Math.sin (2 * LIB_PI * Math.sqrt (dx*dx + dy*dy) / _waveLength + _phase) ;
        un_x = (amnt + dx) / fScaleX + cen_x ;
        un_y = (amnt + dy) / fScaleY + cen_y ;
        un_x = Function.FClampDouble (un_x, 0.0, width-1.0) ;
        un_y = Function.FClampDouble (un_y, 0.0, height-1.0) ;
        return new double[]{un_x, un_y};
    }
}
