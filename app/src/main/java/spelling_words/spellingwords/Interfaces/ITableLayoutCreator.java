package spelling_words.spellingwords.Interfaces;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public interface ITableLayoutCreator {

    public void setContext(Context context);
    public void setViewController(IViewController viewController);
    public TableLayout getTableLayout();
    public TableRow getTableRow(int index);
    public void CreateTabelRows();
}
