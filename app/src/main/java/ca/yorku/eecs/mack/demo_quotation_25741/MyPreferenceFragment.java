package ca.yorku.eecs.mack.demo_quotation_25741;

import android.os.Bundle;

import android.preference.PreferenceFragment;

public class MyPreferenceFragment extends PreferenceFragment
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
	}
}



