package legend.jacko;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;


public class Home extends ActionBarActivity {
    ImageButton store, play;
    EditText input;
    String speakTextTxt;
    TextToSpeech mTts;
    HashMap<String, String> myHashRender = new HashMap<String, String>();
    String tempDestFile;
    Animation animActivity;
    float Speechrate=0.7f;
    private SeekBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//ActionBar abar=getActionBar();
//        abar.setIcon(R.mipmap.ic_launcher);

        setContentView(R.layout.activity_home);
        bar = (SeekBar)findViewById(R.id.seekBar);
        play=(ImageButton) findViewById(R.id.talk);
        store=(ImageButton) findViewById(R.id.save);
        input = (EditText) findViewById(R.id.my_text);
        input.setMovementMethod(new ScrollingMovementMethod());
        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTts.setLanguage(Locale.UK);
                    mTts.setSpeechRate(Speechrate);
                }
            }
        });
       play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (input.getText().toString()!=null) {
                    String toSpeak = input.getText().toString();
                    Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                    mTts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
                else{

                    Toast.makeText(getApplicationContext(),"Enter text to Speak",Toast.LENGTH_SHORT).show();


                }
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  if (input.getText().toString() !=null){
                AlertDialog.Builder alert = new AlertDialog.Builder(Home.this);

                alert.setTitle("JacKO");
                alert.setMessage("Save file Name");

// Set an EditText view to get user input
                final EditText sinput = new EditText(getApplicationContext());
                alert.setView(sinput);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = sinput.getText().toString();
                        // Do something with value!
                        SaveSong(input.getText().toString(), sinput.getText().toString());
                        String exStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                        openFolder(exStoragePath + "/jacko");
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
                else
                  {
                      Toast.makeText(getApplicationContext(),"Enter text to Save",Toast.LENGTH_SHORT).show();


                  }
            }
        });
bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
float curval=0.0f;
        switch (progress){
            case 1:
                curval=0.1f;
                break;
            case 2:
                curval=0.2f;
                break;
            case 3:
                curval=0.3f;
                break;
            case 4:
                curval=0.4f;
                break;
            case 5:
                curval=0.5f;
                break;
            case 6:
                curval=0.6f;
                break;
            case 7:
                curval=0.7f;
                break;
            case 8:
                curval=0.8f;
                break;
            case 9:
                curval=0.9f;
                break;
            case 10:
                curval=1.0f;
                break;
             default:
                curval=1.2f;
                break;

        }
        mTts.setSpeechRate( curval);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});
    }

    public void onPause() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    void SaveSong(String texttosay, String tempFilename) {

        myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, texttosay);

        String exStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File appTmpPath = new File(exStoragePath + "/jacko");
        appTmpPath.mkdirs();
        tempFilename = tempFilename+ ".wav";
        tempDestFile = appTmpPath.getAbsolutePath() + "/" + tempFilename;
       // new MySpeech(texttosay);
        mTts.synthesizeToFile(texttosay, myHashRender, tempDestFile );
        Toast.makeText(getApplicationContext(),appTmpPath.getAbsolutePath() + "/" + tempFilename +"Saved",Toast.LENGTH_SHORT).show();

    }

    class MySpeech implements TextToSpeech.OnInitListener {

        String tts;

        public MySpeech(String tts) {
            this.tts = tts;
            mTts = new TextToSpeech(Home.this, this);
        }

        @Override
        public void onInit(int status) {
            Log.v("log", "initi");
           // mTts.synthesizeToFile(texttosay, myHashRender, tempDestFile);
        }
    }
    void openFolder(String path){

        Intent intent= new Intent(Intent.ACTION_GET_CONTENT);

        Uri uri=Uri.parse(path);
        intent.setDataAndType(uri, "folder/*");
        startActivity(Intent.createChooser(intent,"Open in"));



    }
}
