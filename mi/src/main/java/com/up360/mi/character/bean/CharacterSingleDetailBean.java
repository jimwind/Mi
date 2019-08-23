package com.up360.mi.character.bean;

import java.io.Serializable;

public class CharacterSingleDetailBean implements Serializable {
    private CharacterSingleBean word;
    private String domain;
    private String isVip;//是否VIP 0:不是VIP 1:是VIP（自主学习，搜索进详情用）
    private String serviceCode;//服务代码（固定值）chinese_word:生字自主学习
    private String priceDescription;//服务包月价格说明，例：每天仅需0.5元

    private CharacterRuleBean wordRules;//生字规则

    public CharacterSingleBean getWord() {
        return word;
    }

    public void setWord(CharacterSingleBean word) {
        this.word = word;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public CharacterRuleBean getWordRules() {
        return wordRules;
    }

    public void setWordRules(CharacterRuleBean wordRules) {
        this.wordRules = wordRules;
    }

    public static class CharacterRuleBean implements Serializable {
        private int strokeSpeed;//笔画自动描画速度（单位: 100像素/s，基于学生年级配置）
        private int difficultyLevel;//书写难易程度(1到10难度递减，基于学生年级配置)

        public int getStrokeSpeed() {
            return strokeSpeed;
        }

        public void setStrokeSpeed(int strokeSpeed) {
            this.strokeSpeed = strokeSpeed;
        }

        public int getDifficultyLevel() {
            return difficultyLevel;
        }

        public void setDifficultyLevel(int difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
        }
    }
}
