package com.hz.tt.util.voice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.speech.tts.TextToSpeech;

import com.shoushuo.android.tts.ITts;
import com.shoushuo.android.tts.ITtsCallback;

public class SpeakShoushuoUtils {
	private Context mContext;
	private ITts ttsService;
	private boolean ttsBound;

	public SpeakShoushuoUtils(Context context) {
		this.mContext = context;
	}

	/**
	 * 定义Handler.
	 */
	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// Toast.makeText(mContext, " 我的话说完了 ", Toast.LENGTH_SHORT).show();s
		}
	};

	/**
	 * 回调参数.
	 */
	private final ITtsCallback ttsCallback = new ITtsCallback.Stub() {
		// 朗读完毕.
		@Override
		public void speakCompleted() throws RemoteException {
			handler.sendEmptyMessage(0);
		}
	};

	/**
	 * tts服务连接.
	 */
	private final ServiceConnection ttsConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			try {
				// 注册回调参数
				ttsService.unregisterCallback(ttsCallback);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ttsService = null;
			ttsBound = false;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			ttsService = ITts.Stub.asInterface(service);
			ttsBound = true;
			try {
				// tts服务初始化
				ttsService.initialize();
				// 撤销回调参数.
				ttsService.registerCallback(ttsCallback);
			} catch (RemoteException e) {
				ttsBound = false;
			}
		}
	};

	/**
	 * 语音播放
	 * 
	 * @param message
	 */
	public void speechMessage(String message) {
		try {
			ttsService.speak(message, TextToSpeech.QUEUE_FLUSH);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 语音注册
	 */
	public void registSpeech() {
		if (!ttsBound) {
			String actionName = "com.shoushuo.android.tts.intent.action.InvokeTts";
			Intent intent = new Intent(actionName);
			// 绑定tts服务
			mContext.bindService(intent, ttsConnection,
					Context.BIND_AUTO_CREATE);
		}
	}

	/**
	 * 语音注销
	 */
	public void unregistSpeech() {
		if (ttsBound) {
			ttsBound = false;
			// 撤销tts服务
			mContext.unbindService(ttsConnection);
		}
	}
}
