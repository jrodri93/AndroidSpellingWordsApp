package spelling_words.spellingwords;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import spelling_words.spellingwords.Interfaces.ITableLayoutCreator;
import spelling_words.spellingwords.Interfaces.IViewController;

public class ViewController implements IViewController {
    private ViewSwitcher view;
    private Context context;
    private ITableLayoutCreator tableLayoutCreator;
    private int attempts = 3;
    private boolean exit = false;
    private int rowCount = 6;
    private int rowHeight = 5;

    public ViewController(ViewSwitcher view) {
        this.view = view;
        this.context = view.getContext();
        tableLayoutCreator = new TableLayoutCreator(this);
        tableLayoutCreator.CreateTabelRows();
    }
    @Override
    public void setView(ViewSwitcher view) {
        this.view = view;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void showSelectionPage() {

    }

    @Override
    public void showDescriptionPage() {
        resetExitAttempts();
        if(isViewHome()) { view.showNext(); }
    }

    @Override
    public void showHomePage() {
        if(view.getDisplayedChild() == 0) {
            Toast.makeText(view.getContext(), String.valueOf(--attempts) + " attempts left to exit.", Toast.LENGTH_SHORT).show();
        } else if(!isViewHome()) { view.showPrevious(); }
        exit = (attempts <= 0);
    }

    @Override
    public boolean isViewHome() {
        return (view.getDisplayedChild() == 0);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public boolean isAppClosing() {
        return exit;
    }

    private void resetExitAttempts() {
        attempts = 3;
    }
}
