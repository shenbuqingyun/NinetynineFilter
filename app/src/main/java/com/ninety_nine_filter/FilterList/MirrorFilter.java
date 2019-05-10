package com.ninety_nine_filter.FilterList;

/**
 * 镜子方向
 */
public class MirrorFilter  extends FIlterBase implements IImageFilter{

   private boolean IsHorizontal = true;

   public MirrorFilter(boolean IsHorizontal)
   {
       this.IsHorizontal = IsHorizontal;
   }

   public Image process(Image imageIn)
   {
       int height = imageIn.getHeight();
       int width = imageIn.getWidth();
       int color, color2;

       if (IsHorizontal)
       {
           for (int y = 0; y < height; y++)
           {
               for (int x = 0; x < (width / 2); x++)
               {
                   color = imageIn.getPixelColor(x, y);
                   color2 = imageIn.getPixelColor(width - 1 - x, y);
                   imageIn.setPixelColor(width - 1 - x, y, color);
                   imageIn.setPixelColor(x, y, color2);
               }
           }
       }
       else
       {
           for (int x = 0; x < width; x++)
           {
               for (int y = 0; y < (height / 2); y++)
               {
                   color = imageIn.getPixelColor(x, y);
                   color2 = imageIn.getPixelColor(x, height - 1 - y);
                   imageIn.setPixelColor(x, height - 1 - y, color);
                   imageIn.setPixelColor(x, y, color2);
               }
           }
       }
       return imageIn;
   }

}
