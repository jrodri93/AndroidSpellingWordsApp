package spelling_words.spellingwords.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import spelling_words.spellingwords.Interfaces.IWord;
import spelling_words.spellingwords.R;
import spelling_words.spellingwords.Word;

public class SpellingListData extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase database;
    private final static String dbName = "SpellingListDataBase.db";
    private final static int DATABASE_VERSION = 3;
    private String query;
    private final String ALL = "*";
    private final String tblWords = "Words";
    private final String tblCategory = "Category";
    private final String tblSentences = "Sentences";
    private final String tblAll = "Words, Sentences, Category";
    private final String[] COLUMNS = {"Words.WordID, Words.Word, Words.Language, Sentences.ID, Sentences.Sentence, Sentences.CategoryID, Category.CategoryType",
                                        "Words.Word"};
    private final String[] WHERE = {"Word = ?", "Word = ? AND Category = ?",
                                    "Category.CategoryID = Sentences.CategoryID AND CategoryType = ?", "CategoryType = ?",
                                    "Words.Word = Sentences.Word AND Sentences.CategoryID = Category.CategoryID AND Words.Word = ?",
                                    "Words.Week = ?"};

    public SpellingListData(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
        this.context = context;

        if(!doesDBExist()) {
            createDB();
        }
    }

    public IWord getWord(String word) {
        query = String.format(context.getString(R.string.SELECT_FROM_WHERE), COLUMNS[0], tblAll, WHERE[4]);
        Log.d("query----", query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(query, new String[] { word } );

        return new Word(res);
    }

    public Cursor getWords(int week) {
        query = String.format(context.getString(R.string.SELECT_FROM_WHERE), COLUMNS[1], tblWords, WHERE[5]);
        Log.d("query----", query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(query, new String[] {String.valueOf(week)} );
        return res;
    }

//    public IWord getAllSentences(String word) {
//        query = String.format(context.getString(R.string.SELECT_FROM_WHERE), ALL, tblSentences, WHERE[0]);
//        Log.d("query----", query);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery(query, new String[] { word } );
//
//        return new Word(res);
//    }
//
//    public Cursor getSentencesByCategoryType(String word, String CategoryType) {
//        query = String.format(context.getString(R.string.SELECT_FROM_WHERE), ALL, tblWords,WHERE[2]);
//        Log.d("query----", query);
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery(query, new String[] { word, CategoryType } );
//        return res;
//    }

    public boolean clearDB() {
        if(!doesDBExist()) {
            Log.d("DB does not Exist", String.valueOf(doesDBExist()));
            return false;
        }
        return context.getDatabasePath(dbName).delete();
    }

    public boolean doesDBExist() {
        return (context.getDatabasePath(dbName).exists());
    }

    private void createDB() {
        //Copy DB from assets to database folder
        String dbPath =  context.getDatabasePath(dbName).getPath();
        context.openOrCreateDatabase(dbPath, 0, null);
        try {
            InputStream ipDB = context.getAssets().open(dbName);
            OutputStream opDB = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length =ipDB.read(buffer))> 0) {
                opDB.write(buffer, 0, length);
            }
            Log.d("DB move Successful", dbPath);
            opDB.flush();
            opDB.close();
            ipDB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
