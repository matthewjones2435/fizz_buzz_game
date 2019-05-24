package edu.cnm.deepdive.fizzbuzz;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

  private int value;
  private TextView valueDisplay;
  private Timer timer;
  private boolean running;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d("Trace","Starting onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    valueDisplay = findViewById(R.id.value_display);
    Log.d("Trace","Leaving onCreate");
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    Log.d("Trace","Entering onRestoreInstanceState");
    super.onRestoreInstanceState(savedInstanceState);
    Log.d("Trace","Leaving onRestoreInstanceState");
  }

  @Override
  protected void onRestart() {
    Log.d("Trace","Entering onRestart");
    super.onRestart();
    Log.d("Trace","Leaving onRestart");
  }

  @Override
  protected void onStart() {
    Log.d("Trace","Entering onStart");
    super.onStart();
    Log.d("Trace","Leaving onStart");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    Log.d("Trace","Entering onCreateOptionsMenu");
    getMenuInflater().inflate(R.menu.options, menu);
    Log.d("Trace","Leaving onCreateOptionsMenu");
    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuItem play = menu.findItem(R.id.play);
    MenuItem pause = menu.findItem(R.id.pause);
    play.setEnabled(!running);
    play.setVisible(!running);
    pause.setEnabled(running);
    pause.setVisible(running);
    return true;
  }

  @Override
  protected void onResume() {
    Log.d("Trace","Entering onResume");
    super.onResume();
    Log.d("Trace","Leaving onCreate");
  }


  @Override
  protected void onPause() {
    Log.d("Trace","Entering onPause");
    super.onPause();
    Log.d("Trace","Leaving onPause");
  }

  @Override
  protected void onStop() {
    Log.d("Trace","Entering onStop");
    super.onStop();
    Log.d("Trace","Leaving onStop");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    Log.d("Trace","Entering onSaveInstanceState");
    super.onSaveInstanceState(outState);
    Log.d("Trace","Leaving onSaveInstanceState");
  }

  @Override
  protected void onDestroy() {
    Log.d("Trace","Entering onDestroy");
    super.onDestroy();
    Log.d("Trace","Leaving onDestroy");
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Log.d("Trace","Entering onOptionsItemsSelected");
    boolean handled = true;
    switch (item.getItemId()){
      case R.id.play:
        resumeGame();
        break;
      case R.id.pause:
        pauseGame();
        break;
      case R.id.settings:
        Intent intent = new Intent (this, SettingsActivity.class);
        startActivity(intent);

        break;
      default:
        handled = super.onOptionsItemSelected(item);
        break;
    }
    Log.d("Trace","Leaving onOptionsItemsSelected");
    return handled;
  }

  private void pauseGame(){
    running = false;
    if (timer != null){
      timer.cancel();
      timer= null;
    }
    // TODO update any fields, timer, and menu
    invalidateOptionsMenu();
  }

  private void resumeGame(){
    running = true;
    timer = new Timer();
    timer.schedule(new RandomValueTask(), 0, 3000); // FIXME this should read preferences
    // TODO update any fields, timer, and menu
    invalidateOptionsMenu();
  }


  private class RandomValueTask extends TimerTask {

    private Random rng = new Random();

    @Override
    public void run() {
      Log.d("Trace","Entering run");
      value = rng.nextInt(100);
      runOnUiThread(()-> valueDisplay.setText(Integer.toString(value)));
      Log.d("Trace","Leaving run");
    }
  }

}

