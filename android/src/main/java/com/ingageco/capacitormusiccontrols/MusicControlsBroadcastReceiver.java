package com.ingageco.capacitormusiccontrols;


import com.getcapacitor.JSObject;

import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.view.KeyEvent;

public class MusicControlsBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "CMCBroadRcvr";

	public MusicControlsBroadcastReceiver(){
	}

	public void stopListening(){

		JSObject ret = new JSObject();
		ret.put("message", "music-controls-stop-listening");
		CapacitorMusicControls.GetInstance().controlsNotification(ret);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String message = intent.getAction();
		JSObject ret = new JSObject();

		Log.i(TAG, "onReceive fired "  + message);



		if(message.equals(Intent.ACTION_HEADSET_PLUG)){
				// Headphone plug/unplug
				int state = intent.getIntExtra("state", -1);
				switch (state) {
					case 0:

						ret.put("message", "music-controls-headset-unplugged");

						CapacitorMusicControls.GetInstance().controlsNotification(ret);
						CapacitorMusicControls.GetInstance().unregisterMediaButtonEvent();
						break;
					case 1:

						ret.put("message", "music-controls-headset-plugged");

						CapacitorMusicControls.GetInstance().registerMediaButtonEvent();
						break;
					default:
						break;
				}
			} else if (message.equals("music-controls-media-button")){
				// Media button
				KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
				if (event.getAction() == KeyEvent.ACTION_DOWN) {

					int keyCode = event.getKeyCode();
					switch (keyCode) {
						case KeyEvent.KEYCODE_MEDIA_NEXT:
							ret.put("message", "music-controls-next");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_PAUSE:
							ret.put("message", "music-controls-pause");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_PLAY:
							ret.put("message", "music-controls-play");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
							ret.put("message", "music-controls-toggle-play-pause");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
							ret.put("message", "music-controls-previous");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_STOP:
							ret.put("message", "music-controls-stop");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_FAST_FORWARD:
							ret.put("message", "music-controls-fast-forward");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_REWIND:
							ret.put("message", "music-controls-rewind");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_SKIP_BACKWARD:
							ret.put("message", "music-controls-skip-backward");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_SKIP_FORWARD:
							ret.put("message", "music-controls-skip-forward");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_STEP_BACKWARD:
							ret.put("message", "music-controls-step-backward");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MEDIA_STEP_FORWARD:
							ret.put("message", "music-controls-step-forward");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_META_LEFT:
							ret.put("message", "music-controls-meta-left");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_META_RIGHT:
							ret.put("message", "music-controls-meta-right");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_MUSIC:
							ret.put("message", "music-controls-music");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_VOLUME_UP:
							ret.put("message", "music-controls-volume-up");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_VOLUME_DOWN:
							ret.put("message", "music-controls-volume-down");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_VOLUME_MUTE:
							ret.put("message", "music-controls-volume-mute");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						case KeyEvent.KEYCODE_HEADSETHOOK:
							ret.put("message", "music-controls-headset-hook");
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
						default:
							ret.put("message", message);
							CapacitorMusicControls.GetInstance().controlsNotification(ret);
							break;
					}
				}
			} else if (message.equals("music-controls-destroy")){
				// Close Button
				ret.put("message", "music-controls-destroy");
				CapacitorMusicControls.GetInstance().controlsNotification(ret);
				CapacitorMusicControls.GetInstance().destroyPlayerNotification();
			} else {
				ret.put("message", message);
				CapacitorMusicControls.GetInstance().controlsNotification(ret);
			}


	}
}
