package PongSounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/************************************************************************
 Made by        PatrickSys
 Date           17/02/2021
 Package        sample
 Description:
 ************************************************************************/
public class Sounds {


    public static void playSounds(File Sound) {

        try {

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
