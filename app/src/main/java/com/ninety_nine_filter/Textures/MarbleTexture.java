package com.ninety_nine_filter.Textures;

import com.ninety_nine_filter.FilterList.*;

public class MarbleTexture implements ITextureGenerator
{
	// Perlin noise function used for texture generation
    private PerlinNoise noise = new PerlinNoise( 1.0 / 32, 1.0, 0.65, 2 );

 	private int	r;

	private double	xPeriod = 5.0;
	private double	yPeriod = 10.0;



    /// <summary>
    /// Initializes a new instance of the <see cref="MarbleTexture"/> class
    /// </summary>
    /// 
	public MarbleTexture( )
    {
        Reset( );
    }

    /// <summary>
    /// Initializes a new instance of the <see cref="MarbleTexture"/> class
    /// </summary>
    /// 
    /// <param name="xPeriod">XPeriod value</param>
    /// <param name="yPeriod">YPeriod value</param>
    /// 
	public MarbleTexture( double xPeriod, double yPeriod )
	{
		this.xPeriod =  Math.max( 2.0, xPeriod);
		this.yPeriod =  Math.max( 2.0, yPeriod);
		Reset( );
	}

    /// <summary>
    /// Generate texture
    /// </summary>
    /// 
    /// <param name="width">Texture's width</param>
    /// <param name="height">Texture's height</param>
    /// 
    /// <returns>Two dimensional array of intensities</returns>
    /// 
    /// <remarks>Generates new texture with specified dimension.</remarks>
    /// 
    public float[][] Generate( int width, int height )
	{
		float[][]	texture = new float[height][width];
		double		xFact = xPeriod / width;
		double		yFact = yPeriod / height;

		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				texture[y][x] = 
					Math.min( 1.0f, (float)
						Math.abs( Math.sin( 
							( x * xFact + y * yFact + noise.Function2D( x + r, y + r ) ) * Math.PI
						) )
					);

			}
		}
		return texture;
	}
    
  

    /// <summary>
    /// Reset generator
    /// </summary>
    /// 
    /// <remarks>Regenerates internal random numbers.</remarks>
    /// 
    public void Reset( )
	{
		r = NoiseFilter.getRandomInt(1, 5000 );
	}
}
