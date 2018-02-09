package com.markazuschlag.flourhour;


import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import java.util.prefs.Preferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load preferences from the XML file
        addPreferencesFromResource(R.xml.app_preferences);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference incrementsPref = (ListPreference) preference;
            int index = incrementsPref.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary(
                    index >= 0
                            ? incrementsPref.getEntries()[index]
                            : null);
        } else if (preference instanceof EditTextPreference) {
            EditTextPreference targetPref = (EditTextPreference) preference;
            String target = targetPref.getText();
            if (target != null) {
                preference.setSummary(target);
            }

        }
        return true;
    }
}
