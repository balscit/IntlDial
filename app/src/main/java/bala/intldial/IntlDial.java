package bala.intldial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.widget.Toast;

/**
 *
 * Copyright 2015 Apache License
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Broadcast receiver to handle phone call
 **/

public class IntlDial extends BroadcastReceiver {

    public static final String settingMatch = "matchSetting";
    public static final String settingSubstitute = "substituteSetting";

    public IntlDial() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            // get old number
            final String oldNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            // get preferences
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String match = preferences.getString(settingMatch, null);
            String substitute = preferences.getString(settingSubstitute, null);

            // if the configured pattern matches
            if(match != null && substitute != null && oldNumber.matches(match))
            {
                String newNumber = oldNumber.replaceAll(match, substitute);
                this.setResultData(newNumber);

                ShowMessage("Dialing int'l number: " + newNumber, context);
            }
        }
        catch (Exception ex) {
            ShowMessage(ex.getMessage(), context);
        }
    }

    public static  void ShowMessage(String msg, Context context) {
        if(msg != null) {
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
