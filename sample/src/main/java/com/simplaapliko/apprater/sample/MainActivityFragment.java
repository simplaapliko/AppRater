/*
 * Copyright (C) 2015 Oleg Kan, @Simplaapliko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplaapliko.apprater.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.simplaapliko.apprater.AppRater;
import com.simplaapliko.apprater.RateAppDialog;

public class MainActivityFragment extends Fragment implements DialogInterface.OnClickListener {

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppRater.appLaunched(getActivity(), this, this, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button showDialog = (Button) view.findViewById(R.id.show_dialog);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialog = new RateAppDialog.Builder()
                        .build();

                ((RateAppDialog) dialog).setOnPositiveButtonListener(MainActivityFragment.this);
                ((RateAppDialog) dialog).setOnNegativeButtonListener(MainActivityFragment.this);
                ((RateAppDialog) dialog).setOnNeutralButtonListener(MainActivityFragment.this);

                dialog.show(getFragmentManager(), RateAppDialog.class.getSimpleName());
            }
        });

        return view;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(getContext(), "onPositiveButtonClick()", Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(getContext(), "onNegativeButtonClick()", Toast.LENGTH_SHORT).show();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                Toast.makeText(getContext(), "onNeutralButtonClick()", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}