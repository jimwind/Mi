package com.up360.mi.character.bean;

import java.io.Serializable;
import java.util.ArrayList;


public class CharacterSingleBean implements Serializable {
    private String wordName;
    private String radicals;//部首
    private String structureName;//结构
    private String stroke;//笔画
    private String writingTips;//书写提示
    private String strokeData;//笔画数据 绘制用
    private String medianData;//中间值 绘制用
    private String medianParts;//
    private String pinyin;
    private String audio;//
    private String requiredName;//
    private String phrases;//组词
    private String sentencesClass;//课内造句
    private String sentencesExtra;//课外造句

    private String pinyinSpell;//
    private String learned;//是否已学 是否已学 0未学,1已学
    private String favFlag;//是否收藏标记 0未收藏,1 已收藏

    private int strokesNum;//笔画数
    private long bookId;
    private long unitId;
    private long lessonId;
    private long lessonWordId;//课文生字id
    private long wordId;//生字id
    private String content;


    private String handworkFlag;//是否手工标记: 0 否, 1 是

    private String strokeAudios;//笔画发音
//    private ArrayList<CharacterAudiosBean> strokeAudios;//笔画发音
    private ArrayList<CharacterExtraBean> extList;//生字下扩展
    private ArrayList<CharacterExtraBean> extLessonList;//课文下扩展

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public ArrayList<CharacterAudiosBean> getStrokeAudios() {
//        return strokeAudios;
//    }
//
//    public void setStrokeAudios(ArrayList<CharacterAudiosBean> strokeAudios) {
//        this.strokeAudios = strokeAudios;
//    }

    public String getStrokeAudios() {
        return strokeAudios;
    }

    public void setStrokeAudios(String strokeAudios) {
        this.strokeAudios = strokeAudios;
    }

    public ArrayList<CharacterExtraBean> getExtList() {
        return extList;
    }

    public void setExtList(ArrayList<CharacterExtraBean> extList) {
        this.extList = extList;
    }

    public ArrayList<CharacterExtraBean> getExtLessonList() {
        return extLessonList;
    }

    public void setExtLessonList(ArrayList<CharacterExtraBean> extLessonList) {
        this.extLessonList = extLessonList;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getRadicals() {
        return radicals;
    }

    public void setRadicals(String radicals) {
        this.radicals = radicals;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getWritingTips() {
        return writingTips;
    }

    public void setWritingTips(String writingTips) {
        this.writingTips = writingTips;
    }

    public String getStrokeData() {
        return strokeData;
    }

    public void setStrokeData(String strokeData) {
        this.strokeData = strokeData;
    }

    public String getMedianData() {
        return medianData;
    }

    public void setMedianData(String medianData) {
        this.medianData = medianData;
    }

    public String getMedianParts() {
        return medianParts;
    }

    public void setMedianParts(String medianParts) {
        this.medianParts = medianParts;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getRequiredName() {
        return requiredName;
    }

    public void setRequiredName(String requiredName) {
        this.requiredName = requiredName;
    }

    public String getPhrases() {
        return phrases;
    }

    public void setPhrases(String phrases) {
        this.phrases = phrases;
    }

    public String getSentencesClass() {
        return sentencesClass;
    }

    public void setSentencesClass(String sentencesClass) {
        this.sentencesClass = sentencesClass;
    }

    public String getSentencesExtra() {
        return sentencesExtra;
    }

    public void setSentencesExtra(String sentencesExtra) {
        this.sentencesExtra = sentencesExtra;
    }

    public String getPinyinSpell() {
        return pinyinSpell;
    }

    public void setPinyinSpell(String pinyinSpell) {
        this.pinyinSpell = pinyinSpell;
    }

    public String getLearned() {
        return learned;
    }

    public void setLearned(String learned) {
        this.learned = learned;
    }

    public String getFavFlag() {
        return favFlag;
    }

    public void setFavFlag(String favFlag) {
        this.favFlag = favFlag;
    }

    public int getStrokesNum() {
        return strokesNum;
    }

    public void setStrokesNum(int strokesNum) {
        this.strokesNum = strokesNum;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public long getLessonWordId() {
        return lessonWordId;
    }

    public void setLessonWordId(long lessonWordId) {
        this.lessonWordId = lessonWordId;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public String getHandworkFlag() {
        return handworkFlag;
    }

    public void setHandworkFlag(final String handworkFlag) {
        this.handworkFlag = handworkFlag;
    }

    public static class CharacterExtraBean implements Serializable {
        private String type;//扩展类型
        private String typeName;//扩展类型名
        private String content;//扩展内容
        private String image;//扩展图片
        private int imageWidth;//图片宽度
        private int imageHeight;//图片高度

        private String extendId;//

        public String getExtendId() {
            return extendId;
        }

        public void setExtendId(String extendId) {
            this.extendId = extendId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(int imageWidth) {
            this.imageWidth = imageWidth;
        }

        public int getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(int imageHeight) {
            this.imageHeight = imageHeight;
        }
    }

    public static class CharacterAudiosBean implements Serializable {
        private String name;//笔画
        private String audio;//笔画发音

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }
    }


}
