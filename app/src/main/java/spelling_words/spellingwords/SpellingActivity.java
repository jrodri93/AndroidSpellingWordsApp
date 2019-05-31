package spelling_words.spellingwords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.util.Scanner;

import spelling_words.spellingwords.DataAccess.SpellingListData;
import spelling_words.spellingwords.Interfaces.ITableLayoutCreator;
import spelling_words.spellingwords.Interfaces.IViewController;

public class SpellingActivity extends AppCompatActivity {
    ListView simpleList;
    private IViewController view;
    private int exitAttempts = 2;
    private ITableLayoutCreator tableLayoutCreator;
    private SpellingListData spellingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spelling_words_view);
        Toolbar toolbar = findViewById(R.id.toolbar);


        //Get intent
        //Get INTENT.
        final String week = getIntent().getStringExtra("string");
        //Initialize Components
//        simpleList = (ListView) findViewById(R.id.simpleListView);
//        String animalList[] = {"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_list_view, R.id.textView, animalList);
//        simpleList.setAdapter(arrayAdapter);


        spellingDB = new SpellingListData(this);
        SpellingWords.getInstance().setWordsOfTheWeek(spellingDB.getWords(Integer.parseInt(week)));

//        Cursor res = spellingDB.getSentencesByCategoryType("swimming", "noun");
//        Cursor res = spellingDB.getSentencesByCategoryType("ace", "noun");
//        Word word = (Word) spellingDB.getWord("ace");
//        Log.d("Word", word.getWord());

//        Log.d("Was Words Set", String.valueOf(SpellingWords.getInstance().setWordsOfTheWeek(spellingDB.getWords(1))));

//        while(res.moveToNext()) {
//            Log.d("res-----", res.getString(res.getColumnIndex("Word")));
//        }
        SpellingWords.getInstance().addSpellingWord(new Word("word1"));
        SpellingWords.getInstance().addSpellingWord(new Word("word2"));
        SpellingWords.getInstance().addSpellingWord(new Word("word3"));
        SpellingWords.getInstance().addSpellingWord(new Word("word4"));
        SpellingWords.getInstance().addSpellingWord(new Word("word5"));
        SpellingWords.getInstance().addSpellingWord(new Word("word6"));
        SpellingWords.getInstance().addSpellingWord(new Word("word7"));
        SpellingWords.getInstance().addSpellingWord(new Word("word8"));
        SpellingWords.getInstance().addSpellingWord(new Word("word9"));
        SpellingWords.getInstance().addSpellingWord(new Word("word10"));
        SpellingWords.getInstance().addSpellingWord(new Word("word11"));
        SpellingWords.getInstance().addSpellingWord(new Word("word12"));

        view = new ViewController((ViewSwitcher) findViewById(R.id.view));

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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