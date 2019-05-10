package com.ninety_nine_filter.Distort;

public class BulgeFilter extends BilinearDistort{
	double   _amount ;
    double   _offsetX ;
    double   _offsetY ;
	 
    public BulgeFilter (int amount)
    {
        this(amount, 0, 0);
    }
    
    public BulgeFilter (int amount, double offsetX, double offsetY)
    {
        _amount = amount / 100.0 ;
        _offsetX = Function.FClampDouble(offsetX, -1.0, 1.0) ;
        _offsetY = Function.FClampDouble(offsetY, -1.0, 1.0) ;
    }
    
    public double[] calc_undistorted_coord (int x, int y, double un_x, double un_y)
    {
        double hw = clone.getWidth() / 2.0 ;
        double hh = clone.getHeight() / 2.0 ;
        double maxrad = (hw < hh ? hw : hh) ;
        hw += _offsetX * hw ;
        hh += _offsetY * hh ;

        double u = x - hw ;
        double v = y - hh ;
        double r = Math.sqrt(u*u + v*v) ;
        double rscale1 = 1.0 - (r / maxrad) ;
        if (rscale1 > 0)
        {
            double rscale2 = 1.0 - _amount * rscale1 * rscale1 ;
            un_x = Function.FClampDouble (u * rscale2 + hw, 0.0, clone.getWidth()-1.0) ;
            un_y = Function.FClampDouble (v * rscale2 + hh, 0.0, clone.getHeight()-1.0) ;
        }
        else
        {
            un_x = x ;
            un_y = y ;
        }
        return new double[]{un_x, un_y};
    }
    

}
