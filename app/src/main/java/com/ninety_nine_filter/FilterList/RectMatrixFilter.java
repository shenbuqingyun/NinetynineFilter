
package com.ninety_nine_filter.FilterList;

import android.graphics.Color;

/**
 * 方块矩阵效果
 */
public class RectMatrixFilter extends RadialDistortionFilter{
	
	/**
	 * 0垂直方向， 1水平方向
	 */
	public int Oriention = 0;
	
	public int BannerNum = 15;
  
    //@Override
    public Image process(Image imageIn) {
       int width = imageIn.getWidth();
       int height = imageIn.getHeight();
       int r = 0, g = 0, b = 0;
      
       Image clone = imageIn.clone();
       clone.clearImage(Color.LTGRAY);
       
       //ˮƽ����
	   Point[] point = new Point[BannerNum];
	   int dh = height / BannerNum; 
	   int dw = width;
	   for(int i = 0; i < BannerNum; i++){
    	   point[i] = new Point(0, i * dh);
       }	
	   for (int x = 0; x < dh; x++) {
            for (int y = 0; y < BannerNum; y++) {
            	for(int k = 0 ; k < dw; k++) { 		
            		int xx = (int)point[y].X + k;
            	    int yy = (int)point[y].Y + (int)(x/1.8);    
	            	r = imageIn.getRComponent(xx, yy);
	            	g = imageIn.getGComponent(xx, yy);
	            	b = imageIn.getBComponent(xx, yy);
	            	clone.setPixelColor(xx,  yy, r, g, b);
            	}
            }
	   }
	   //��ͼ�����ಿ�������
       for(int xx = 0; xx < width; xx++){
    	   for(int yy = (int)point[BannerNum-1].Y + dh; yy <height ; yy++){
            	r = imageIn.getRComponent(xx, yy);
            	g = imageIn.getGComponent(xx, yy);
            	b = imageIn.getBComponent(xx, yy);
            	clone.setPixelColor(xx, yy, r, g, b);
  	       }
       }	
       
       //��ֱ����
	   point = new Point[BannerNum];
	   dw = width / BannerNum;	
	   dh = height;
       for(int i = 0; i < BannerNum; i++){
    	   point[i] = new Point(i * dw, 0);
       }	       
       for (int x = 0; x < dw; x++) {
            for (int y = 0; y < BannerNum; y++) {
            	for(int k = 0 ; k < dh; k++) {
            		int xx = (int)point[y].X + (int)(x/1.8);
            	    int yy = (int)point[y].Y + k;
                	r = imageIn.getRComponent(xx, yy);
	            	g = imageIn.getGComponent(xx, yy);
	            	b = imageIn.getBComponent(xx, yy);
	            	clone.setPixelColor(xx,  yy, r, g, b);
            	}
            }
       }   
       //��ͼ�����ಿ�������
       for(int yy = 0; yy < height; yy++){
    	   for(int xx = (int)point[BannerNum-1].X + dw; xx < width ; xx++){
            	r = imageIn.getRComponent(xx, yy);
            	g = imageIn.getGComponent(xx, yy);
            	b = imageIn.getBComponent(xx, yy);
            	clone.setPixelColor(xx, yy, r, g, b);
  	       }
       }	

       return clone;  
    }
}
