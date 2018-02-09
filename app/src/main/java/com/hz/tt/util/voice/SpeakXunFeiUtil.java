package com.hz.tt.util.voice;

import android.content.Context;
import android.media.SoundPool;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.iflytek.speech.ErrorCode;
import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechSynthesizer;
import com.iflytek.speech.SpeechUtility;
import com.iflytek.speech.SynthesizerListener;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 讯飞离线TTS
 */
@SuppressWarnings("unused")
public class SpeakXunFeiUtil {
	private Context context;
	private SpeechSynthesizer speechSynthesizer;
	public static int SPEAK_TYPE_ADD = 0;// 可以循环播放
	public static int SPEAK_TYPE_FLUSH = 1;// 单条播放
	private Queue<String> queue;// 播放队列
	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap;

	public SpeakXunFeiUtil(Context context) {
		this.context = context;
		initXunfei(context);
		// 基于LinkedBlockingQueue队列
		queue = new LinkedBlockingQueue<String>();
	}

	private void initXunfei(Context context) {
		// 检测是否安装讯飞语音服务
		if (SpeechUtility.getUtility(context).queryAvailableEngines() == null
				|| SpeechUtility.getUtility(context).queryAvailableEngines().length <= 0) {
//			Toast.makeText(context, "请安装讯飞语音服务", Toast.LENGTH_SHORT).show();
		}
		// 设置你申请的应用appid
		// SpeechUtility.getUtility(context).setAppid("4d6774d0");
		SpeechUtility.getUtility(context).setAppid("");
		// 初始化合成对象 可以设置null
		speechSynthesizer = new SpeechSynthesizer(context, initListener);
		// 设置引擎类型
		speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE, "local");
		// 设置发音人
		speechSynthesizer.setParameter(SpeechSynthesizer.VOICE_NAME, "xiaoyan");
		// speechSynthesizer.setParameter(SpeechSynthesizer.VOICE_NAME,
		// "jiajia");
		// 设置语速
		speechSynthesizer.setParameter(SpeechSynthesizer.SPEED, "40");
		// 设置音调
		speechSynthesizer.setParameter(SpeechSynthesizer.PITCH, "50");
	}

	protected void Speak(String content, int SPEAK_TYPE) {
		if (TextUtils.isEmpty(content))
			return;

		if (SPEAK_TYPE_ADD == SPEAK_TYPE) {// 循环播放
			queue.offer(content);// 加入播放队列
			if (speechSynthesizer != null && !speechSynthesizer.isSpeaking()) {
				// 开始合成
				speechSynthesizer.startSpeaking(queue.peek(),
						synthesizerListener);
			}
		} else {// 直接播放
			queue.clear();// 清空队列
			speechSynthesizer.startSpeaking(content, synthesizerListener);
		}
	}

	private SynthesizerListener synthesizerListener = new SynthesizerListener.Stub() {
		@Override
		public void onBufferProgress(int progress) throws RemoteException {
			// 缓冲进度回调
			Log.e("state", "onBufferProgress");
			// soundPool.play(soundPoolMap.get(1), 1.0f, 1.0f, 1, 0, 1f);
		}

		@Override
		public void onCompleted(int code) throws RemoteException {
			// 结束回调
			Log.e("state", "onCompleted");
			queue.poll();
			if (queue != null && queue.size() > 0)
				speechSynthesizer.startSpeaking(queue.peek(),
						synthesizerListener);
		}

		@Override
		public void onSpeakBegin() throws RemoteException {
			// 开始播放回调
			Log.e("state", "onSpeakBegin");
		}

		@Override
		public void onSpeakPaused() throws RemoteException {
			// 暂停回调
			Log.e("state", "onSpeakPaused");
		}

		@Override
		public void onSpeakProgress(int progress) throws RemoteException {
			// 播放进度回调
			Log.e("state", "onSpeakProgress");
		}

		@Override
		public void onSpeakResumed() throws RemoteException {
			// 重新播放回调
			Log.e("state", "onSpeakResumed");
		}
	};

	/**
	 * 初期化监听
	 */
	private InitListener initListener = new InitListener() {

		@Override
		public void onInit(ISpeechModule arg0, int code) {
			if (code == ErrorCode.SUCCESS) {
				Log.i("讯飞初始化", "初始化成功");
			}
		}
	};

	public void onDestory() {
		if (speechSynthesizer != null) {
			speechSynthesizer.destory();
		}

		if (queue != null) {
			queue.clear();
		}
	}
}
