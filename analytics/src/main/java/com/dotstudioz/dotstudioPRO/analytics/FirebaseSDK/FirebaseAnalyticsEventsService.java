package com.dotstudioz.dotstudioPRO.analytics.FirebaseSDK;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticsEventsService {

    private Context mContext;

    private FirebaseAnalytics mFirebaseAnalytics;

    public static final String DEVICE_TYPE = "DeviceType";
    public static final String DEVICE_TYPE_ANDROID_TV = "androidtv";
    public static final String DEVICE_TYPE_MOBILE = "mobile";
    public static final String FIRE_TV_PARAM_NAME = "FIRE_TV";
    public static final String FIRE_TV_PARAM_VALUE = "FIRE_TV";
    public static final String ANDROID_TV_PARAM_NAME = "ANDROID_TV";
    public static final String ANDROID_TV_PARAM_VALUE = "ANDROID_TV";
    public static final String ANDROID_PARAM_NAME = "ANDROID";
    public static final String ANDROID_PARAM_VALUE = "ANDROID";

    private FirebaseAnalyticsEventsService() { }

    private static FirebaseAnalyticsEventsService mInstance = new FirebaseAnalyticsEventsService();
    public static synchronized FirebaseAnalyticsEventsService getInstance() {
        if(mInstance == null)
            mInstance = new FirebaseAnalyticsEventsService();
        return mInstance;
    }

    public void initialize(Context context) {
        if(mContext == null)
            mContext = context;
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    synchronized public void sendFirebaseAnalyticsEvent(String eventCategory, String eventType, String videoID) {
        try {
            Bundle bundle = new Bundle();
            if (eventCategory != null && eventCategory.length() > 0) {
                bundle.putString("event_category", eventCategory);
            }
            if (videoID != null && videoID.length() > 0) {
                bundle.putString("event_label", videoID);
            }
            mFirebaseAnalytics.logEvent(eventType, bundle);
        } catch(Exception e) {
            Log.d("FirebaseAnalytics", "sendFirebaseAnalyticsEvent 1");
            e.printStackTrace();
        }
    }

    synchronized public void sendFirebaseAnalyticsEvent(String eventType, String categorySlug, String channelSlug, String videoID) {
        try {
            Bundle bundle = new Bundle();
            if (categorySlug != null && categorySlug.length() > 0) {
                bundle.putString("category-slug", categorySlug);
            }
            if (channelSlug != null && channelSlug.length() > 0) {
                bundle.putString("channel-slug", channelSlug);
            }
            if (videoID != null && videoID.length() > 0) {
                bundle.putString("video-id", videoID);
            }
            Log.d("FirebaseAnalytics", eventType+", "+categorySlug+", "+channelSlug+", "+videoID+", sendFirebaseAnalyticsEvent: ");
            mFirebaseAnalytics.logEvent(eventType, bundle);
        } catch(Exception e) {
            Log.d("FirebaseAnalytics", "sendFirebaseAnalyticsEvent 2");
            e.printStackTrace();
        }
    }

    synchronized public void sendFirebaseAnalyticsEvent(String eventCategory, String eventType, String channelSlug, String videoID, String customParameterName, String customParameterValue) {
        try {
            Bundle bundle = new Bundle();
            if (eventCategory != null && eventCategory.length() > 0) {
                bundle.putString("event_category", eventCategory);
            }
            if (channelSlug != null && channelSlug.length() > 0) {
                bundle.putString("channel-slug", channelSlug);
            }
            if (videoID != null && videoID.length() > 0) {
                bundle.putString("video-id", videoID);
            }
            if (customParameterName != null && customParameterName.length() > 0 &&
                    customParameterValue != null && customParameterValue.length() > 0) {
                bundle.putString(customParameterName, customParameterValue);
            }
            mFirebaseAnalytics.logEvent(eventType, bundle);
        } catch(Exception e) {
            Log.d("FirebaseAnalytics", "sendFirebaseAnalyticsEvent 2");
            e.printStackTrace();
        }
    }

    synchronized public void sendFirebaseAnalyticsUserProperty(String customParameterName, String customParameterValue) {
        try {
            if (customParameterName != null && customParameterName.length() > 0 &&
                    customParameterValue != null && customParameterValue.length() > 0) {
                mFirebaseAnalytics.setUserProperty(customParameterName, customParameterValue);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
