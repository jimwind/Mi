package com.up360.mi.character;

import android.os.Bundle;
import android.view.View;

import com.up360.mi.R;
import com.up360.mi.activity.BaseActivity;
import com.up360.mi.character.bean.CharacterSingleBean;
import com.up360.mi.character.bean.CharacterSingleDetailBean;
import com.up360.mi.character.view.DrawFontView;
import com.up360.mi.network.RequestMode;
import com.up360.mi.network.ResponseMode;

public class WriteActivity extends BaseActivity {
    private DrawFontView mDrawFontView;

    private RequestMode mRequestMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_write_activity);
        mDrawFontView = findViewById(R.id.characterwrite_drawfont);
        mRequestMode = new RequestMode(context, new ResponseMode() {
            @Override
            public void onGetCharacter(CharacterSingleDetailBean character) {
                CharacterSingleBean characterSingleBean = character.getWord();
                mDrawFontView.initData(characterSingleBean.getStrokeData(), characterSingleBean.getMedianData(), null, false, false, false,characterSingleBean.getHandworkFlag().equals("1"));
                mDrawFontView.setCanTouch(false);
            }
        });
        mRequestMode.getSingleCharacter(10458676, 317);
    }

    public void auto_play(View v){
        mDrawFontView.autoPlay();
    }
}
