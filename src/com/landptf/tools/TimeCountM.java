package com.landptf.tools;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.landptf.controls.ButtonM;

public class TimeCountM extends CountDownTimer{
	private ButtonM btn;//按钮
	private int backColori = 0;
	private String backColors = "";
	/**
	 * 倒计时
	 * @param millisInFuture
	 * @param countDownInterval
	 * @param btn
	 */
	public TimeCountM(long millisInFuture, long countDownInterval,ButtonM btn) {
		super(millisInFuture, countDownInterval);
		this.btn =btn;
	}
	
	/**
	 * 设置倒计时结束后的背景色
	 * @param backColor
	 */
	public void setBackColor(int backColor){
		this.backColori = backColor;
	}
	
	/**
	 * 设置倒计时结束后的背景色
	 * @param backColor
	 */
	public void setBackColor(String backColor){
		this.backColors = backColor;
	}
	
	@Override
	public void onFinish() {
		btn.setText("重新获取验证码");
		btn.setEnabled(true);//重新获得点击
		if (backColori != 0) {
			btn.setBackgroundColor(backColori);//还原背景色
		}else if (!backColors.equals("")) {
			btn.setBackgroundColor(Color.parseColor(backColors));//还原背景色
		}else {
			btn.setBackgroundColor(Color.rgb(0, 156, 255));//还原背景色
		}
	}

	@Override
	public void onTick(long arg0) {
		btn.setEnabled(false);//设置不能点击
		btn.setText(arg0 / 1000 + "秒后可重新发送");//设置倒计时时间
		//设置按钮为灰色，这时是不能点击的
		btn.setBackgroundColor(android.graphics.Color.GRAY);
		Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
		span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
		btn.setText(span);
	}

}
