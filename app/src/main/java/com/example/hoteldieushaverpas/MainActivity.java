package com.example.hoteldieushaverpas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;
/*import java.io.*;
import java.net.*;
import com.google.gson.*;
import javax.net.ssl.HttpsURLConnection;*/

public class MainActivity extends AppCompatActivity {
    int result;
    TextToSpeech speech;
    Button input;
    String finalString = "";
    String testFinalString = "";
    TextView currentOutput;
    String currLetter = "";
    TableLayout mainTable;
    TableRow currRow;
    ArrayList<Button>clickedButtons = new ArrayList<Button>();

    /*static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/spellcheck";

    static String key = "<f88530b123664beeabc0f088f7771702>";

    static String mkt = "en-US";
    static String mode = "proof";
    static String text = "Hollo, wrld!";


    public static void check() throws Exception {
        String params = "?mkt=" + mkt + "&mode=" + mode;
        // add the rest of the code snippets here (except prettify() and main())...
        URL url = new URL(host + path + params);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("text=" + text);
        wr.flush();
        wr.close();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);


    }*/

    public void newWord(View view){
        finalString += " ";


    }



    public void reset(View view){

        currentOutput.setText("");
        if (currRow != null){
            currRow.setBackgroundColor(Color.BLACK);

        }
        for (Button button : clickedButtons){
            button.setAlpha((float) 1.0);

        }
        finalString = "";
        testFinalString = "";

    }






    public void onLetterChoice(View view){
        if (currRow != null) {
            currRow.setBackgroundColor(Color.BLACK);
        }
        input = (Button)findViewById(view.getId());
        currRow = (TableRow)input.getParent();
        currRow.setBackgroundColor(Color.YELLOW);
        testFinalString = finalString;
        if (input.getText().toString().equals(currLetter)){
            currRow.setBackgroundColor(Color.BLACK);
            finalString += currLetter;
            currentOutput.setText(finalString);
            speak(finalString);
            for (Button button : clickedButtons){
                button.setAlpha((float) 1.0);

            }


            clickedButtons = new ArrayList<Button>();
        } else {
            speak(input.getText().toString());
            testFinalString += input.getText().toString();
            currentOutput.setText(testFinalString);
            clickedButtons.add(input);
            input.setAlpha((float) 0.5);
        }

        currLetter = input.getText().toString();








    }


    private void speak(String text){
        speech.setPitch(1);
        speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentOutput = (TextView)findViewById(R.id.outputText);
        mainTable = (TableLayout) findViewById(R.id.viewTable);
        speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int result = speech.setLanguage(Locale.ENGLISH);

                }





            }
        });
        /*mainTable.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch(dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        //etc etc. do some stuff with the drag event
                        Log.i("We are dragging","");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        //do something with the position (a scroll i.e);

                        Log.i("We are dragging","");
                        break;
                    default:
                }



                return true;
            }
        });*/



    }
}
