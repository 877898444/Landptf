package com.landptf.controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/** 
* @ClassName: RelativeLayoutM 
* @Description: RelativeLayoutM that is press 
* @author landptf
* @date 2015-9-12 下午6:16:47  
*/ 
public class RelativeLayoutM extends RelativeLayout{

	private int backColori = 0;
	private String backColors = "'";
	private int backColorSeletedi = 0;
	private String backColorSeleteds = "";
	
	public RelativeLayoutM(Context context) {
		this(context, null);
	}
	
	public RelativeLayoutM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public RelativeLayoutM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				setColor(event.getAction());
				return false;
			}
		});
	}
	//根据按下状态改变背景色
	public void setColor(int state){
		if (state == MotionEvent.ACTION_DOWN) {
			if (backColorSeletedi != 0) {
				setBackgroundColor(backColorSeletedi);
			}else if (!backColorSeleteds.equals("")) {
				setBackgroundColor(Color.parseColor(backColorSeleteds));
			}
		}
		if (state == MotionEvent.ACTION_UP) {
			if (backColori != 0) {
				setBackgroundColor(backColori);
			}else {
				setBackgroundColor(Color.parseColor(backColors));
			}
		}
	}

	public void setBackColor(String color) {
		this.backColors = color;
		this.setBackgroundColor(Color.parseColor(color));
	}

	public void setBackColor(int color) {
		this.backColori = color;
		this.setBackgroundColor(color);
	}

	public void setBackColorSeleted(int backColorSeletedi) {
		this.backColorSeletedi = backColorSeletedi;
	}

	public void setBackColorSeleted(String backColorSeleteds) {
		this.backColorSeleteds = backColorSeleteds;
	}
}
