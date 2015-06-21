package legend.jacko;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity  {
ImageView image;
    TextView ent1;
            TextView ent3;
    TextView ent2;
    Button btn;
    Animation animFadein1;
    Animation animFadein2;
    Animation animFadein3;

    Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar abar=getActionBar();
//        abar.setIcon(R.mipmap.ic_launcher);
        image= (ImageView) findViewById(R.id.imageView);

        ent1 = (TextView) findViewById(R.id.entrance2);
        ent2 = (TextView) findViewById(R.id.entrance3);
        ent3 = (TextView) findViewById(R.id.entrance4);
        btn = (Button) findViewById(R.id.button);

        // load the animation

        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animFadein1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animFadein2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        animFadein3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        // set animation listener
        animFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ent2.setVisibility(View.VISIBLE);
                ent2.startAnimation(animFadein1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ent1.setVisibility(View.VISIBLE);
        ent1.startAnimation(animFadein);

        animFadein1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ent3.setVisibility(View.VISIBLE);
                ent3.startAnimation(animFadein2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFadein2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn.setVisibility(View.VISIBLE);
                btn.startAnimation(animFadein3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animFadein3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,Home.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
