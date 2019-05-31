package spelling_words.spellingwords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.util.Scanner;

import spelling_words.spellingwords.DataAccess.SpellingListData;
import spelling_words.spellingwords.Interfaces.ITableLayoutCreator;
import spelling_words.spellingwords.Interfaces.IViewController;
import spelling_words.spellingwords.Interfaces.IWord;

public class MainActivity extends AppCompatActivity {
    ListView simpleList;
    private IViewController view;
    private int exitAttempts = 2;
    private ITableLayoutCreator tableLayoutCreator;
    private SpellingListData spellingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize Components

        Button btn = findViewById(R.id.button6);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent libraryPage = new Intent(MainActivity.this, SpellingActivity.class);

                libraryPage.putExtra("string", "1");

                startActivity(libraryPage);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(view.isAppClosing()) {
            super.onBackPressed();
        } else { view.showHomePage(); }
    }

    private void importFile(String load) {
        Scanner read;
        try {
            read = new Scanner(getAssets().open(load));

            while (read.hasNext()) {
                String[] parse = read.nextLine().split("ANSWER");
                //IWord word =
                //SpellingWords.getInstance().addSpellingWord()//addCard(new Card(parse[0], parse[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
