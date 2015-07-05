package com.landptf.controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 重写TextView 实现点击改变字体颜色
 * @author landptf
 * @date 2015-6-6
 */
public class TextViewM extends TextView{
	private int textColori = 0;//控件的文字颜色，Int型
	private String textColors = "";//控件的文字颜色，String型
	private int textColorSeletedi = 0;//控件被按下后的文字颜色，Int型
	private String textColorSeleteds = "";//控件被按下后的文字颜色，String型
	
	public TextViewM(Context context) {
		this(context, null);
	}

	public TextViewM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 实现TextView的构造方法
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TextViewM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//设置TextView的Touch事件
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				//设置颜色变化
				setColor(event.getAction());
				//注意此处的返回值，若想设置TextView的Click事件，则返回false
				return true;
			}
		});
	}
	//设置颜色变化，该方法为private，不对外公开
	private void setColor(int state){
		try {
			//根据传过来的MotionEvent值来设置文字颜色
			if (state == MotionEvent.ACTION_DOWN) {
				//鼠标按下
				if (textColorSeletedi != 0) {
					setTextColor(textColorSeletedi);
				}else if (!textColorSeleteds.equals("")) {
					setTextColor(Color.parseColor(textColorSeleteds));
				}
			}
			if (state == MotionEvent.ACTION_UP) {
				//鼠标抬起
				if (textColori == 0 && textColors.equals("")) {
					//如果为设置颜色值，则默认为黑色
					setTextColor(Color.BLACK);
				}else if (textColori != 0) {
					setTextColor(textColori);
				}else {
					setTextColor(Color.parseColor(textColors));
				}
			}
		} catch (Exception e) {
		}
		
	}
	
	/**
	 * 设置文字的颜色
	 * 为了不造成原setTextColor的冲突，在后面加“i”
	 * @param color int类型
	 */
	public void setTextColori(int color) {
		this.textColori = color;
		try {
			this.setTextColor(color);
		} catch (Exception e) {
		}
	}

	/**
	 * 设置文字的颜色
	 * 为了不造成原setTextColor的冲突，在后面加“s”
	 * @param color String类型
	 */
	public void setTextColors(String color) {
		this.textColors = color;
		try {
			this.setTextColor(Color.parseColor(color));
		} catch (Exception e) {
		}
	}

	/**
	 * 设置文字被按下后的颜色
	 * @param color int类型
	 */
	public void setTextColorSeleted(int textColorSeletedi) {
		this.textColorSeletedi = textColorSeletedi;
	}
	
	/**
	 * 设置文字被按下后的颜色
	 * @param color String类型
	 */
	public void setTextColorSeleted(String textColorSeleteds) {
		this.textColorSeleteds = textColorSeleteds;
	}
}
