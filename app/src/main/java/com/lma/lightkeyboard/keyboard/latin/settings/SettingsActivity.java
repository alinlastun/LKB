
package com.lma.lightkeyboard.keyboard.latin.settings;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import com.lma.lightkeyboard.keyboard.latin.utils.FragmentUtils;

public final class SettingsActivity extends PreferenceActivity {
    private static final String DEFAULT_FRAGMENT = SettingsFragment.class.getName();

    public static final String EXTRA_SHOW_HOME_AS_UP = "show_home_as_up";
    public static final String EXTRA_ENTRY_KEY = "entry";
    public static final String EXTRA_ENTRY_VALUE_LONG_PRESS_COMMA = "long_press_comma";

    private boolean mShowHomeAsUp;

    @Override
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        final ActionBar actionBar = getActionBar();
        final Intent intent = getIntent();
        if (actionBar != null) {
            mShowHomeAsUp = intent.getBooleanExtra(EXTRA_SHOW_HOME_AS_UP, true);
            actionBar.setDisplayHomeAsUpEnabled(mShowHomeAsUp);
            actionBar.setHomeButtonEnabled(mShowHomeAsUp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (mShowHomeAsUp && item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Intent getIntent() {
        final Intent intent = super.getIntent();
        final String fragment = intent.getStringExtra(EXTRA_SHOW_FRAGMENT);
        if (fragment == null) {
            intent.putExtra(EXTRA_SHOW_FRAGMENT, DEFAULT_FRAGMENT);
        }
        intent.putExtra(EXTRA_NO_HEADERS, true);
        return intent;
    }

    @Override
    public boolean isValidFragment(final String fragmentName) {
        return FragmentUtils.isValidFragment(fragmentName);
    }
}
