package com.slowpath.actionsheet;

import android.util.Log;
import android.support.v4.app.FragmentActivity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import com.baoyz.actionsheet.ActionSheet;


public class ActionSheetModule extends ReactContextBaseJavaModule implements ActionSheet.ActionSheetListener {

    private static final String MODULE_NAME = "ActionSheetAndroid";
    private FragmentActivity _activity;
    private Callback _callback;
    private ReactApplicationContext _context;

    public ActionSheetModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this._context = reactContext;
        this._activity = getCurrentActivity();
        this._callback = null;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void showActionSheetWithOptions(ReadableMap params, Callback callback) {

        this._callback = callback;
        int cancelButtonIndex = params.getInt("cancelButtonIndex");
        ReadableArray options = params.getArray("options");
        List<String> list = new ArrayList<>(options.size());
        String cancelButtonName = options.getString(cancelButtonIndex);

        for (int i = 0; i < options.size(); ++i) {
          String value = options.getString(i);
          if (!value.equals(cancelButtonName)) {
            list.add(value);
          }
        }

        String[] args = new String[list.size()];

        ActionSheet.createBuilder(_context, _activity.getSupportFragmentManager())
                  .setCancelButtonTitle(cancelButtonName)
                  .setOtherButtonTitles(list.toArray(args))
                  .setCancelableOnTouchOutside(false)
                  .setListener(this).show();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
      if (_callback != null) {
        _callback.invoke(index);
      }
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancle) {
      if (_callback != null && isCancle) {
        _callback.invoke(-1);
      }
    }
  }