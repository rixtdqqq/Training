package com.zhuyx.training.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseFragment;
import com.zhuyx.training.util.TrainingUtils;
import com.zhuyx.training.widget.TrainingGlideTestView;

import org.antlr.runtime.MismatchedTokenException;

import java.io.File;


public class Blank2Fragment extends TrainingBaseFragment {
    private ImageView mImageView, mImageView1, mImageView2, mImageView3, mImageView4, mImageView5, mImageView6;
    private TextView tvGlide2Path;
    private TrainingGlideTestView mTestView;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.training_f_blank2;
    }

    @Override
    public void initView(View view) {
        mImageView = (ImageView) view.findViewById(R.id.iv_glide);//网络图片
        mImageView1 = (ImageView) view.findViewById(R.id.iv_glide1);//本地图片
        mImageView2 = (ImageView) view.findViewById(R.id.iv_glide2);//从文件中加载
        tvGlide2Path = (TextView) view.findViewById(R.id.iv_glide2_path);
        mImageView3 = (ImageView) view.findViewById(R.id.iv_glide3);//从 Uri 中加载
        mImageView4 = (ImageView) view.findViewById(R.id.iv_glide4);//网络gif
        mImageView5 = (ImageView) view.findViewById(R.id.iv_glide5);
        mImageView6 = (ImageView) view.findViewById(R.id.iv_glide6);
        mTestView = (TrainingGlideTestView) view.findViewById(R.id.glide_test_view);
    }

    /**
     * DiskCacheStrategy.NONE 什么都不缓存，就像刚讨论的那样
     * DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
     * DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
     * DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
     * Priority.LOW
     * Priority.NORMAL
     * Priority.HIGH
     * Priority.IMMEDIATE
     */

    @Override
    public void initData() {
        Glide.with(Blank2Fragment.this)//网络图片
                .load("http://cdn.duitang.com/uploads/blog/201404/12/20140412173010_weCjm.thumb.700_0.jpeg")
                .priority(Priority.LOW)
                .thumbnail(0.1f)//缩略图，如果缩略图对于任何原因，在原始图像到达之后，它不会取代原始图像。它只会被抹除
                .placeholder(R.mipmap.ic_launcher)//占位符
                .error(R.mipmap.ic_launcher)//错误占位符
                .diskCacheStrategy(DiskCacheStrategy.NONE)//跳过磁盘缓存,默认的它将仍然使用内存缓存
                .skipMemoryCache(true)//diskCacheStrategy,skipMemoryCache同时调用不会有缓存
                .crossFade(300)//淡入淡出动画
                .into(mImageView);
        Glide.with(Blank2Fragment.this)//本地图片
                .load(R.mipmap.back)
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()//不需要动画
                .override(48, 48)//如果你知道这个图片多少大，用 override 去提供明确的尺寸
                .centerCrop()//缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示
                .into(mImageView1);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "a.png");
        Glide.with(Blank2Fragment.this)//从文件中加载
                .load(file)
                .skipMemoryCache(true)//跳过内存缓存,但仍然利用磁盘缓存来避免重复的网络请求。
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(200, 100)
                .crossFade()
                .fitCenter()//缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView
                .into(mImageView2);
        tvGlide2Path.setText(file.getAbsolutePath());
        Glide.with(Blank2Fragment.this)//从 Uri 中加载
                .load(TrainingUtils.resourceIdToUri(mContext, R.mipmap.weixin_icon))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mImageView3);
        Glide.with(Blank2Fragment.this)
                .load("http://img1.imgtn.bdimg.com/it/u=1449286795,1718596860&fm=21&gp=0.jpg")
                .asGif()//Gif检查
//                .asBitmap() //Gif 转为 Bitmap
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(mImageView4);
        File videoPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera/VID_20161016_144921.mp4");
        Glide.with(Blank2Fragment.this)
                .load(Uri.fromFile(videoPath))
                .placeholder(R.mipmap.weixin_icon)
                .error(R.mipmap.ic_launcher)
                .into(mImageView5);
        loadSimpleTarget();
        loadViewTarget();

    }

    @Override
    public void initListeners() {

    }


    private void loadSimpleTarget() {
        SimpleTarget mTarget = new SimpleTarget<GifDrawable>() {
            @Override
            public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
                mImageView6.setImageDrawable(resource.mutate());
            }
        };
        Glide.with(Blank2Fragment.this)
                .load("http://img3.imgtn.bdimg.com/it/u=2182299929,3303854252&fm=21&gp=0.jpg")
                .asGif()
                .into(mTarget);
    }


    private void loadViewTarget() {
        ViewTarget mViewTarget = new ViewTarget<TrainingGlideTestView, GlideDrawable>(mTestView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                mTestView.setImageResource(resource.getCurrent());
            }
        };
        Glide.with(getActivity().getApplicationContext())
                .load("http://img3.imgtn.bdimg.com/it/u=2182299929,3303854252&fm=21&gp=0.jpg")
                .error(R.mipmap.weixin_icon)
                .placeholder(R.mipmap.ic_launcher)
                .into(mViewTarget);
    }

}
