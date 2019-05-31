package spelling_words.spellingwords.Interfaces;

import android.provider.MediaStore;

import java.util.ArrayList;

public interface IWord {

    public void setWord(String word);
    public void setSound(MediaStore.Audio sound);
    public void setExample(ArrayList<String[]> examples);

    public String getWord();
    public MediaStore.Audio getSound();
    public ArrayList<String[]> getExamples();
}
