package spelling_words.spellingwords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private ViewSwitcher view;
    private int exitAttempts = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize viewSwitcher
        view = (ViewSwitcher) findViewById(R.id.view);
        final Button btn21 = (Button) findViewById(R.id.btnWord1);
        TableLayout tblRow = findViewById(R.id.tableRow);
        tblRow.getChildAt(1).addChildrenForAccessibility(new Button(super));


        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                view.showNext();
            }
        });

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
        if(view.getDisplayedChild() == 1) { view.showPrevious(); }
        else {
            exitAttempts--;
            Toast.makeText(this, String.valueOf(exitAttempts) + " attempts left to exit app.", Toast.LENGTH_SHORT).show();
        }

        if (exitAttempts == 0) {
            super.onBackPressed();
        }
    }

    Button createOnClickEvent(final Button click) {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize file with subject to study
                //String file = click.getText().toString().replaceAll(" ", "") + ".txt";
                //Display log on file name to load
                Log.d("Switching view: ", "view2");

                    //switch views.
                    view.showNext();
                }
        });
        return click;
    }
}
