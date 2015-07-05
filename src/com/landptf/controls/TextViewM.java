package com.landptf.controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * ��дTextView ʵ�ֵ���ı�������ɫ
 * @author landptf
 * @date 2015-6-6
 */
public class TextViewM extends TextView{
	private int textColori = 0;//�ؼ���������ɫ��Int��
	private String textColors = "";//�ؼ���������ɫ��String��
	private int textColorSeletedi = 0;//�ؼ������º��������ɫ��Int��
	private String textColorSeleteds = "";//�ؼ������º��������ɫ��String��
	
	public TextViewM(Context context) {
		this(context, null);
	}

	public TextViewM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * ʵ��TextView�Ĺ��췽��
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public TextViewM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//����TextView��Touch�¼�
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				//������ɫ�仯
				setColor(event.getAction());
				//ע��˴��ķ���ֵ����������TextView��Click�¼����򷵻�false
				return true;
			}
		});
	}
	//������ɫ�仯���÷���Ϊprivate�������⹫��
	private void setColor(int state){
		try {
			//���ݴ�������MotionEventֵ������������ɫ
			if (state == MotionEvent.ACTION_DOWN) {
				//��갴��
				if (textColorSeletedi != 0) {
					setTextColor(textColorSeletedi);
				}else if (!textColorSeleteds.equals("")) {
					setTextColor(Color.parseColor(textColorSeleteds));
				}
			}
			if (state == MotionEvent.ACTION_UP) {
				//���̧��
				if (textColori == 0 && textColors.equals("")) {
					//���Ϊ������ɫֵ����Ĭ��Ϊ��ɫ
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
	 * �������ֵ���ɫ
	 * Ϊ�˲����ԭsetTextColor�ĳ�ͻ���ں���ӡ�i��
	 * @param color int����
	 */
	public void setTextColori(int color) {
		this.textColori = color;
		try {
			this.setTextColor(color);
		} catch (Exception e) {
		}
	}

	/**
	 * �������ֵ���ɫ
	 * Ϊ�˲����ԭsetTextColor�ĳ�ͻ���ں���ӡ�s��
	 * @param color String����
	 */
	public void setTextColors(String color) {
		this.textColors = color;
		try {
			this.setTextColor(Color.parseColor(color));
		} catch (Exception e) {
		}
	}

	/**
	 * �������ֱ����º����ɫ
	 * @param color int����
	 */
	public void setTextColorSeleted(int textColorSeletedi) {
		this.textColorSeletedi = textColorSeletedi;
	}
	
	/**
	 * �������ֱ����º����ɫ
	 * @param color String����
	 */
	public void setTextColorSeleted(String textColorSeleteds) {
		this.textColorSeleteds = textColorSeleteds;
	}
}
