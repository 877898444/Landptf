package com.landptf.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 自定义底部选项卡
 * @author landptf
 * @date 2015-7-29
 */
public class MenuItemM extends RelativeLayout{
	//控件
	private Context context;
	private ImageView ivIcon;
	private TextView tvItem;
	private ImageView ivNew;
	private ImageView ivMore;
	private ButtonM btnmNums;
	
	private int backgroundImagei = 0;
	private Bitmap backgroundImageb = null;
	private int backgroundImageSeletedi = 0;
	private Bitmap backgroundImageSeletedb = null;
	private int textColori = 0;
	private String textColors = "";
	private int textColorSeletedi = 0;
	private String textColorSeleteds = "";
	
	private OnClickListenerM onClickListener = null;
	
	/** 
     * 一定一个接口 
     */ 
    public interface OnClickListenerM{  
        public void onClick(View v);
    }
    /** 
     * 自定义控件的自定义事件 
     * @param iBack 接口类型 
     */  
    public void setOnClickListener(OnClickListenerM onClickListener){  
    	this.onClickListener = onClickListener;  
    }
    
	public MenuItemM(Context context) {
		this(context,null);
	}
	
	public MenuItemM(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MenuItemM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickListener != null) {
					setSelectedState(true);
					onClickListener.onClick(v);
				}
			}
		});
		init();
	}
	private void init() {
		//图标
		ivIcon = new ImageView(context);
		//如果未指定ID则默认为-1，不可以当做参照控件使用
		ivIcon.setId(1);
		RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		ivIcon.setLayoutParams(lp);
		//文字描述
		tvItem = new TextView(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		lp.addRule(RelativeLayout.BELOW, ivIcon.getId());
		tvItem.setLayoutParams(lp);
		//NEW提示
		ivNew = new ImageView(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
		lp.addRule(RelativeLayout.ALIGN_TOP, ivIcon.getId());
		ivNew.setLayoutParams(lp);
		setNewVisible(false);
		//More提示
		ivMore = new ImageView(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
		lp.addRule(RelativeLayout.ALIGN_TOP, ivIcon.getId());
		ivMore.setLayoutParams(lp);
		setMoreVisible(false);
		//未读数量提示
		btnmNums = new ButtonM(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
		lp.addRule(RelativeLayout.ALIGN_TOP, ivIcon.getId());
		btnmNums.setLayoutParams(lp);
		btnmNums.setFillet(true);
		btnmNums.setTextColori(Color.WHITE);
		btnmNums.setTextSize(12);
		setNumsCount(0);
		
		addView(ivIcon);
		addView(tvItem);
		addView(ivNew);
		addView(ivMore);
		addView(btnmNums);
	}
	
	/**
	 * 设置选中状态
	 */
	public void setSelectedState(boolean selectedState){
		if (selectedState) {
			if (backgroundImageSeletedi != 0) {
				ivIcon.setImageResource(backgroundImageSeletedi);
			}else if(backgroundImageSeletedb != null){
				ivIcon.setImageBitmap(backgroundImageSeletedb);
			}
			if (textColorSeletedi != 0) {
				tvItem.setTextColor(textColorSeletedi);
			}else if (!textColorSeleteds.equals("")) {
				tvItem.setTextColor(Color.parseColor(textColorSeleteds));
			}
		}else {
			if (backgroundImagei != 0) {
				ivIcon.setImageResource(backgroundImagei);
			}else if(backgroundImageb != null){
				ivIcon.setImageBitmap(backgroundImageb);
			}
			if (textColori != 0) {
				tvItem.setTextColor(textColori);
			}else if (!textColors.equals("")) {
				tvItem.setTextColor(Color.parseColor(textColors));
			}
		}
		
	}
	
	
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setBackground(int icon) {
		this.backgroundImagei = icon;
		ivIcon.setImageResource(icon);
	}
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setBackground(Bitmap icon) {
		this.backgroundImageb = icon;
		ivIcon.setImageBitmap(icon);
	}
	/**
	 * 设置选中的图标
	 * @param icon
	 */
	public void setBackgroundSelected(int icon) {
		this.backgroundImageSeletedi = icon;
	}
	/**
	 * 设置选中的图标
	 * @param icon
	 */
	public void setBackgroundSelected(Bitmap icon) {
		this.backgroundImageSeletedb = icon;
	}
	/**
	 * 设置文字
	 * @param text
	 */
	public void setText(String text){
		tvItem.setText(text);
	}
	/**
	 * 设置文字的颜色
	 * @param color
	 */
	public void setTextColor(int color){
		this.textColori = color;
		tvItem.setTextColor(color);
	}
	/**
	 * 设置文字的颜色
	 * @param color
	 */
	public void setTextColor(String color) {
		this.textColors = color;
		tvItem.setTextColor(Color.parseColor(color));
	}
	/**
	 * 设置选中文字的颜色
	 * @param color
	 */
	public void setTextColorSelected(int color){
		this.textColorSeletedi = color;
	}
	/**
	 * 设置选中文字的颜色
	 * @param color
	 */
	public void setTextColorSelected(String color) {
		this.textColorSeleteds = color;
	}
	/**
	 * 设置NEW提示
	 * @param newIcon
	 */
	public void setNewImage(int newIcon) {
		ivNew.setImageResource(newIcon);
	}
	/**
	 * 设置NEW提示
	 * @param newIcon
	 */
	public void setNewImage(Bitmap newIcon) {
		ivNew.setImageBitmap(newIcon);
	}
	/**
	 * 设置NEW提示图标是否显示
	 * @param isVisible
	 */
	public void setNewVisible(boolean isVisible){
		ivNew.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}
	/**
	 * 设置More提示
	 * @param moreIcon
	 */
	public void setMoreImage(int moreIcon) {
		ivMore.setImageResource(moreIcon);
	}
	/**
	 * 设置More提示
	 * @param moreIcon
	 */
	public void setMoreImage(Bitmap moreIcon) {
		ivMore.setImageBitmap(moreIcon);
	}
	/**
	 * 设置More提示图标是否显示
	 * @param isVisible
	 */
	public void setMoreVisible(boolean isVisible){
		ivMore.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}
	
	/**
	 * 设置Nums提示图标
	 * @param numsIcon
	 */
	public void setNumsImage(int numsIcon) {
		btnmNums.setBackGroundImage(numsIcon);
	}
	/**
	 * 设置未读数量
	 * @param count
	 */
	public void setNumsCount(int count){
		btnmNums.setText(String.valueOf(count));
		if (count > 0) {
			setNumsVisible(true);
		}else {
			setNumsVisible(false);
		}
	}
	/**
	 * 设置Nums提示图标是否显示
	 * @param isVisible
	 */
	public void setNumsVisible(boolean isVisible){
		btnmNums.setVisibility(isVisible ? View.VISIBLE : View.GONE);
	}
}
