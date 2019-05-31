package spelling_words.spellingwords;

import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.Log;

import java.util.ArrayList;
import java.util.zip.Adler32;

import spelling_words.spellingwords.Interfaces.IWord;

public class Word implements IWord {
    private final String NOUN = "Noun";
    private final String ADJECTIVE = "Adjective";
    private final String ADVERB = "Adverb";
    private Cursor res = null;
    protected int id;
    protected String word;
    protected String language;
    protected int weekList;
    protected MediaStore.Audio sound;
    protected ArrayList<String[]> Sentences;

    public Word(String word) {
        this.word = word;
        Init();
    }

    public Word (Cursor res) {
        this.res = res;
        Init();
    }

    private void Init() {
        Sentences = new ArrayList<String[]>();

        if(res != null) {
            while (res.moveToNext()) {
                this.word = res.getString(1);
                this.id = Integer.parseInt(res.getString(0));
                this.weekList = Integer.parseInt(res.getString(3));
                if (res.getString(2).equals(NOUN)) {
                    this.Sentences.add(new String[] {res.getString(2),NOUN});
                }
                if (res.getString(2).equals(ADJECTIVE)) {
                    this.Sentences.add(new String[] {res.getString(2), ADJECTIVE});
                }
                if (res.getString(2).equals(ADVERB)) {
                    this.Sentences.add(new String[] {res.getString(2), ADVERB});
                }
            }
        }
    }

    @Override
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public void setSound(MediaStore.Audio sound) {
        this.sound = sound;
    }

    @Override
    public void setExample(ArrayList<String[]> examples) {
        Sentences = examples;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public MediaStore.Audio getSound() {
        return sound;
    }

    @Override
    public ArrayList<String[]> getExamples() {

        return new ArrayList<String[]>(Sentences);
    }
}
