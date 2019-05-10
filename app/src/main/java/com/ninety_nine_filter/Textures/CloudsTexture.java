package com.ninety_nine_filter.Textures;

import com.ninety_nine_filter.FilterList.*;

public class CloudsTexture implements ITextureGenerator
{
    // Perlin noise function used for texture generation
    private PerlinNoise noise = new PerlinNoise( 1.0 / 32, 1.0, 0.5, 8 );

  
	private int	r;

    /// <summary>
    /// Initializes a new instance of the <see cref="CloudsTexture"/> class
    /// </summary>
    /// 
	public CloudsTexture( )
	{
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
		float[][] texture = new float[height][width];

		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				texture[y][x] = 
					Math.max( 0.0f, Math.min( 1.0f,
						(float) noise.Function2D( x + r, y + r ) * 0.5f + 0.5f
					) );

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
