package com.landptf.controls;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/** 
* @ClassName: BottomMuteM 
* @Description: 底部选项卡
* @author landptf
* @date 2016-5-7 上午11:30:03  
*/ 
public class BottomMuteM extends LinearLayout{
	
	private static final String TAG = "BottomMuteM";
	
	private List<MenuItemM> mimList = new ArrayList<MenuItemM>();
	private OnClickListenerM[] onClickListener;
    private int index = 0;
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
    public void setOnClickListener(int index, OnClickListenerM onClickListener){  
    	this.onClickListener[index] = onClickListener;  
    }
	
	public BottomMuteM(Context context) {
		this(context, null);
	}

	public BottomMuteM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public BottomMuteM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setOrientation(HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
	}
	
	public void setMimList(List<MenuItemM> mimList) {
		this.mimList = mimList;
		int i = 0;
		for (MenuItemM menuItemM : mimList) {
			LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			menuItemM.setLayoutParams(lp);
			addView(menuItemM);
			setItemClick(i);
			i++;
		}
		onClickListener = new OnClickListenerM[mimList.size()];
	}
	
	private void setItemClick(final int index){
		mimList.get(index).setOnClickListener(new com.landptf.controls.MenuItemM.OnClickListenerM() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "index = " + index);
				if (null != onClickListener[index]) {
					BottomMuteM.this.index = index;
					setSelectedState(index, true);
					onClickListener[index].onClick(v);
				}
			}
		});
	}
	
	/**
	 * 获得点击菜单的索引
	 * @return
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * 设置控件被选中状态
	 * @param selectedState
	 */
	public void setSelectedState(int index, boolean selectedState){
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setSelectedState(false);
		}
		mimList.get(index).setSelectedState(selectedState);
	}
	
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setBackground(int[] icon) {
		if (icon.length != mimList.size()) {
			Log.d(TAG, "icon size do not equals control count");
			return;
		}
		for (int i = 0; i < icon.length; i++) {
			mimList.get(i).setBackground(icon[i]);
		}
	}
	/**
	 * 设置图标
	 * @param icon
	 */
	public void setBackground(Bitmap[] icon) {
		if (icon.length != mimList.size()) {
			Log.d(TAG, "icon size do not equals control count");
			return;
		}
		for (int i = 0; i < icon.length; i++) {
			mimList.get(i).setBackground(icon[i]);
		}
	}
	/**
	 * 设置选中的图标
	 * @param icon
	 */
	public void setBackgroundSelected(int[] icon) {
		if (icon.length != mimList.size()) {
			Log.d(TAG, "icon size do not equals control count");
			return;
		}
		for (int i = 0; i < icon.length; i++) {
			mimList.get(i).setBackgroundSelected(icon[i]);
		}
	}
	/**
	 * 设置选中的图标
	 * @param icon
	 */
	public void setBackgroundSelected(Bitmap[] icon) {
		if (icon.length != mimList.size()) {
			Log.d(TAG, "icon size do not equals control count");
			return;
		}
		for (int i = 0; i < icon.length; i++) {
			mimList.get(i).setBackgroundSelected(icon[i]);
		}
	}
	
	/**
	 * 设置文字
	 * @param text
	 */
	public void setText(String[] text){
		if (text.length != mimList.size()) {
			Log.d(TAG, "text size do not equals control count");
			return;
		}
		for (int i = 0; i < text.length; i++) {
			mimList.get(i).setText(text[i]);
		}
	}
	
	/**
	 * 设置文字的颜色
	 * @param color
	 */
	public void setTextColor(int color){
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setTextColor(color);
		}
	}
	
	/**
	 * 设置文字的颜色
	 * @param color
	 */
	public void setTextColor(String color) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setTextColor(color);
		}
	}
	
	/**
	 * 设置选中文字的颜色
	 * @param color
	 */
	public void setTextColorSelected(int color){
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setTextColorSelected(color);
		}
	}
	
	/**
	 * 设置选中文字的颜色
	 * @param color
	 */
	public void setTextColorSelected(String color) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setTextColorSelected(color);
		}
	}
	
	/**
	 * 设置文字大小
	 * @param size
	 */
	public void setTextSize(float size){
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setTextSize(size);
		}
	}
	
	/**
	 * 设置NEW提示
	 * @param newIcon
	 */
	public void setNewImage(int newIcon) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setNewImage(newIcon);
		}
	}
	
	/**
	 * 设置NEW提示
	 * @param newIcon
	 */
	public void setNewImage(Bitmap newIcon) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setNewImage(newIcon);
		}
	}
	
	/**
	 * 设置NEW提示图标是否显示
	 * @param isVisible
	 */
	public void setNewVisible(int index, boolean isVisible){
		mimList.get(index).setNewVisible(isVisible);
	}
	
	/**
	 * 设置More提示
	 * @param moreIcon
	 */
	public void setMoreImage(int moreIcon) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setMoreImage(moreIcon);
		}
	}
	
	/**
	 * 设置More提示
	 * @param moreIcon
	 */
	public void setMoreImage(Bitmap moreIcon) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setMoreImage(moreIcon);
		}
	}
	
	/**
	 * 设置More提示图标是否显示
	 * @param isVisible
	 */
	public void setMoreVisible(int index, boolean isVisible){
		mimList.get(index).setMoreVisible(isVisible);
	}
	
	/**
	 * 设置Nums提示图标
	 * @param numsIcon
	 */
	public void setNumsImage(int numsIcon) {
		for (MenuItemM menuItemM : mimList) {
			menuItemM.setNumsImage(numsIcon);
		}
	}
	
	/**
	 * 设置未读数量
	 * @param count
	 */
	public void setNumsCount(int index, int count){
		mimList.get(index).setNumsCount(count);
	}
	
	/**
	 * 设置Nums提示图标是否显示
	 * @param isVisible
	 */
	public void setNumsVisible(int index, boolean isVisible){
		mimList.get(index).setNumsVisible(isVisible);
	}
}
