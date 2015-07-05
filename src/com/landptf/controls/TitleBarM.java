package com.landptf.controls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * �̳�RelativeLayout���Զ��������
 * @author landptf
 * @date 2015-6-10
 */
public class TitleBarM extends RelativeLayout{
	private Context context;
	//���������ؼ���
	private ButtonM btnLeft;
	private TextView tvTitle;
	private ButtonM btnRight;
	//�������ؼ��Ľӿ�
	private OnClickListenerL onClickListenerL = null;
	//�����Ҳ�ؼ��Ľӿ�
	private OnClickListenerR onClickListenerR = null;
	
	public interface OnClickListenerL{
		//����һ������
		public void onClick(View v);
	}
	
	public interface OnClickListenerR{
		//����һ������
		public void onClick(View v);
	}

	/**
	 * Ϊ���ؼ����¼�
	 * @param onClickListenerL
	 */
	public void setOnClickLisenerL(OnClickListenerL onClickListenerL) {
		this.onClickListenerL = onClickListenerL;
	}
	
	/**
	 * Ϊ�Ҳ�ؼ����¼�
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
		//����RelativeLayout�Ĳ���
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		//����Ĭ�ϵı���ɫ
		setBackgroundColor(Color.parseColor("#B674D2"));
		init();
	}

	private void init() {
		//��ʼ�����ButtonM
		btnLeft = new ButtonM(context);
		RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//��ֱ����
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		//���þ������10dp
		lp.leftMargin = dp2px(context, 10);
		btnLeft.setLayoutParams(lp);
		btnLeft.setTextSize(16);//���������С,Ĭ��Ϊ16
		btnLeft.setTextColori(Color.WHITE);//Ĭ��������ɫΪ��ɫ
		btnLeft.setTextColorSelected("#909090");//���º��������ɫ
		//���������¼�
		btnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickListenerL != null) {
					onClickListenerL.onClick(v);
				}
			}
		});
		//��ʼ���м����ؼ�
		tvTitle = new TextView(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		//ʹ�䴦�ڸ��ؼ����м�λ�ã�Ҳ��һЩAPP�ı���ƫ�󣬿ɸ�����Ŀ��Ҫ���е�����Ҳ�ɶ�̬����
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		tvTitle.setLayoutParams(lp);
		//���ñ���������ɫ
		tvTitle.setTextColor(Color.WHITE);
		//���ñ������ִ�С
		tvTitle.setTextSize(18);//Ĭ��Ϊ18
		//��ʼ���Ҳ�ButtonM
		btnRight = new ButtonM(context);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.rightMargin = dp2px(context, 10);
		//��ֱ����
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		//���ڸ��ؼ����Ҳ�
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		btnRight.setLayoutParams(lp);
		btnRight.setTextSize(16);//Ĭ����16
		btnRight.setVisibility(View.GONE); //Ĭ�������Ҳ�ؼ�
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
		
		//�ֱ������ؼ����뵽������
		addView(btnLeft);
		addView(tvTitle);
		addView(btnRight);
	}

	/**
	 * ���ñ������ı���ɫ ��String
	 * @param backColors
	 */
	public void setBackColor(String backColors) {
		setBackgroundColor(Color.parseColor(backColors));
	}

	/**
	 * ���ñ������ı���ɫ ��int
	 * @param backColori
	 */
	public void setBackColor(int backColori) {
		setBackgroundColor(backColori);
	}

	/**
	 * �������ؼ���ʾ������
	 * @param leftText
	 */
	public void setLeftText(String leftText) {
		btnLeft.setText(leftText);
	}

	/**
	 * �������ؼ��ı���ͼ
	 * @param leftBackImage
	 */
	public void setLeftBackImage(int leftBackImage) {
		if (leftBackImage != 0) {
			btnLeft.setBackGroundImage(leftBackImage);
		}
	}

	/**
	 * �������ؼ�ѡ�еı���ͼ
	 * @param leftBackImageSeleted
	 */
	public void setLeftBackImageSeleted(int leftBackImageSeleted) {
		if (leftBackImageSeleted != 0) {
			btnLeft.setBackGroundImageSeleted(leftBackImageSeleted);
		}
	}

	/**
	 * �������ؼ���ʾ���ԣ�Ĭ��Ϊ��ʾ
	 * @param leftVisible
	 */
	public void setLeftVisible(boolean leftVisible) {
		btnLeft.setVisibility(leftVisible ? View.VISIBLE : View.GONE); 
	}

	/**
	 * �������ؼ���ʾ�������С
	 * @param leftTextSize
	 */
	public void setLeftTextSize(float leftTextSize) {
		btnLeft.setTextSize(leftTextSize);
	}
	
	/**
	 * �����м�ؼ���ʾ���ԣ�Ĭ��Ϊ��ʾ
	 * @param leftVisible
	 */
	public void setTitleVisible(boolean titleVisible) {
		tvTitle.setVisibility(titleVisible ? View.VISIBLE : View.GONE); 
	}

	/**
	 * �����м�ؼ�������
	 * @param titleText
	 */
	public void setTitleText(String titleText) {
		tvTitle.setText(titleText);
	}

	/**
	 * �����м�ؼ������ֵĴ�С
	 * @param titleTextSize
	 */
	public void setTitleTextSize(float titleTextSize) {
		tvTitle.setTextSize(titleTextSize);
	}
	
	/**
	 * �����Ҳ�ؼ���ʾ������
	 * @param leftText
	 */
	public void setRightText(String rightText) {
		btnRight.setText(rightText);
	}

	/**
	 * �����Ҳ�ؼ��ı���ͼ
	 * @param leftBackImage
	 */
	public void setRightBackImage(int rightBackImage) {
		if (rightBackImage != 0) {
			btnRight.setBackGroundImage(rightBackImage);
		}
	}

	/**
	 * �����Ҳ�ؼ�ѡ�еı���ͼ
	 * @param leftBackImageSeleted
	 */
	public void setRightBackImageSeleted(int rightBackImageSeleted) {
		if (rightBackImageSeleted != 0) {
			btnRight.setBackGroundImageSeleted(rightBackImageSeleted);
		}
	}

	/**
	 * �����Ҳ�ؼ���ʾ�������С
	 * @param leftTextSize
	 */
	public void setRightTextSize(float rightTextSize) {
		btnRight.setTextSize(rightTextSize);
	}
	
	/**
	 * �����Ҳ�ؼ���ʾ���ԣ�Ĭ��Ϊ����
	 * @param leftVisible
	 */
	public void setRightVisible(boolean rightVisible) {
		btnRight.setVisibility(rightVisible ? View.VISIBLE : View.GONE); 
	}
	
	//����һ��˽�з���dp2px
	private int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
}
