package com.landptf.controls;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.landptf.tools.ConvertM;

/** 
* @ClassName: LoopImageM 
* @Description: 图片轮播控件，支持自动播放和手动滑动播放
* @author landptf
* @date 2016-4-19 下午9:47:29  
*/ 
public class LoopImageM extends RelativeLayout{
	private static final String TAG  			= "LoopImageM";
	
	private View[] vIndex = null;
	//放轮播图片的ImageView 的list
	private List<ImageView> imageViewsList;
	//默认背景图
	private int defaultImageResid = 0;
	//轮播图片数量
	private int imageCount = 4;
    //自动轮播的时间间隔(s)
    private int intervalTime = 5;
    //自动轮播启用开关
    private boolean isAutoPlay = true; 
	//自定义轮播图的资源
    private int[] images;
    
    private ViewPager viewPager;
    //当前轮播页
    private int currentItem  = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    private GradientDrawable gradientDrawable;
    private Context context;
    
    public LoopImageM(Context context) {
    	this(context, null);
    }
    
    public LoopImageM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoopImageM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		gradientDrawable = new GradientDrawable();
		initView();
		if(isAutoPlay){
            startPlay();
        }
	}
	
	public void setImage(int[] bitmapImage){
		this.images = bitmapImage;
	}
	
	public void setImageCount(int imageCount){
		this.imageCount = imageCount;
		vIndex = new View[imageCount];
		imageViewsList = new ArrayList<ImageView>();
		LinearLayout rlRoot = new LinearLayout(context);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rlRoot.setLayoutParams(lp);
		rlRoot.setPadding(ConvertM.dp2px(context, 8), ConvertM.dp2px(context, 8), ConvertM.dp2px(context, 8), ConvertM.dp2px(context, 8));
		rlRoot.setGravity(Gravity.RIGHT);
		rlRoot.setOrientation(LinearLayout.HORIZONTAL);
		rlRoot.setBackgroundColor(Color.parseColor("#00ffffff"));
		for (int i = 0; i < imageCount; i++) {
			ImageView view =  new ImageView(context);
        	view.setScaleType(ScaleType.FIT_XY);
        	if (0 != defaultImageResid) {
        		view.setBackgroundResource(defaultImageResid);
			}
        	imageViewsList.add(view);
			
			vIndex[i] = new View(context);
			LinearLayout.LayoutParams lpView = new LinearLayout.LayoutParams(ConvertM.dp2px(context, 8),ConvertM.dp2px(context, 8));
			lpView.rightMargin = ConvertM.dp2px(context, 5);
			vIndex[i].setLayoutParams(lpView);
			gradientDrawable.setShape(GradientDrawable.OVAL);
			gradientDrawable.setColor(Color.parseColor("#696969"));
			vIndex[i].setBackgroundDrawable(gradientDrawable);
			vIndex[i].bringToFront();
			rlRoot.addView(vIndex[i]);
			viewPager.setAdapter(new MyPagerAdapter());
		}
		addView(rlRoot);
	}
	
	/**
	 * 设置默认背景图
	 * @param defaultBitMap
	 */
	public void setDefaultImage(int resid) {
		this.defaultImageResid = resid;
	}
	
	/**
	 * 设置自动轮播的时间间隔
	 * @param intervalTime
	 */
	public void setIntervalTime(int intervalTime)  {
		this.intervalTime = intervalTime;
	}
	
	/**
	 * 设置自动轮播开关
	 * @param isAutoPlay
	 */
	public void setAutoPlay(boolean isAutoPlay) {
		this.isAutoPlay = isAutoPlay;
	}
	
	/**
     * 开始轮播图切换
     */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, intervalTime, TimeUnit.SECONDS);
    }

	private void initView() {
		viewPager = new ViewPager(context);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		viewPager.setLayoutParams(lp);
		addView(viewPager);
        viewPager.setFocusable(true);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}
	
	/**
     * 填充ViewPager的页面适配器
     * 
     */
    private class MyPagerAdapter  extends PagerAdapter{
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }
        @Override
        public Object instantiateItem(View container, int position) {
			ImageView imageView = imageViewsList.get(position);
			if(images != null && images[position] != 0){
				imageView.setBackgroundResource(images[position]);
			}
			((ViewPager) container).addView(imageViewsList.get(position));
			return imageViewsList.get(position);
        }
        @Override
        public int getCount() {
            return imageCount;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }
        @Override
        public Parcelable saveState() {
            return null;
        }
        @Override
        public void startUpdate(View arg0) {
        }
        @Override
        public void finishUpdate(View arg0) {
        }
    }
    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     * 
     */
	private class MyPageChangeListener implements OnPageChangeListener {
		boolean isAutoPlay = false;
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
					viewPager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
					viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
				}
				break;
			}
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int pos) {
			currentItem = pos;
			Log.d(TAG, "position = " + pos);
			for (int i = 0; i < imageCount; i++) {
				((View) vIndex[i]).setBackgroundDrawable(gradientDrawable);
			}
			GradientDrawable gradientDrawablePos = new GradientDrawable();
			gradientDrawablePos.setShape(GradientDrawable.OVAL);
			gradientDrawablePos.setColor(Color.parseColor("#ff0000"));
			((View) vIndex[pos]).setBackgroundDrawable(gradientDrawablePos);
		}
	}
	
	/**
     *执行轮播图切换任务
     *
     */
    private class SlideShowTask implements Runnable{
        @Override
        public void run() {
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }
    
	private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
    };
}
