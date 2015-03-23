/****************************************************************************
 Copyright (c) 2012      Wenbin Wang
 
 http://geeksavetheworld.com
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 ****************************************************************************/

package com.wonderwombat.ChartboostX;

import java.lang.ref.WeakReference;
import org.cocos2dx.lib.*;
import android.util.Log;
import android.view.View;
import com.chartboost.sdk.*;


public class ChartboostXBridge {
    private static final String TAG = "ChartboostX";
    private static WeakReference<Cocos2dxActivity> s_activity;

    private static boolean callFromJNI (String function, String location)
    {
        return (1 == Cocos2dxJavascriptJavaBridge.evalString("cc.chartboostx.dispatch(\""+function+"\", \""+location+"\")"));
    }

    // the method must be called in main thread, before any other method
    public static void initChartboostXBridge(Cocos2dxActivity activity, String appId, String appSign){
        ChartboostXBridge.s_activity = new WeakReference<Cocos2dxActivity>(activity);
        Chartboost.startWithAppId(ChartboostXBridge.s_activity.get(), appId, appSign);
        Chartboost.setDelegate(ChartboostXBridge.delegate);
    }
    
    public static void cacheInterstitial(final String location) {
        Log.v(TAG, "cacheInterstitial(\"" + location + "\") is called...");
        
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.cacheInterstitial(location);
            }
        });
    }

    public static boolean hasInterstitial(final String location) {
        Log.v(TAG, "hasInterstitial(\"" + location + "\") is called...");
        return Chartboost.hasInterstitial(location);
    }

    public static void showInterstitial(final String location) {
        Log.v(TAG, "showInterstitial(\"" + location + "\") is called...");

        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.showInterstitial(location);
            }
        });
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    public static void cacheMoreApps(final String location) {
        Log.v(TAG, "cacheMoreApps() is called...");
        
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.cacheMoreApps(location);
            }
        });
    }
    
    public static boolean hasMoreApps(final String location) {
        Log.v(TAG, "hasMoreApps(\"" + location + "\") is called...");
        return Chartboost.hasMoreApps(location);
    }

    public static void showMoreApps(final String location) {
        Log.v(TAG, "showMoreApps() is called...");
        
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.showMoreApps(location);
            }
        });
    }
    
/////////////////////////////////////////////////////////////////////////////
    
    public static void cacheRewardedVideo(final String location) {
        Log.v(TAG, "cacheRewardedVideo() is called...");
        
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.cacheRewardedVideo(location);
            }
        });
    }
    
    public static boolean hasRewardedVideo(final String location) {
        Log.v(TAG, "hasRewardedVideo(\"" + location + "\") is called...");
        return Chartboost.hasRewardedVideo(location);
    }

    public static void showRewardedVideo(final String location) {
        Log.v(TAG, "showRewardedVideo() is called...");
        
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.showRewardedVideo(location);
            }
        });
    }

/////////////////////////////////////////////////////////////////////////////

    public static void onCreate(final boolean impressionsUseActivities) {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.setImpressionsUseActivities(impressionsUseActivities);
                Chartboost.onCreate(ChartboostXBridge.s_activity.get());
            }
        });
    }

    public static void onStart() {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.onStart(ChartboostXBridge.s_activity.get());
            }
        });
    }

    public static void onResume() {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.onResume(ChartboostXBridge.s_activity.get());
            }
        });
    }
    
    public static void onPause() {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.onPause(ChartboostXBridge.s_activity.get());
            }
        });
    }

    public static void onStop() {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.onStop(ChartboostXBridge.s_activity.get());
            }
        });
    }

    public static void onDestroy() {
        ChartboostXBridge.s_activity.get().runOnUiThread(new Runnable() {
            public void run() {
                Chartboost.onDestroy(ChartboostXBridge.s_activity.get());
            }
        });
    }

    public static boolean onBackPressed() {
        return Chartboost.onBackPressed();
    }

/////////////////////////////////////////////////////////////////////////////

    public static ChartboostDelegate delegate = new ChartboostDelegate() {

        @Override
        public boolean shouldRequestInterstitial(String location) {
            Log.i(TAG, "SHOULD REQUEST INTERSTITIAL '"+ (location != null ? location : "null"));   
            return ChartboostXBridge.callFromJNI ("shouldRequestInterstitial", location);
        }
    
        @Override
        public boolean shouldDisplayInterstitial(String location) {
            Log.i(TAG, "SHOULD DISPLAY INTERSTITIAL '"+ (location != null ? location : "null"));
            return ChartboostXBridge.callFromJNI ("shouldDisplayInterstitial", location);
        }
    
        @Override
        public void didCacheInterstitial(String location) {
            Log.i(TAG, "DID CACHE INTERSTITIAL '"+ (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didCacheInterstitial", location);
        }
    
        // @Override
        // public void didFailToLoadInterstitial(String location, CBImpressionError error) {
        //     Log.i(TAG, "DID FAIL TO LOAD INTERSTITIAL '"+ (location != null ? location : "null")+ " Error: " + error.name());
        // }
    
        @Override
        public void didDismissInterstitial(String location) {
            Log.i(TAG, "DID DISMISS INTERSTITIAL: "+ (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didDismissInterstitial", location);
        }
    
        @Override
        public void didCloseInterstitial(String location) {
            Log.i(TAG, "DID CLOSE INTERSTITIAL: "+ (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didCloseInterstitial", location);
        }
    
        @Override
        public void didClickInterstitial(String location) {
            Log.i(TAG, "DID CLICK INTERSTITIAL: "+ (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didClickInterstitial", location);
        }
    
        @Override
        public void didDisplayInterstitial(String location) {
            Log.i(TAG, "DID DISPLAY INTERSTITIAL: " +  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didDisplayInterstitial", location);
        }
    
    /////////////////////////////////////////////////////////////////////////////

        @Override
        public boolean shouldRequestMoreApps(String location) {
            Log.i(TAG, "SHOULD REQUEST MORE APPS: " +  (location != null ? location : "null"));
            return ChartboostXBridge.callFromJNI ("shouldRequestMoreApps", location);
        }
    
        @Override
        public boolean shouldDisplayMoreApps(String location) {
            Log.i(TAG, "SHOULD DISPLAY MORE APPS: " +  (location != null ? location : "null"));
            return ChartboostXBridge.callFromJNI ("shouldDisplayMoreApps", location);
        }
    
        // @Override
        // public void didFailToLoadMoreApps(String location, CBImpressionError error) {
        //     Log.i(TAG, "DID FAIL TO LOAD MOREAPPS " +  (location != null ? location : "null")+ " Error: "+ error.name());
        // }
    
        @Override
        public void didCacheMoreApps(String location) {
            Log.i(TAG, "DID CACHE MORE APPS: " +  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didCacheMoreApps", location);
        }
    
        @Override
        public void didDismissMoreApps(String location) {
            Log.i(TAG, "DID DISMISS MORE APPS " +  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didDismissMoreApps", location);
        }
    
        @Override
        public void didCloseMoreApps(String location) {
            Log.i(TAG, "DID CLOSE MORE APPS: "+  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didCloseMoreApps", location);
        }
    
        @Override
        public void didClickMoreApps(String location) {
            Log.i(TAG, "DID CLICK MORE APPS: "+  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didClickMoreApps", location);
        }
    
        @Override
        public void didDisplayMoreApps(String location) {
            Log.i(TAG, "DID DISPLAY MORE APPS: " +  (location != null ? location : "null"));
            ChartboostXBridge.callFromJNI ("didDisplayMoreApps", location);
        }
    
        // @Override
        // public void didFailToRecordClick(String uri, CBClickError error) {
        //     Log.i(TAG, "DID FAILED TO RECORD CLICK " + (uri != null ? uri : "null") + ", error: " + error.name());
        // }
    
/////////////////////////////////////////////////////////////////////////////

        @Override
        public boolean shouldDisplayRewardedVideo(String location) {
            Log.i(TAG, String.format("SHOULD DISPLAY REWARDED VIDEO: '%s'",  (location != null ? location : "null")));
            return ChartboostXBridge.callFromJNI ("shouldDisplayRewardedVideo", location);
        }
    
        @Override
        public void didCacheRewardedVideo(String location) {
            Log.i(TAG, String.format("DID CACHE REWARDED VIDEO: '%s'",  (location != null ? location : "null")));
            ChartboostXBridge.callFromJNI ("didCacheRewardedVideo", location);
        }
    
        // @Override
        // public void didFailToLoadRewardedVideo(String location,
        //         CBImpressionError error) {
        //     Log.i(TAG, String.format("DID FAIL TO LOAD REWARDED VIDEO: '%s', Error:  %s",  (location != null ? location : "null"), error.name()));
        // }
    
        @Override
        public void didDismissRewardedVideo(String location) {
            Log.i(TAG, String.format("DID DISMISS REWARDED VIDEO '%s'",  (location != null ? location : "null")));
            ChartboostXBridge.callFromJNI ("didDismissRewardedVideo", location);
        }
    
        @Override
        public void didCloseRewardedVideo(String location) {
            Log.i(TAG, String.format("DID CLOSE REWARDED VIDEO '%s'",  (location != null ? location : "null")));
            ChartboostXBridge.callFromJNI ("didCloseRewardedVideo", location);
        }
    
        @Override
        public void didClickRewardedVideo(String location) {
            Log.i(TAG, String.format("DID CLICK REWARDED VIDEO '%s'",  (location != null ? location : "null")));
            ChartboostXBridge.callFromJNI ("didClickRewardedVideo", location);
        }
    
        @Override
        public void didCompleteRewardedVideo(String location, int reward) {
            Log.i(TAG, String.format("DID COMPLETE REWARDED VIDEO '%s' FOR REWARD %d",  (location != null ? location : "null"), reward));
            ChartboostXBridge.callFromJNI ("didCompleteRewardedVideo", location);
        }
        
        @Override
        public void didDisplayRewardedVideo(String location) {
            Log.i(TAG, String.format("DID DISPLAY REWARDED VIDEO '%s' FOR REWARD", location));
            ChartboostXBridge.callFromJNI ("didDisplayRewardedVideo", location);
        }
        
        @Override
        public void willDisplayVideo(String location) {
            Log.i(TAG, String.format("WILL DISPLAY VIDEO '%s", location));
            ChartboostXBridge.callFromJNI ("willDisplayVideo", location);
        }
        
    };

}
