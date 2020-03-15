package com.bridgern.IdWall;

import android.app.Activity;
import com.facebook.react.bridge.ActivityEventListener;
import android.content.Intent;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


import co.idwall.toolkit.IDwallToolkit;
import co.idwall.toolkit.flow.core.Doc;
import co.idwall.toolkit.flow.core.Flow;

import static android.app.Activity.RESULT_OK;

public class WallModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public WallModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(mActivityEventListener);
    }
    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            super.onActivityResult(activity, requestCode, resultCode, data);
            String token = "";
            //fluxo do SDK
            if(resultCode == RESULT_OK && requestCode == IDwallToolkit.IDWALL_REQUEST){
                if(data != null && data.getExtras() != null && data.getExtras().containsKey(IDwallToolkit.TOKEN)) {
                    token = data.getStringExtra(IDwallToolkit.TOKEN);
                    //envia evento ao react native
                    sendEvent(reactContext, "TOKEN", token);
                }
            }
        }

    };
    //metodo responsavel por enviar evendo ao React Native
    private void sendEvent(ReactContext reactContext, String eventName, @Nullable String token ) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, token);
    }
    @Override
    public String getName() {
        return "Wall";
    }
    @ReactMethod
    public void startSDK() {
        Activity currentActivity = getCurrentActivity();
        IDwallToolkit.getInstance().startFlow(currentActivity, Flow.COMPLETE, Doc.CHOOSE);
    }
}
