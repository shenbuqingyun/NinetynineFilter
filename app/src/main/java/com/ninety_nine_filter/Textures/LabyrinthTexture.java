package com.ninety_nine_filter.Textures;

import com.ninety_nine_filter.FilterList.*;

public class LabyrinthTexture implements ITextureGenerator
{
	// Perlin noise function used for texture generation
    private PerlinNoise noise = new PerlinNoise( 1.0 / 16, 1.0, 0.65, 1 );

    
    private int r;

    /// <summary>
    /// Initializes a new instance of the <see cref="LabyrinthTexture"/> class
    /// </summary>
    /// 
	public LabyrinthTexture( )
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
					Math.min( 1.0f,
						(float) Math.abs( noise.Function2D( x + r, y + r ) )
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
