package com.landptf.controls;

import com.landptf.controls.MenuItemM.OnClickListenerM;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 继承RelativeLayout，自定义标题栏
 * @author landptf
 * @date 2015-6-10
 */
public class TitleBarM extends RelativeLayout{
	
	/**
	 * position is left
	 */
	public static final int TITLE_POSITION_LEFT = 0;
	/**
	 * position is center
	 */
	public static final int TITLE_POSITION_CENTER = 1;
	/**
	 * position is right
	 */
	public static final int TITLE_POSITION_RIGHT = 2;
	
	private static final int MIMLEFT_ID = 0x0001;
	private static final int BTNRIGHT_ID = 0x0002;
	
	
	private Context context;
	//定义三个控件
	private MenuItemM mimLeft;
	private TextView tvTitle;
	private ButtonM btnRight;
	//定义左侧控件的接口
	private OnClickListenerL onClickListenerL = null;
	//定义右侧控件的接口
	private OnClickListenerR onClickListenerR = null;
	
	public interface OnClickListenerL{
		//定义一个方法
		public void onClick(View v);
	}
	
	public interface OnClickListenerR{
		//定义一个方法
		public void onClick(View v);
	}

	/**
	 * 为左侧控件绑定事件
	 * @param onClickListenerL
	 */
	public void setOnClickLisenerL(OnClickListenerL onClickListenerL) {
		this.onClickListenerL = onClickListenerL;
	}
	
	/**
	 * 为右侧控件绑定事件
	 * @param onClickListenerR
	 */
	public void setOnClickLisenerR(OnClickListenerR onClickListenerR) {
		this.onClickListenerR = onClickListenerR;
	}

	public TitleBarM(Context context) {
		this(context, null);
	}
	
	public TitleBarM(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public TitleBarM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		//设置RelativeLayout的布局
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		//设置默认的背景色
		setBackgroundColor(Color.parseColor("#009933"));
		init();
	}

	private void init() {
		mimLeft = new MenuItemM(context);
		mimLeft.setId(MIMLEFT_ID);
		RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//垂直居中
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		//设置距离左侧5dp
		lp.leftMargin = dp2px(context, 5);
		mimLeft.setLayoutParams(lp);
		mimLeft.setTextSize(18);//设置字体大小,默认为16
		mimLeft.setTextColor(Color.WHITE);//默认字体颜色为白色
		mimLeft.setTextColorSelected("#909090");//按下后的字体颜色
		mimLeft.setIconStyle(MenuItemM.STYLE_ICON_LEFT);
		mimLeft.setOnClickListener(new OnClickListenerM() {
			@Override
			public void onClick(View v) {
				if (onClickListenerL != null) {
					onClickListenerL.onClick(v);
				}
			}
		});
		//初始化中间标题控件
		tvTitle = new TextView(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//使其处于父控件的中间位置，也有一些APP的标题偏左，可根据项目需要自行调整，也可动态设置
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		tvTitle.setLayoutParams(lp);
		//设置标题文字颜色
		tvTitle.setTextColor(Color.WHITE);
		//设置标题文字大小
		tvTitle.setTextSize(20);//默认为18
		//初始化右侧ButtonM
		btnRight = new ButtonM(context);
		btnRight.setId(BTNRIGHT_ID);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.rightMargin = dp2px(context, 5);
		//垂直居中
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		//居于父控件的右侧
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		btnRight.setLayoutParams(lp);
		btnRight.setTextSize(16);//默认有16
		btnRight.setVisibility(View.GONE); //默认隐藏右侧控件
		btnRight.setTextColori(Color.WHITE);
		btnRight.setTextColorSelected("#909090");
		btnRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickListenerR != null) {
					onClickListenerR.onClick(v);
				}
			}
		});
		
		//分别将三个控件加入到容器中
		addView(mimLeft);
		addView(tvTitle);
		addView(btnRight);
	}

	/**
	 * 设置标题栏的背景色 ，String
	 * @param backColors
	 */
	public void setBackColor(String backColors) {
		setBackgroundColor(Color.parseColor(backColors));
	}

	/**
	 * 设置标题栏的背景色 ，int
	 * @param backColori
	 */
	public void setBackColor(int backColori) {
		setBackgroundColor(backColori);
	}

	/**
	 * 设置左侧控件显示的文字
	 * @param leftText
	 */
	public void setLeftText(String leftText) {
		mimLeft.setText(leftText);
	}
	
	/**
	 * 设置左侧控件的文字颜色
	 * @param leftBackImageSeleted
	 */
	public void setLeftTextColor(int resId){
		mimLeft.setTextColor(resId);
	}
	
	/**
	 * 设置左侧控件选中的文字颜色
	 * @param leftBackImageSeleted
	 */
	public void setLeftTextColorSelected(int resId){
		mimLeft.setTextColorSelected(resId);
	}
	
	/**
	 * 设置左侧控件的背景图
	 * @param leftBackImage
	 */
	public void setLeftBackImage(int leftBackImage) {
		if (leftBackImage != 0) {
			mimLeft.setBackground(leftBackImage);
		}
	}

	/**
	 * 设置左侧控件选中的背景图
	 * @param leftBackImageSeleted
	 */
	public void setLeftBackImageSeleted(int leftBackImageSeleted) {
		if (leftBackImageSeleted != 0) {
			mimLeft.setBackgroundSelected(leftBackImageSeleted);
		}
	}

	/**
	 * 设置左侧控件显示属性，默认为显示
	 * @param leftVisible
	 */
	public void setLeftVisible(boolean leftVisible) {
		mimLeft.setVisibility(leftVisible ? View.VISIBLE : View.GONE); 
	}

	/**
	 * 设置左侧控件显示的字体大小
	 * @param leftTextSize
	 */
	public void setLeftTextSize(float leftTextSize) {
		mimLeft.setTextSize(leftTextSize);
	}
	
	/**
	 * 设置中间控件显示属性，默认为显示
	 * @param leftVisible
	 */
	public void setTitleVisible(boolean titleVisible) {
		tvTitle.setVisibility(titleVisible ? View.VISIBLE : View.GONE); 
	}

	/**
	 * 设置中间控件的文字
	 * @param titleText
	 */
	public void setTitleText(String titleText) {
		tvTitle.setText(titleText);
	}

	/**
	 * 设置中间控件的文字的大小
	 * @param titleTextSize
	 */
	public void setTitleTextSize(float titleTextSize) {
		tvTitle.setTextSize(titleTextSize);
	}
	/**
	 * 设置中间控件文字的颜色
	 * @param color
	 */
	public void setTitleTextColor(int color){
		tvTitle.setTextColor(color);
	}
	/**
	 * 设置中间控件文字的颜色
	 * @param color
	 */
	public void setTitleTextColor(String color){
		tvTitle.setTextColor(Color.parseColor(color));
	}
	/**
	 * 设置右侧控件显示的文字
	 * @param leftText
	 */
	public void setRightText(String rightText) {
		btnRight.setText(rightText);
	}

	/**
	 * 设置右侧控件的背景图
	 * @param leftBackImage
	 */
	public void setRightBackImage(int rightBackImage) {
		if (rightBackImage != 0) {
			btnRight.setBackGroundImage(rightBackImage);
		}
	}

	/**
	 * 设置右侧控件选中的背景图
	 * @param leftBackImageSeleted
	 */
	public void setRightBackImageSeleted(int rightBackImageSeleted) {
		if (rightBackImageSeleted != 0) {
			btnRight.setBackGroundImageSeleted(rightBackImageSeleted);
		}
	}

	/**
	 * 设置右侧控件显示的字体大小
	 * @param leftTextSize
	 */
	public void setRightTextSize(float rightTextSize) {
		btnRight.setTextSize(rightTextSize);
	}
	
	/**
	 * 设置右侧控件显示属性，默认为隐藏	
	 * @param leftVisible
	 */
	public void setRightVisible(boolean rightVisible) {
		btnRight.setVisibility(rightVisible ? View.VISIBLE : View.GONE); 
	}
	
	public void setTitlePosition(int position){
		RelativeLayout.LayoutParams lp;
		switch (position) {
		case TITLE_POSITION_LEFT:
			lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			lp.addRule(RelativeLayout.RIGHT_OF,MIMLEFT_ID);
			lp.leftMargin = dp2px(context, 5);
			tvTitle.setLayoutParams(lp);
			break;
		case TITLE_POSITION_CENTER:
			
			break;
		case TITLE_POSITION_RIGHT:
			lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			lp.addRule(RelativeLayout.LEFT_OF,BTNRIGHT_ID);
			lp.rightMargin = dp2px(context, 5);
			tvTitle.setLayoutParams(lp);
			break;
		default:
			break;
		}
	}
	
	//定义一个私有方法dp2px
	private int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
}
