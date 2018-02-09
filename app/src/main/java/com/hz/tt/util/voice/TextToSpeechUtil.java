package com.hz.tt.util.voice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

/**
 * 播放语音处理类
 */
@SuppressLint("UseSparseArrays")
public class TextToSpeechUtil {
	public Context context;
	public TextToSpeech textToSpeech;
	public boolean flag = true;// 语音是否可用
	public SoundPool soundPool;
	public HashMap<Integer, Integer> soundPoolMap;
	private SpeakShoushuoUtils shoushuoSpeechUtils;// 手说TTS
	private SpeakXunFeiUtil speakXunFeiUtil;// 讯飞TTS

	public TextToSpeechUtil(Context context) {
		// initSoundPool(context);
		// initTextToSpeech(context);
		// initShoushuoSpeech(context);
		initXunFeiSpeech(context);
	}

	/**
	 * =======================以下:TTS语音部分=======================
	 */
	public boolean check() {
		if (textToSpeech != null && flag)
			return true;
		return false;
	}

	public void speak(String content) {
		if (check())
			textToSpeech.speak(content, TextToSpeech.QUEUE_ADD, null);
	}

	public void onDestroy() {
		if (textToSpeech != null) {
			textToSpeech.stop();
			textToSpeech.shutdown();
			textToSpeech = null;
		}

		if (soundPool != null) {
			soundPool.release();
			soundPool = null;
		}

		if (shoushuoSpeechUtils != null) {
			shoushuoSpeechUtils.unregistSpeech();
			shoushuoSpeechUtils = null;
		}

		if (speakXunFeiUtil != null) {
			speakXunFeiUtil.onDestory();
			speakXunFeiUtil = null;
		}
	}

	/** 设置语音界面 */
	public void setting() {
		context.startActivity(new Intent("com.android.settings.TTS_SETTINGS"));
	}

	public void initTextToSpeech(Context context) {
		textToSpeech = new TextToSpeech(context, new OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					int result = textToSpeech.setLanguage(Locale.ENGLISH);
					if (result == TextToSpeech.LANG_MISSING_DATA
							|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
						flag = false;
						Log.e("语音", "初始化语音失败");
					} else {
						Log.e("语音", "初始化语音成功");
					}
				}
			}
		});
	}

	/**
	 * =======================以下:讯飞TTS=======================
	 */
	public void initXunFeiSpeech(Context context) {
		speakXunFeiUtil = new SpeakXunFeiUtil(context);
	}

	/**
	 * 讯飞 播放语音
	 * 
	 * @param message
	 */
	public void speakXunFei(String message) {
		if (speakXunFeiUtil != null && !TextUtils.isEmpty(message)) {
			speakXunFeiUtil.Speak(message, SpeakXunFeiUtil.SPEAK_TYPE_FLUSH);
		}
	}

	/**
	 * =======================以下:手说TTS=======================
	 */
	public void initShoushuoSpeech(Context context) {
		shoushuoSpeechUtils = new SpeakShoushuoUtils(context);
		shoushuoSpeechUtils.registSpeech();
	}

	/**
	 * 手说播放语音
	 * 
	 * @param message
	 */
	public void speakShouShuo(String message) {
		if (shoushuoSpeechUtils != null && !TextUtils.isEmpty(message)) {
			shoushuoSpeechUtils.speechMessage(message);
		}
	}

	/**
	 * =======================以下:播放声音部分=======================
	 */
	public void initSoundPool(Context context) {
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		// soundPoolMap.put(1, soundPool.load(context, R.raw.ok, 1));// 正确
		// soundPoolMap.put(2, soundPool.load(context, R.raw.error, 2));// 错误
	}

	/**
	 * 正确
	 */
	public void soundPoolOk() {
		if (soundPool != null)
			soundPool.play(soundPoolMap.get(1), 1.0f, 1.0f, 1, 0, 1f);
	}

	/**
	 * 错误
	 */
	public void soundPoolError() {
		if (soundPool != null)
			soundPool.play(soundPoolMap.get(2), 1.0f, 1.0f, 1, 0, 1f);
	}
}
