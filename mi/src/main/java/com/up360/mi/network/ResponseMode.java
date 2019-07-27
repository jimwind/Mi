package com.up360.mi.network;

import com.up360.mi.character.bean.CharacterSingleDetailBean;

public abstract class ResponseMode {
    public void onLogin(){}
    public void onGetCharacter(CharacterSingleDetailBean character){}
}
