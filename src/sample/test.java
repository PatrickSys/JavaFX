package sample;



import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.*;
/************************************************************************
 Made by        PatrickSys
 Date           17/02/2021
 Package        PACKAGE_NAME
 Description:
 ************************************************************************/
public class test {

    public static void main(String[] args){


        File Clap = new File("C:\\Users\\bitaz\\IdeaProjects\\JavaFX\\src\\PongSounds\\PaddleBounce.wav");
        playSound(Clap);


    }


    public static void playSound(File Sound){

        try{

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    }




