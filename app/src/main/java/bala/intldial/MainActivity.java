package bala.intldial;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Main activity to handle settings for the application
 **/

public class MainActivity extends ActionBarActivity {

    private static String TAG = "IntlDial";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        buttonSave();
                    }
                });

        // set values from preferences
        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            ((EditText) findViewById(R.id.textMatch)).setText(
                    preferences.getString(IntlDial.settingMatch, ""));
            ((EditText) findViewById(R.id.textSubstitute)).setText(
                    preferences.getString(IntlDial.settingSubstitute, ""));
        }
        catch (Exception ex){
            IntlDial.ShowMessage(ex.getMessage(), this);
        }
    }

    private void buttonSave(){
        try {

            String match = ((EditText) findViewById(R.id.textMatch)).getText().toString();
            String substitute = ((EditText) findViewById(R.id.textSubstitute)).getText().toString();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(IntlDial.settingMatch, match);
            editor.putString(IntlDial.settingSubstitute, substitute);
            editor.commit();
        }
        catch (Exception ex){
            IntlDial.ShowMessage(ex.getMessage(), this);
        }
    }
}
