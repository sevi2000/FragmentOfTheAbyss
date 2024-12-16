package com.formiko.fragmentsoftheabyss.controller;

public enum EffectType {
    MonsterAttack("musics/monster_attack.mp3");
    public final String filePath;
    private EffectType(String filePath){
        this.filePath = filePath;
    }
    String getFilePath() {
       return filePath;
    }
}
