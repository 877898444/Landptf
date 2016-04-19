package com.landptf.controls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** 
* @ClassName: MessageItemM 
* @Description: the messages or notes item
* @author landptf
* @date 2015-8-25 下午11:33:24  
*/ 
public class MessageItemM extends RelativeLayout {
	private static final int VIEW_IVICON_ID = 0x0001;
	private static final int VIEW_TVTITLE_ID = 0x0002;
	private static final int VIEW_TVCONTENT_ID = 0x0003;
	private static final int VIEW_BTMTIP_ID = 0x0004;
	private static final int VIEW_IVEXPAND_ID = 0x0005;
	
	private Context context;
	private OnClickListener onClickListener = null;
	
	/*view control*/
	private ImageView ivIcon;
	private TextView tvTitle;
	private TextView tvContent;
	private ButtonM btmTip;
	private ImageView ivExpand;
	
	public interface OnClickListener{
		public void onClick(View v);
	}
	
	public void setOnClickListener(OnClickListener onClickListener){
		this.onClickListener = onClickListener;
	}

	public MessageItemM(Context context) {
		this(context, null);
	}
	
	public MessageItemM(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MessageItemM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		setBackgroundColor(android.graphics.Color.WHITE);
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				//setColor(event.getAction());
				return false;
			}
		});
		setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickListener != null) {
					onClickListener.onClick(v);
				}
			}
		});
		initView();
	}
	
	private void initView() {
		ivIcon = new ImageView(context);
		ivIcon.setId(VIEW_IVICON_ID);
		RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.setMargins(10, 5, 10, 5);
		ivIcon.setLayoutParams(lp);
		
		tvTitle = new TextView(context);
		tvTitle.setId(VIEW_TVTITLE_ID);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
		lp.setMargins(0, 5, 0, 5);
		tvTitle.setTextSize(16);
		tvTitle.setTextColor(Color.parseColor("#696969"));
		tvTitle.setLayoutParams(lp);
		
		ivExpand = new ImageView(context);
		ivExpand.setId(VIEW_IVEXPAND_ID);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.rightMargin = 10;
		ivExpand.setLayoutParams(lp);
		
		btmTip = new ButtonM(context);
		btmTip.setId(VIEW_BTMTIP_ID);
		btmTip.setShape(GradientDrawable.OVAL);
		btmTip.setFillet(true);
		btmTip.setTextColori(android.graphics.Color.WHITE);
		btmTip.setTextSize(16);
		btmTip.setRadius(15);
		/*back color is red*/
		btmTip.setBackColor(Color.parseColor("#ff0000"));
		lp=new RelativeLayout.LayoutParams(dp2px(context,32),dp2px(context,32));
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.LEFT_OF, ivExpand.getId());
		lp.rightMargin = 5;
		btmTip.setLayoutParams(lp);
		
		tvContent = new TextView(context);
		tvContent.setId(VIEW_TVCONTENT_ID);
		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.LEFT_OF, btmTip.getId());
		lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
		lp.addRule(RelativeLayout.BELOW, tvTitle.getId());
		lp.rightMargin = 10;
		lp.bottomMargin = 5;
		tvContent.setTextSize(18);
		tvContent.setTextColor(getResources().getColor(android.R.color.black));
		tvContent.setLayoutParams(lp);
		/* When the length of the text is more than one line, then the end of"..." */
		tvContent.setSingleLine();
		tvContent.setEllipsize(TruncateAt.END);
		
		addView(ivIcon);
		addView(tvTitle);
		addView(tvContent);
		addView(btmTip);
		addView(ivExpand);
	}
	
	/**
	 * set icon vith drawable
	 * @param drawable
	 */
	public void setImageDrawableIcon(Drawable drawable){
		ivIcon.setImageDrawable(drawable);
	}
	
	/**
	 * set icon with resId
	 * @param resId
	 */
	public void setImageResourceIcon(int resId){
		ivIcon.setImageResource(resId);
	}
	
	/**
	 * set icon with bitmap
	 * @param bitmap
	 */
	public void setImageBitmapIcon(Bitmap bitmap){
		ivIcon.setImageBitmap(bitmap);
	}
	
	/**
	 * set the title show state that change the tvContent position 
	 * @param visible
	 */
	public void setVisibleContent(Boolean visible){
		RelativeLayout.LayoutParams lp;
		if (visible) {
			tvContent.setVisibility(View.VISIBLE);
			lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
			lp.setMargins(0, 5, 0, 5);
			tvTitle.setLayoutParams(lp);
		}else {
			tvContent.setVisibility(View.GONE);
			lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.LEFT_OF, btmTip.getId());
			lp.addRule(RelativeLayout.RIGHT_OF, ivIcon.getId());
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			tvTitle.setLayoutParams(lp);
		}
	} 
	
	/**
	 * set text to the title 
	 * @param title
	 */
	public void setTextTitle(String title){
		tvTitle.setText(title);
	}
	
	/**
	 * set text to the content
	 * @param content
	 */
	public void setTextContent(String content){
		tvContent.setText(content);
	}
	
	/**
	 * set text to the Tip
	 * @param tip
	 */
	public void setTextTip(String tip){
		btmTip.setText(tip);
	}
	
	/**
	 * set Tip backgroundResource with resId
	 * @param resId
	 */
	public void setImageResourceTip(int resId){
		btmTip.setBackGroundImage(resId);
	}
	
	/**
	 * set visible to the Tip
	 * @param visible
	 */
	public void setVisibleTip(Boolean visible){
		btmTip.setVisibility(visible ? View.VISIBLE : View.GONE);
	}
	
	/**
	 * set expand vith drawable
	 * @param drawable
	 */
	public void setImageDrawableExpand(Drawable drawable){
		ivExpand.setImageDrawable(drawable);
	}
	
	/**
	 * set expand with resId
	 * @param resId
	 */
	public void setImageResourceExpand(int resId){
		ivExpand.setImageResource(resId);
	}
	
	/**
	 * set expand with bitmap
	 * @param bitmap
	 */
	public void setImageBitmapExpand(Bitmap bitmap){
		ivExpand.setImageBitmap(bitmap);
	}
	
	/**
	 * set visible to the expand
	 * @param visible
	 */
	public void setVisibleExpand(Boolean visible){
		ivExpand.setVisibility(visible ? View.VISIBLE : View.GONE);
	}
	
	
	private int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
	

}
