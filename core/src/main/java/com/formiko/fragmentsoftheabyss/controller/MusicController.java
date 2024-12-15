package com.formiko.fragmentsoftheabyss.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicController {
    private static Music music;
    public static void playMusic(Music music){
        if(MusicController.music != null){
            MusicController.music.stop();
        }
        MusicController.music = music;
        MusicController.music.setLooping(true);
        // MusicController.music.play();
    }
    public static void playMainMusic(){
        MusicController.playMusic(Gdx.audio.newMusic(Gdx.files.internal("musics/main.mp3")));
    }
}
