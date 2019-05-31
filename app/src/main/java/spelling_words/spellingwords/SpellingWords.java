package spelling_words.spellingwords;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import spelling_words.spellingwords.Interfaces.IWord;

public class SpellingWords {
    private static final SpellingWords ourInstance = new SpellingWords();
    private final String NOUN = "Noun";
    private final String ADJECTIVE = "Adjective";
    private final String ADVERB = "Adverb";
    private int count = 1;
    private int spellingListWeek;
    private boolean hasWeekBeenSet;
    private ArrayList<String> wordsOfTheWeek;
    private ArrayList<IWord> words;
    public static SpellingWords getInstance() {
        return ourInstance;
    }

    private SpellingWords() {
        hasWeekBeenSet = false;
        words = new ArrayList<IWord>();
        wordsOfTheWeek = new ArrayList<String>();
    }

    public boolean addSpellingWord(IWord word) {
        count++;
        return (words.add(word));
    }

    public ArrayList<IWord> getSpellingList() {
        return new ArrayList<IWord>(words);
    }

    public int getCount() {
        return count;
    }

    public boolean setWordsOfTheWeek(Cursor res) {
        try{
            while(res.moveToNext()) {
                wordsOfTheWeek.add(res.getString(0));
                Log.d( "What is this word", res.getString(0));
            }
        }catch (Exception E) {
            return false;
        }

        return true;
    }

    public ArrayList<String> getWordsOfTheWeek() {
        return new ArrayList<String>(wordsOfTheWeek);
    }

    public void setSpellingWeek(int week) {
        spellingListWeek = week;
        hasWeekBeenSet = true;
    }
}
