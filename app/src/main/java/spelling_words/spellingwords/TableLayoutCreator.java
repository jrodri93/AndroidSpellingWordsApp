package spelling_words.spellingwords;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

import spelling_words.spellingwords.Interfaces.ITableLayoutCreator;
import spelling_words.spellingwords.Interfaces.IViewController;
import spelling_words.spellingwords.Interfaces.IWord;

public class TableLayoutCreator implements ITableLayoutCreator {
    private TableLayout tableLayout;
    private ArrayList<TableRow> tableRows;
    private IViewController viewController;
    private Context context;

    public TableLayoutCreator(IViewController viewController) {
        this.viewController = viewController;
        this.context = viewController.getContext();
        tableRows = new ArrayList<TableRow>();
        tableLayout = viewController.getView().findViewById(R.id.tableLayout);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setViewController(IViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public TableLayout getTableLayout() {
        return this.tableLayout;
    }

    @Override
    public TableRow getTableRow(int index) {
        if(tableRows.size() > index) {
            return tableRows.get(index);
        }
        return null;
    }

    @Override
    public void CreateTabelRows() {
        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                20.0f
        );

        for(int row = 0; row < 5; row++) {
            tableRows.add(new TableRow(context));
        }

        for(TableRow row : tableRows) {
            tableLayout.addView(row, rowParams);
        }

        int index = 0, count = 0;
        for (IWord word : SpellingWords.getInstance().getSpellingList()) {
            if(count > 0 && (count)%5 == 0) { index++; }
            tableRows.get(index).addView(createOnClickEvent(word));
            count++;
        }
    }

    private Button createOnClickEvent(IWord word) {
        Button button = new Button(context);
        button.setText(word.getWord());
        button.setId(word.getWord().toCharArray().hashCode());

        TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
        );
        button.setTextSize(24);
        button.setPadding(5,0,5,0);
        button.setLayoutParams(buttonParams);
        button.setBackgroundColor(Color.rgb(rgb(),rgb(),rgb()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                viewController.showDescriptionPage();
            }
        });

        return button;
    }

    private int rgb(){
        return ((int) (Math.random()* 254) +1);
    }
}
