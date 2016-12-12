package pl.wsb.szmibartolo.bartosz.gpstracker.bindings;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by Maciej on 2016-12-12.
 */

public class MyBindings {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

}
