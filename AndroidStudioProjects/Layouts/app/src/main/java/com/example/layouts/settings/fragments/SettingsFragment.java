package com.example.layouts.settings.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.layouts.MainActivity;
import com.example.layouts.R;
import com.example.layouts.settings.SettingsActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        findPreference("pref_key_dark_theme").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                updateTheme((Boolean) newValue);
                return true;
            }
        });

        findPreference("pref_key_eliminate_all").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showConfirmationDialog();
                return true;
            }
        });
    }

    private void updateTheme(boolean isDarkThemeEnabled) {
        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        requireActivity().recreate();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle(R.string.borrarDB);
        builder.setMessage(R.string.confirmacionDBBorrar);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendConfirmationMessageToMainActivity();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    private void sendConfirmationMessageToMainActivity() {
            Bundle bundle = new Bundle();
            bundle.putString("delete", "delete");
            ((SettingsActivity) getActivity()).startMainActivityWithBundle(bundle);
    }
}
