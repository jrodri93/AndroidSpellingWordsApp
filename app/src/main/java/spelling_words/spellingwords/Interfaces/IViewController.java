package spelling_words.spellingwords.Interfaces;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;
import android.widget.ViewSwitcher;

public interface IViewController {

    public void setView(ViewSwitcher view);
    public void setContext(Context context);
    public void showSelectionPage();
    public void showDescriptionPage();
    public void showHomePage();

    public boolean isViewHome();
    public Context getContext();
    public View getView();
    public boolean isAppClosing();
}
