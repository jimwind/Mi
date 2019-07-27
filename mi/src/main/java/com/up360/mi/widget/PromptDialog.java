package com.up360.mi.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.up360.mi.R;

/**
 * 本提示框默认支持 title & message 宽度定为270dp,高度wrap_content <br />
 * title margin_top=30dp textSize=15sp <br />
 * msg 距title 15dp textSize=20sp bold <br />
 * 按钮 距msg 15dp <br />
 * 左，下，右的padding都是15dp <br />
 * 详见dialog_prompt.xml <br />
 * 如果要自定义提示信息,请自行layout,并且调用setContentView,除按钮区,完全自定义  
 * 实例为dialog_prompt_content.xml
 * mi.gao@20161101
 */
public class PromptDialog extends Dialog {
	public static final int BUTTON_STYLE_GREEN = 1;
	public static final int BUTTON_STYLE_GRAY = 2;

    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_SHOW_CLOSE = 2;//bottom center
    public static final int TYPE_SHOW_CLOSE_TOP_RIGHT = 3;
    public static final int TYPE_SHOW_CLOSE_TOP_RIGHT_EX = 31;
    public static final int TYPE_DEFAULT_EX = 11;
	public PromptDialog(Context context) {
		super(context);
	}

	public PromptDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder{
		private Context context;
        private String title;
        private CharSequence message;
        private String positiveButtonText;
        private int positiveButtonStyle = BUTTON_STYLE_GRAY;
        private String negativeButtonText;
        private int negativeButtonStyle = BUTTON_STYLE_GREEN;
        private String neutralButtonText;
        private int neutralButtonStyle = BUTTON_STYLE_GREEN;
        private String bigButtonText;
        private int bigButtonStyle = BUTTON_STYLE_GREEN;
        private View contentView;
        private int mType = TYPE_DEFAULT;//样式详见对应的layout文件
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private DialogInterface.OnClickListener neutralButtonClickListener;
        private DialogInterface.OnClickListener bigButtonClickListener;

        private DialogInterface.OnKeyListener keyListener;//物理按键监听，现只用于监听返回，生字用

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public void setKeyListener(OnKeyListener keyListener) {
            this.keyListener = keyListener;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setShowType(int type){
            if(type == TYPE_DEFAULT
                    || type == TYPE_SHOW_CLOSE
                    || type == TYPE_SHOW_CLOSE_TOP_RIGHT
                    || type == TYPE_SHOW_CLOSE_TOP_RIGHT_EX
                    || type == TYPE_DEFAULT_EX){
                mType = type;
            }
            return this;
        }

        /**
         * 左侧Button
         * @param positiveButtonText
         * @param listener
         * @param buttonStyle
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener, int buttonStyle) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            this.positiveButtonStyle = buttonStyle;
            return this;
        }
        /**
         * 左侧Button
         * @param positiveButtonText
         * @param listener
         * @param buttonStyle
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener, int buttonStyle) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.positiveButtonStyle = buttonStyle;
            return this;
        }
        /**
         * 右侧Button
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener, int buttonStyle) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            this.negativeButtonStyle = buttonStyle;
            return this;
        }
        /**
         * 右侧Button
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener, int buttonStyle) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            this.negativeButtonStyle = buttonStyle;
            return this;
        }
        /**
         * 中间Button
         * @param neutralButtonText
         * @param listener
         * @return
         */
        public Builder setNeutralButton(String neutralButtonText,
                                        DialogInterface.OnClickListener listener, int buttonStyle) {
        	this.neutralButtonText = neutralButtonText;
        	this.neutralButtonClickListener = listener;
        	this.neutralButtonStyle = buttonStyle;
        	return this;
        }
        /**
         * 中间Button
         * @param neutralButtonText
         * @param listener
         * @param buttonStyle
         * @return
         */
        public Builder setNeutralButton(int neutralButtonText,
                                        DialogInterface.OnClickListener listener, int buttonStyle) {
        	this.neutralButtonText = (String) context.getText(neutralButtonText);
        	this.neutralButtonClickListener = listener;
        	this.neutralButtonStyle = buttonStyle;
        	return this;
        }

        public Builder setBigButton(String bigButtonText,
                                    DialogInterface.OnClickListener listener, int buttonStyle){
            this.bigButtonText = bigButtonText;
            this.bigButtonClickListener = listener;
            this.bigButtonStyle = buttonStyle;
            return this;
        }
        public Builder setBigButton(int bigButtonText,
                                    DialogInterface.OnClickListener listener, int buttonStyle){
            this.bigButtonText = (String) context.getText(bigButtonText);
            this.bigButtonClickListener = listener;
            this.bigButtonStyle = buttonStyle;
            return this;
        }

        View layout;
        public PromptDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final PromptDialog dialog = new PromptDialog(context,R.style.Dialog);
            int resource = R.layout.dialog_prompt;
            switch (mType){
                case TYPE_DEFAULT:{
                    resource = R.layout.dialog_prompt;
                    break;
                }
                case TYPE_SHOW_CLOSE:{
                    resource = R.layout.dialog_prompt_close;
                    break;
                }
                case TYPE_SHOW_CLOSE_TOP_RIGHT:{
                    resource = R.layout.dialog_prompt_close_top_right;
                    break;
                }
                case TYPE_SHOW_CLOSE_TOP_RIGHT_EX:{
                    resource = R.layout.dialog_prompt_close_top_right_ex;
                    break;
                }
                case TYPE_DEFAULT_EX:{
                    resource = R.layout.dialog_prompt_ex;
                    break;
                }
                default:{
                    resource = R.layout.dialog_prompt;
                    break;
                }
            }
            layout = inflater.inflate(resource, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            if(title != null && !"".equals(title)){
            	((TextView) layout.findViewById(R.id.title)).setVisibility(View.VISIBLE);
            	((TextView) layout.findViewById(R.id.title)).setText(title);
            } else {
            	((TextView) layout.findViewById(R.id.title)).setVisibility(View.GONE);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                setButtonStyle((TextView) layout.findViewById(R.id.positiveButton), positiveButtonStyle);
                if (positiveButtonClickListener != null) {
                    (layout.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                    	public void onClick(View v) {
                    		positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    	}
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                setButtonStyle((TextView) layout.findViewById(R.id.negativeButton), negativeButtonStyle);
                if (negativeButtonClickListener != null) {
                    (layout.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                    	public void onClick(View v) {
                    		negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    	}
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            if(neutralButtonText != null){
            	((TextView) layout.findViewById(R.id.neutralButton)).setText(neutralButtonText);
            	setButtonStyle((TextView) layout.findViewById(R.id.neutralButton), neutralButtonStyle);
            	if(neutralButtonClickListener != null){
            		(layout.findViewById(R.id.neutralButton)).setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							neutralButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
						}
					});
            	}
            } else {
        		layout.findViewById(R.id.neutralButton).setVisibility(View.GONE);
        	}

            if(!TextUtils.isEmpty(bigButtonText)){
                ((TextView)layout.findViewById(R.id.bigButton)).setText(bigButtonText);
                setButtonStyle((TextView) layout.findViewById(R.id.bigButton), bigButtonStyle);
                if(bigButtonClickListener != null){
                    (layout.findViewById(R.id.bigButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bigButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                layout.findViewById(R.id.bigButton).setVisibility(View.GONE);
            }

            if(layout.findViewById(R.id.close) != null) {
                layout.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }

            if(keyListener != null){
                dialog.setOnKeyListener(keyListener);
            }

            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);
            Window w = dialog.getWindow();
            android.view.WindowManager.LayoutParams lp = w.getAttributes();
            WindowManager m = ((Activity)context).getWindowManager();
            Display d = m.getDefaultDisplay();
    		float density = context.getResources().getDisplayMetrics().density;
//          w.setGravity(Gravity.BOTTOM);
            lp.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
            lp.width = (int) (density * 290f);
            w.setAttributes(lp);
            return dialog;  
        }  
        private void setButtonStyle(TextView btn, int style){
            //目前只有一个按钮的样式，不知道后续会怎么改 mi.gao 2019/4/17
            if(mType == TYPE_SHOW_CLOSE_TOP_RIGHT_EX){
                //do nothing
            } else if(mType == TYPE_DEFAULT_EX) {
                if (style == BUTTON_STYLE_GREEN) {
                    btn.setBackgroundResource(R.drawable.two_semi_circle_solid_47cf5b_r20);
                    btn.setTextColor(0xffffffff);
                }  else if (style == BUTTON_STYLE_GRAY) {
                    btn.setBackgroundResource(R.drawable.two_semi_circle_solid_ffffff_stroke_47cf5b_1_r20);
                    btn.setTextColor(0xff47cf5b);
                }
            } else {
                if (style == BUTTON_STYLE_GREEN) {
                    btn.setBackgroundResource(R.drawable.round_corner_solid_37bb52_r5);
                    btn.setTextColor(context.getResources().getColor(R.color.white));
                } else if (style == BUTTON_STYLE_GRAY) {
                    btn.setBackgroundResource(R.drawable.round_corner_solid_c6c6c6_r5);
                    btn.setTextColor(context.getResources().getColor(R.color.white));
                }
            }
        }

        public Builder setMessageHeight(int l ,int t ,int r ,int b) {
            layout.findViewById(R.id.message).setPadding(l , t , r , b);
            return this;
        }

        public Builder setMessageSize(int size) {
            ((TextView)layout.findViewById(R.id.message)).setTextSize(size);
            return this;
        }

        public Builder setMessageColor(String color){
            ((TextView)layout.findViewById(R.id.message)).setTextColor(Color.parseColor(color));
            return this;
        }

        public Builder setButtonSize(int size) {
            ((TextView) layout.findViewById(R.id.positiveButton)).setTextSize(size);
            ((TextView) layout.findViewById(R.id.negativeButton)).setTextSize(size);
            ((TextView) layout.findViewById(R.id.neutralButton)).setTextSize(size);
            return this;
        }

        public Builder setButtonHeight(int l ,int t ,int r ,int b) {
            ((TextView) layout.findViewById(R.id.positiveButton)).setPadding(l, t, r, b);
            ((TextView) layout.findViewById(R.id.negativeButton)).setPadding(l, t, r, b);
            ((TextView) layout.findViewById(R.id.neutralButton)).setPadding(l, t, r, b);
            return this;
        }

        public Builder setTitleColor(int c) {
            ((TextView)layout.findViewById(R.id.title)).setTextColor(c);
            return this;
        }
        public Builder setTitleSize(int size) {
            ((TextView)layout.findViewById(R.id.title)).setTextSize(size);
            return this;
        }
        public Builder setTitleHeight(int l ,int t ,int r ,int b) {
            ((TextView)layout.findViewById(R.id.title)).setPadding(l , t , r , b);
            return this;
        }
    }

    public void setBackKeyDisable(){
	    mBackKeyEnable = false;
    }
    private boolean mBackKeyEnable = true;

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            if(!mBackKeyEnable){
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }
}