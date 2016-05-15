package com.landptf.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/** 
* @ClassName: LoginInfoM 
* @Description: 登陆\注册用户名密码输入框
* @author landptf
* @date 2016-4-24 上午10:26:59  
*/ 
public class LoginInfoM extends RelativeLayout {
	private static final int IV_ICON_ID = 0x0001;
	private static final int ET_INPUT_ID = 0x0002;
	private static final int IV_DEL_ICON_ID = 0x0003;
	
	private Context context;
	private ImageView ivIcon;
	private EditText etInput;
	private ImageView ivDelIcon;
	public LoginInfoM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		initView();
	}

	public LoginInfoM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoginInfoM(Context context) {
		this(context, null);
	}
	
	/**
	 * 设置左侧的图标
	 * @param drawable
	 */
	public void setIconDrawable(Drawable drawable){
		ivIcon.setImageDrawable(drawable);
	}
	/**
	 * 设置左侧的图标
	 * @param resid
	 */
	public void setIconDrawable(int resid){
		ivIcon.setImageResource(resid);
	}
	/**
	 * 设置左侧的图标
	 * @param bitmap
	 */
	public void setIconDrawable(Bitmap bitmap){
		ivIcon.setImageBitmap(bitmap);
	}
	
	/**
	 * 设置左侧图标是否可见
	 * @param visible
	 */
	public void setIconVisible(int visible){
		ivIcon.setVisibility(visible);
	}
	
	/**
	 * 设置输入框提示信息
	 * @param text
	 */
	public void setHint(String text){
		etInput.setHint(text);
	}
	
	/**
	 * 允许输入的最大字符长度
	 * @param length
	 */
	public void setInputLength(int length){
		InputFilter[] filters = new InputFilter[]{new InputFilter.LengthFilter(length)}; 
		etInput.setFilters(filters);
	}
	
	/**
	 * 获得输入的内容
	 * @return
	 */
	public String getText(){
		return etInput.getText().toString();
	}
	
	/**
	 * 设置右侧的删除图标
	 * @param drawable
	 */
	public void setDelIconDrawable(Drawable drawable){
		ivDelIcon.setImageDrawable(drawable);
	}
	/**
	 * 设置右侧的删除图标
	 * @param resid
	 */
	public void setDelIconDrawable(int resid){
		ivDelIcon.setImageResource(resid);
	}
	/**
	 * 设置右侧的删除图标
	 * @param bitmap
	 */
	public void setDelIconDrawable(Bitmap bitmap){
		ivDelIcon.setImageBitmap(bitmap);
	}
	
	/**
	 * 设置输入的样式
	 * @param inputType InputType.*
	 */
	public void setInputType(int inputType){
		etInput.setInputType(inputType);
	}

	private void initView() {
		ivIcon = new ImageView(context);
		ivIcon.setId(IV_ICON_ID);
		RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.leftMargin = dp2px(context, 10);
		ivIcon.setLayoutParams(lp);
		
		ivDelIcon = new ImageView(context);
		ivDelIcon.setId(IV_DEL_ICON_ID);
		lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.rightMargin = dp2px(context, 10);
		ivDelIcon.setLayoutParams(lp);
		ivDelIcon.setVisibility(View.GONE);
		ivDelIcon.setOnClickListener(delClick);
		
		etInput = new EditText(context);
		etInput.setId(ET_INPUT_ID);
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.addRule(ivIcon.getId(), RelativeLayout.RIGHT_OF);
		lp.addRule(ivDelIcon.getId(), RelativeLayout.LEFT_OF);
		lp.leftMargin = dp2px(context, 5);
		lp.rightMargin = dp2px(context, 5);
		etInput.setLayoutParams(lp);
		etInput.setBackgroundDrawable(null);
		etInput.setTextAppearance(context, android.R.attr.textViewStyle);
		etInput.setTextColor(Color.parseColor("#ffffff"));
		etInput.setHintTextColor(Color.parseColor("#878787"));
		etInput.addTextChangedListener(textChanged);
		
		
		addView(ivIcon);
		addView(etInput);
		addView(ivDelIcon);
	}

	private OnClickListener delClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			etInput.setText("");
			ivDelIcon.setVisibility(View.GONE);
		}
	};
	
	private TextWatcher textChanged = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		@Override
		public void afterTextChanged(Editable s) {
			if (s.toString().length() > 0) {
				ivDelIcon.setVisibility(View.VISIBLE);
			}else {
				ivDelIcon.setVisibility(View.GONE);
			}
		}
	};
	
	private int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}

}
