package com.ninety_nine_filter.Textures;

public interface ITextureGenerator {
	
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
	float[][] Generate(int width, int height);

	/// <summary>
	/// Reset generator
	/// </summary>
    /// 
    /// <remarks>Resets the generator - resets interl variables, regenerates
    /// internal random numbers, etc.</remarks>
    /// 
	void Reset();

}
