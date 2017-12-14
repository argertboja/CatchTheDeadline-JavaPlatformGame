package gameManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class SoundManager {
	
	/*private AudioStream as;
	private ContinuousAudioDataStream cas;
	private String path;
	
	public SoundManager( String File ) throws IOException {
		this.path = File;
		InputStream in = new FileInputStream( File );
		as = new AudioStream( in );
		AudioData ad = as.getData();
		cas = new ContinuousAudioDataStream( ad );
	}
	
	public void start() {
		AudioPlayer.player.start(cas);	
	}
	public void stop() {
		AudioPlayer.player.stop(cas);	
	}*/
	
	private Thread sound;
	private boolean play = true;
	
	public void start() {   
        sound = new Thread(){    	
            public void run(){

                AudioPlayer MGP=AudioPlayer.player;
                AudioStream BGM;
                AudioData MD;
                ContinuousAudioDataStream loop=null;

                while( play ){
                    try{ 
                        BGM=new AudioStream(new FileInputStream("D:\\CS_319\\CatchTheDeadline\\src\\images\\caketown.wav"));
                        AudioPlayer.player.start(BGM);
                        sleep(48000);// enter the elapse time of the music to avoid noise
                    }catch(Exception e){ }
                }
            }
        };
        sound.start();  
    }
	
	public void stop() {
		play = false;
	}
}

