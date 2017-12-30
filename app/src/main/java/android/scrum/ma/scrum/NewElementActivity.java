package android.scrum.ma.scrum;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NewElementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_element);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonPrevious = (Button) findViewById(R.id.button_ok);
        final EditText editText   = (EditText)findViewById(R.id.edit_text);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Set<String> elements = preferences.getStringSet("savedData", null);
        if (elements != null) {
            String contentText="";
            for (String element:elements) {
                contentText=contentText+element+"|";
            }
            if(contentText!=null && contentText!="")
            {
                contentText.substring(contentText.length() - 1);
                editText.setText(contentText);
            }

        }


        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                String textData= editText.getText().toString();
                if(textData!=null && textData!="" ){
                    if(" ".equals(textData) || "|".equals(textData) || "| ".equals(textData) || " | ".equals(textData) || " |".equals(textData)){
                        Toast.makeText(getApplicationContext(), "The text value can't be empty", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        String[] data=textData.toLowerCase().split("\\|");
                        Set<String> dataSet = new HashSet<>();
                        Collections.addAll(dataSet, data);
                        editor.putStringSet("savedData",dataSet);
                        editor.apply();

                        Intent intent = new Intent(NewElementActivity.this, MainActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
                        startActivity(intent,options.toBundle());
                    }

                }
            }

        });

        Button buttonReset = (Button) findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor editor = preferences.edit();
            Set<String> dataSet = new HashSet<>(Arrays.asList("0", "0,5", "1", "2", "3", "5", "8", "13", "20", "40", "100", "∞", "?", "☕"));

            editor.putStringSet("savedData",dataSet);
            editor.apply();

            Intent intent = new Intent(NewElementActivity.this, MainActivity.class);
            ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
            startActivity(intent,options.toBundle());

        }

        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewElementActivity.this, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fadein, R.anim.fadeout);
        startActivity(intent, options.toBundle());
    }

}
