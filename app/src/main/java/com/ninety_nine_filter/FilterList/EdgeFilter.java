package com.ninety_nine_filter.FilterList;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * 黑白边缘
 */
public class EdgeFilter extends FIlterBase implements IImageFilter {

    //@Override
    public Image process(Image imageIn) {
        // Image size
        int width = imageIn.getWidth();
        int height = imageIn.getHeight();
        boolean[][] mask = null;
        Paint grayMatrix[] = new Paint[256];

        // Init gray matrix
        for (int i = 0; i <= 255; i++) {
            Paint p = new Paint();
            p.setColor(Color.rgb(i, i, i));
            grayMatrix[i] = p;
        }

        int [][] luminance = new int[width][height];
        for (int y = 0; y < height ; y++) {
            for (int x = 0; x < width ; x++) {
                if(mask != null && !mask[x][y]){
                        continue;
                }
                luminance[x][y] = (int) luminance(imageIn.getRComponent(x, y), imageIn.getGComponent(x, y), imageIn.getBComponent(x, y));
            }
        }


        int grayX, grayY;
        int magnitude;
        for (int y = 1; y < height-1; y++) {
            for (int x = 1; x < width-1; x++) {

                if(mask != null && !mask[x][y]){
                    continue;
                }

                grayX = - luminance[x-1][y-1] + luminance[x-1][y-1+2] - 2* luminance[x-1+1][y-1] + 2* luminance[x-1+1][y-1+2] - luminance[x-1+2][y-1]+ luminance[x-1+2][y-1+2];
                grayY = luminance[x-1][y-1] + 2* luminance[x-1][y-1+1] + luminance[x-1][y-1+2] - luminance[x-1+2][y-1] - 2* luminance[x-1+2][y-1+1] - luminance[x-1+2][y-1+2];

                // Magnitudes sum
                magnitude = 255 - Image.SAFECOLOR(Math.abs(grayX) + Math.abs(grayY));
                Paint grayscaleColor = grayMatrix[magnitude];

                // Apply the color into a new image
                imageIn.setPixelColor(x, y, grayscaleColor.getColor());
            }
        }

        return imageIn;
    }


   

    /**
     * Apply the luminance
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    private int luminance(int r, int g, int b) {
        return (int) ((0.299 * r) + (0.58 * g) + (0.11 * b));
    }

}

