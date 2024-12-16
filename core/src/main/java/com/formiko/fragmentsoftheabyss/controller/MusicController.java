package com.formiko.fragmentsoftheabyss.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController {
    private static Music music;
    public static void playMusic(Music music, boolean loop){
        if(MusicController.music != null){
            MusicController.music.stop();
        }
        MusicController.music = music;
        MusicController.music.setLooping(loop);
        MusicController.music.play();
    }
    public static void playMainMusic(){
        MusicController.playMusic(Gdx.audio.newMusic(Gdx.files.internal("musics/main.mp3")),true);
    }

    public static void playSoundEffect(EffectType effectType){
        MusicController.playMusic(Gdx.audio.newMusic(Gdx.files.internal(effectType.getFilePath())),true);
        System.out.println("play sound effect");
    }
}
