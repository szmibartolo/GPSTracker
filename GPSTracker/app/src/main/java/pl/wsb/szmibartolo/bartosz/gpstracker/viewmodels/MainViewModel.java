package pl.wsb.szmibartolo.bartosz.gpstracker.viewmodels;

import android.databinding.ObservableBoolean;
import android.view.View;

import pl.wsb.szmibartolo.bartosz.gpstracker.models.Stan;
import pl.wsb.szmibartolo.bartosz.gpstracker.storage.SharedPreferencesStorage;

/**
 * Created by Maciej on 2016-12-12.
 */

public class MainViewModel {

    public ObservableBoolean isVisibleStartWorkButton = new ObservableBoolean();
    public ObservableBoolean isVisibleEndWorkButton = new ObservableBoolean();
    public ObservableBoolean isVisibleStartBreakButton = new ObservableBoolean();
    public ObservableBoolean isVisibleEndBreakButton = new ObservableBoolean();


    private Stan stan;

    public MainViewModel(SharedPreferencesStorage sharedPreferencesStorage) {
        stan = sharedPreferencesStorage.loadStan();
        updateButtonsVisibility(stan);
    }

    public void onStartWorkButtonClick(View view) {
        stan = Stan.ATWORK;
        updateButtonsVisibility(stan);
    }

    public void onEndWorkButtonClick(View view) {
        stan = Stan.LOGGEDIN;
        updateButtonsVisibility(stan);
    }

    public void onStartBreakButtonClick(View view) {
        stan = Stan.ONBREAK;
        updateButtonsVisibility(stan);
    }

    public void onEndBreakButtonClick(View view) {
        stan = Stan.ATWORK;
        updateButtonsVisibility(stan);
    }

    private void updateButtonsVisibility(Stan stan) {
        isVisibleStartWorkButton.set(stan == Stan.LOGGEDIN);
        isVisibleEndWorkButton.set(stan == Stan.ATWORK);
        isVisibleStartBreakButton.set(stan == Stan.ATWORK);
        isVisibleEndBreakButton.set(stan == Stan.ONBREAK);
    }
}
