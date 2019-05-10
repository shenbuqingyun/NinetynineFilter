package com.ninety_nine_filter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ninety_nine_filter.Distort.BulgeFilter;
import com.ninety_nine_filter.Distort.RippleFilter;
import com.ninety_nine_filter.Distort.TwistFilter;
import com.ninety_nine_filter.Distort.WaveFilter;
import com.ninety_nine_filter.FilterList.AutoAdjustFilter;
import com.ninety_nine_filter.FilterList.BannerFilter;
import com.ninety_nine_filter.FilterList.BigBrotherFilter;
import com.ninety_nine_filter.FilterList.BlackWhiteFilter;
import com.ninety_nine_filter.FilterList.BlindFilter;
import com.ninety_nine_filter.FilterList.BlockPrintFilter;
import com.ninety_nine_filter.FilterList.BrickFilter;
import com.ninety_nine_filter.FilterList.BrightContrastFilter;
import com.ninety_nine_filter.FilterList.CleanGlassFilter;
import com.ninety_nine_filter.FilterList.ColorQuantizeFilter;
import com.ninety_nine_filter.FilterList.ColorToneFilter;
import com.ninety_nine_filter.FilterList.ComicFilter;
import com.ninety_nine_filter.FilterList.EdgeFilter;
import com.ninety_nine_filter.FilterList.FeatherFilter;
import com.ninety_nine_filter.FilterList.FillPatternFilter;
import com.ninety_nine_filter.FilterList.FilmFilter;
import com.ninety_nine_filter.FilterList.FocusFilter;
import com.ninety_nine_filter.FilterList.GammaFilter;
import com.ninety_nine_filter.FilterList.GaussianBlurFilter;
import com.ninety_nine_filter.FilterList.Gradient;
import com.ninety_nine_filter.FilterList.HslModifyFilter;
import com.ninety_nine_filter.FilterList.IImageFilter;
import com.ninety_nine_filter.FilterList.IllusionFilter;
import com.ninety_nine_filter.FilterList.Image;
import com.ninety_nine_filter.FilterList.InvertFilter;
import com.ninety_nine_filter.FilterList.LensFlareFilter;
import com.ninety_nine_filter.FilterList.LightFilter;
import com.ninety_nine_filter.FilterList.LomoFilter;
import com.ninety_nine_filter.FilterList.MirrorFilter;
import com.ninety_nine_filter.FilterList.MistFilter;
import com.ninety_nine_filter.FilterList.MonitorFilter;
import com.ninety_nine_filter.FilterList.MosaicFilter;
import com.ninety_nine_filter.FilterList.NeonFilter;
import com.ninety_nine_filter.FilterList.NightVisionFilter;
import com.ninety_nine_filter.FilterList.NoiseFilter;
import com.ninety_nine_filter.FilterList.OilPaintFilter;
import com.ninety_nine_filter.FilterList.OldPhotoFilter;
import com.ninety_nine_filter.FilterList.PaintBorderFilter;
import com.ninety_nine_filter.FilterList.PixelateFilter;
import com.ninety_nine_filter.FilterList.PosterizeFilter;
import com.ninety_nine_filter.FilterList.RadialDistortionFilter;
import com.ninety_nine_filter.FilterList.RainBowFilter;
import com.ninety_nine_filter.FilterList.RaiseFrameFilter;
import com.ninety_nine_filter.FilterList.RectMatrixFilter;
import com.ninety_nine_filter.FilterList.ReflectionFilter;
import com.ninety_nine_filter.FilterList.ReliefFilter;
import com.ninety_nine_filter.FilterList.SaturationModifyFilter;
import com.ninety_nine_filter.FilterList.SceneFilter;
import com.ninety_nine_filter.FilterList.SepiaFilter;
import com.ninety_nine_filter.FilterList.SharpFilter;
import com.ninety_nine_filter.FilterList.ShiftFilter;
import com.ninety_nine_filter.FilterList.SmashColorFilter;
import com.ninety_nine_filter.FilterList.SoftGlowFilter;
import com.ninety_nine_filter.FilterList.SupernovaFilter;
import com.ninety_nine_filter.FilterList.ThreeDGridFilter;
import com.ninety_nine_filter.FilterList.ThresholdFilter;
import com.ninety_nine_filter.FilterList.TileReflectionFilter;
import com.ninety_nine_filter.FilterList.TintFilter;
import com.ninety_nine_filter.FilterList.VideoFilter;
import com.ninety_nine_filter.FilterList.VignetteFilter;
import com.ninety_nine_filter.FilterList.VintageFilter;
import com.ninety_nine_filter.FilterList.WaterWaveFilter;
import com.ninety_nine_filter.FilterList.XRadiationFilter;
import com.ninety_nine_filter.FilterList.YCBCrLinearFilter;
import com.ninety_nine_filter.FilterList.ZoomBlurFilter;
import com.ninety_nine_filter.Textures.CloudsTexture;
import com.ninety_nine_filter.Textures.LabyrinthTexture;
import com.ninety_nine_filter.Textures.MarbleTexture;
import com.ninety_nine_filter.Textures.TextileTexture;
import com.ninety_nine_filter.Textures.TexturerFilter;
import com.ninety_nine_filter.Textures.WoodTexture;
import com.ninety_nine_filter.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FilterBean> filterArray = new ArrayList<>();
    private ImageView imageView;
    private RelativeLayout relativeLayout;
    private Bitmap mBitmap;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        initFilterLists();

        imageView = findViewById(R.id.filter_img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        mBitmap = Utils.resizeBitmap(bitmap, width, height);
        imageView.setImageBitmap(mBitmap);
        relativeLayout = findViewById(R.id.animation_shadow_rl);

        RecyclerView recyclerView = findViewById(R.id.recycley_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        FilterAdapter filterAdapter = new FilterAdapter(filterArray);
        filterAdapter.setOnFilterClickListener(new FilterAdapter.OnFilterClickListener() {
            @Override
            public void itemClick(int id) {
                IImageFilter iImageFilter = filterArray.get(id).getFilter();
                new processImageTask(MainActivity.this, iImageFilter).execute();
            }
        });
        recyclerView.setAdapter(filterAdapter);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawable(null);
    }

    private void initFilterLists() {
        //99种效果
        filterArray.add(new FilterBean(R.drawable.edge_filter, new EdgeFilter(),1));
        filterArray.add(new FilterBean(R.drawable.neon_filter, new NeonFilter(),2));
        filterArray.add(new FilterBean(R.drawable.threshold_filter, new ThresholdFilter(),3));
        filterArray.add(new FilterBean(R.drawable.bigbrother_filter, new BigBrotherFilter(),4));
        filterArray.add(new FilterBean(R.drawable.brick_filter, new BrickFilter(),5));
        filterArray.add(new FilterBean(R.drawable.blockprint_filter, new BlockPrintFilter(),6));
        filterArray.add(new FilterBean(R.drawable.noisefilter, new HslModifyFilter(250f),7)); /**/
        filterArray.add(new FilterBean(R.drawable.oilpaint_filter, new LomoFilter(),8));
        filterArray.add(new FilterBean(R.drawable.smashcolor_filter, new SmashColorFilter(),9));
        filterArray.add(new FilterBean(R.drawable.autoadjust_filter, new MistFilter(),10));
        filterArray.add(new FilterBean(R.drawable.colorquantize_filter, new ColorQuantizeFilter(),11));
        filterArray.add(new FilterBean(R.drawable.relief_filter, new ReliefFilter(),12));
        filterArray.add(new FilterBean(R.drawable.posterize_filter, new PosterizeFilter(2),13));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new ComicFilter(),14));
        filterArray.add(new FilterBean(R.drawable.tint_filter, new TintFilter(),15));

        filterArray.add(new FilterBean(R.drawable.video_filter1, new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_STAGGERED),16));
        filterArray.add(new FilterBean(R.drawable.video_filter2, new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_TRIPED),17));
        filterArray.add(new FilterBean(R.drawable.video_filter3, new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_3X3),18));
        filterArray.add(new FilterBean(R.drawable.video_filter4, new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_DOTS),19));
        filterArray.add(new FilterBean(R.drawable.tilereflection_filter1, new TileReflectionFilter(20, 8, 45, (byte) 1),20));
        filterArray.add(new FilterBean(R.drawable.tilereflection_filter2, new TileReflectionFilter(20, 8, 45, (byte) 2),21));
        filterArray.add(new FilterBean(R.drawable.fillpattern_filter, new FillPatternFilter(MainActivity.this, R.drawable.texture1),22));
        filterArray.add(new FilterBean(R.drawable.fillpattern_filter1, new FillPatternFilter(MainActivity.this, R.drawable.texture2),23));
        filterArray.add(new FilterBean(R.drawable.mirror_filter1, new MirrorFilter(true),24));
        filterArray.add(new FilterBean(R.drawable.mirror_filter2, new MirrorFilter(false),25));
        filterArray.add(new FilterBean(R.drawable.ycb_crlinear_filter, new YCBCrLinearFilter(new YCBCrLinearFilter.Range(-0.3f, 0.3f)),26));
        filterArray.add(new FilterBean(R.drawable.texturer_filter, new TexturerFilter(new CloudsTexture(), 0.8f, 0.8f),27));
        filterArray.add(new FilterBean(R.drawable.texturer_filter1, new TexturerFilter(new LabyrinthTexture(), 0.8f, 0.8f),28));
        filterArray.add(new FilterBean(R.drawable.texturer_filter2, new TexturerFilter(new MarbleTexture(), 1.8f, 0.8f),29));
        filterArray.add(new FilterBean(R.drawable.texturer_filter3, new TexturerFilter(new WoodTexture(), 0.8f, 0.8f),30));
        filterArray.add(new FilterBean(R.drawable.texturer_filter4, new TexturerFilter(new TextileTexture(), 0.8f, 0.8f),31));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter, new NoiseFilter(),32));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter0, new HslModifyFilter(40f),33));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter1, new HslModifyFilter(60f),34));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter2, new HslModifyFilter(80f),35));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter3, new HslModifyFilter(100f),36));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter4, new HslModifyFilter(150f),37));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter5, new HslModifyFilter(200f),38));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter6, new HslModifyFilter(250f),39));
        filterArray.add(new FilterBean(R.drawable.hslmodify_filter7, new HslModifyFilter(300f),40));

        filterArray.add(new FilterBean(R.drawable.zoomblur_filter, new ZoomBlurFilter(30),41));
        filterArray.add(new FilterBean(R.drawable.threedgrid_filter, new ThreeDGridFilter(16, 100),42));
        filterArray.add(new FilterBean(R.drawable.colortone_filter, new ColorToneFilter(Color.rgb(33, 168, 254), 192),43));
        filterArray.add(new FilterBean(R.drawable.colortone_filter2, new ColorToneFilter(0x00FF00, 192),44));//green
        filterArray.add(new FilterBean(R.drawable.colortone_filter3, new ColorToneFilter(0xFF0000, 192),45));//blue
        filterArray.add(new FilterBean(R.drawable.colortone_filter4, new ColorToneFilter(0x00FFFF, 192),46));//yellow
        filterArray.add(new FilterBean(R.drawable.softglow_filter, new SoftGlowFilter(10, 0.1f, 0.1f),47));
        filterArray.add(new FilterBean(R.drawable.tilereflection_filter, new TileReflectionFilter(20, 8),48));
        filterArray.add(new FilterBean(R.drawable.blind_filter1, new BlindFilter(true, 96, 100, 0xffffff),49));
        filterArray.add(new FilterBean(R.drawable.blind_filter2, new BlindFilter(false, 96, 100, 0x000000),50));
        filterArray.add(new FilterBean(R.drawable.raiseframe_filter, new RaiseFrameFilter(20),51));
        filterArray.add(new FilterBean(R.drawable.shift_filter, new ShiftFilter(10),52));
        filterArray.add(new FilterBean(R.drawable.wave_filter, new WaveFilter(25, 10),53));
        filterArray.add(new FilterBean(R.drawable.bulge_filter, new BulgeFilter(-97),54));
        filterArray.add(new FilterBean(R.drawable.twist_filter, new TwistFilter(27, 106),55));
        filterArray.add(new FilterBean(R.drawable.ripple_filter, new RippleFilter(38, 15, true),56));
        filterArray.add(new FilterBean(R.drawable.illusion_filter, new IllusionFilter(3),57));
        filterArray.add(new FilterBean(R.drawable.supernova_filter, new SupernovaFilter(0x00FFFF, 20, 100),58));
        filterArray.add(new FilterBean(R.drawable.lensflare_filter, new LensFlareFilter(),59));
        filterArray.add(new FilterBean(R.drawable.gamma_filter, new GammaFilter(50),60));
        filterArray.add(new FilterBean(R.drawable.sharp_filter, new SharpFilter(),61));

        filterArray.add(new FilterBean(R.drawable.invert_filter, new SceneFilter(5f, Gradient.Scene()),62));//green
        filterArray.add(new FilterBean(R.drawable.invert_filter, new SceneFilter(5f, Gradient.Scene1()),63));//purple
        filterArray.add(new FilterBean(R.drawable.invert_filter, new SceneFilter(5f, Gradient.Scene2()),64));//blue
        filterArray.add(new FilterBean(R.drawable.invert_filter, new SceneFilter(5f, Gradient.Scene3()),65));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new FilmFilter(80f),66));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new FocusFilter(),67));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new CleanGlassFilter(),68));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new PaintBorderFilter(0x00FF00),69));//green
        filterArray.add(new FilterBean(R.drawable.invert_filter, new PaintBorderFilter(0x00FFFF),70));//yellow
        filterArray.add(new FilterBean(R.drawable.invert_filter, new PaintBorderFilter(0xFF0000),71));//blue
        filterArray.add(new FilterBean(R.drawable.invert_filter, new OilPaintFilter(),72));
        filterArray.add(new FilterBean(R.drawable.invert_filter, new InvertFilter(),73));

        filterArray.add(new FilterBean(R.drawable.blackwhite_filter, new BlackWhiteFilter(),74));
        filterArray.add(new FilterBean(R.drawable.pixelate_filter, new PixelateFilter(),75));
        filterArray.add(new FilterBean(R.drawable.monitor_filter, new MonitorFilter(),76));
        filterArray.add(new FilterBean(R.drawable.ycb_crlinear_filter2, new YCBCrLinearFilter(new YCBCrLinearFilter.Range(-0.276f, 0.163f),
                new YCBCrLinearFilter.Range(-0.202f, 0.5f)),77)); //
        filterArray.add(new FilterBean(R.drawable.brightcontrast_filter, new BrightContrastFilter(),78));
        filterArray.add(new FilterBean(R.drawable.saturationmodity_filter, new SaturationModifyFilter(),79));
        filterArray.add(new FilterBean(R.drawable.banner_filter1, new BannerFilter(10, true),80));
        filterArray.add(new FilterBean(R.drawable.banner_filter2, new BannerFilter(10, false),81));
        filterArray.add(new FilterBean(R.drawable.rectmatrix_filter, new RectMatrixFilter(),82));
        filterArray.add(new FilterBean(R.drawable.gaussianblur_filter, new GaussianBlurFilter(),83));
        filterArray.add(new FilterBean(R.drawable.light_filter, new LightFilter(),84));
        filterArray.add(new FilterBean(R.drawable.mosaic_filter, new AutoAdjustFilter(),85));
        filterArray.add(new FilterBean(R.drawable.mosaic_filter, new MosaicFilter(),86));
        filterArray.add(new FilterBean(R.drawable.radialdistortion_filter, new RadialDistortionFilter(),87));
        filterArray.add(new FilterBean(R.drawable.reflection1_filter, new ReflectionFilter(true),88));
        filterArray.add(new FilterBean(R.drawable.reflection2_filter, new ReflectionFilter(false),89));
        filterArray.add(new FilterBean(R.drawable.saturationmodify_filter, new SaturationModifyFilter(),90));
        filterArray.add(new FilterBean(R.drawable.vignette_filter, new VignetteFilter(),91));
        filterArray.add(new FilterBean(R.drawable.waterwave_filter, new WaterWaveFilter(),92));
        filterArray.add(new FilterBean(R.drawable.vintage_filter, new VintageFilter(),93));
        filterArray.add(new FilterBean(R.drawable.oldphoto_filter, new OldPhotoFilter(),94));
        filterArray.add(new FilterBean(R.drawable.sepia_filter, new SepiaFilter(),95));
        filterArray.add(new FilterBean(R.drawable.rainbow_filter, new RainBowFilter(),96));
        filterArray.add(new FilterBean(R.drawable.feather_filter, new FeatherFilter(),97));
        filterArray.add(new FilterBean(R.drawable.xradiation_filter, new XRadiationFilter(),98));
        filterArray.add(new FilterBean(R.drawable.nightvision_filter, new NightVisionFilter(),99));

    }

    public class processImageTask extends AsyncTask<Void, Void, Bitmap> {
        private IImageFilter filter;
        private Activity activity = null;

        public processImageTask(Activity activity, IImageFilter imageFilter) {
            this.filter = imageFilter;
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            relativeLayout.setVisibility(View.VISIBLE);
        }

        public Bitmap doInBackground(Void... params) {
            Image img = null;
            try {
                img = new Image(mBitmap);
                if (filter != null) {
                    img = filter.process(img);
                    img.copyPixelsFromBuffer();
                }
                return img.getImage();
            } catch (Exception e) {
                if (img != null && img.destImage.isRecycled()) {
                    img.destImage.recycle();
                    img.destImage = null;
                    System.gc(); // 提醒系统及时回收
                }
            } finally {
                if (img != null && img.image.isRecycled()) {
                    img.image.recycle();
                    img.image = null;
                    System.gc(); // 提醒系统及时回收
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                super.onPostExecute(result);
                imageView.setImageBitmap(result);
            }
            relativeLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        super.onDestroy();
    }
}
