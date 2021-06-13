package it.sephiroth.android.library.demo;


/**
 * Thanks so much to om Yohanes
 * for helped me to use this library
 */

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import it.sephiroth.android.library.checkbox3state.CheckBox3;

public class MainActivity2 extends AppCompatActivity {

    private CheckBox3 checkBox1;
    private boolean listenToUpdates = true;
    private CheckBox[] checkBoxesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (listenToUpdates) {
                    listenToUpdates = false;
                    if (!isChecked) {
                        for (CheckBox it : checkBoxesArray) {
                            it.setChecked(false);
                        }
                    } else if (isChecked) {
                        for (CheckBox it : checkBoxesArray) {
                            it.setChecked(true);
                        }
                    }
                    checkBox1.setText(isChecked ? "Select None": "Select All");
                    checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly);
                    listenToUpdates = true;
                }
            }
        });
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        checkBoxesArray = new CheckBox[] {findViewById(R.id.checkBox2), findViewById(R.id.checkBox3), findViewById(R.id.checkBox4)};

        for (CheckBox it : checkBoxesArray) {
            it.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (listenToUpdates) {
                        listenToUpdates = false;
                        int checkSize = 0;
                        for (CheckBox checkBox : checkBoxesArray) {
                            if (checkBox.isChecked()) {
                                checkSize++;
                            }
                        }

                        if (checkSize == checkBoxesArray.length) {
                            checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly);
                            checkBox1.setChecked(true, false);
                            checkBox1.setText("Select None");
                        } else if (checkSize == 0) {
                            checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly);
                            checkBox1.setChecked(false, false);
                            checkBox1.setText("Select All");
                        } else {
                            checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleAll);
                            checkBox1.setChecked(false,true);
                            checkBox1.setText("Select All");
                        }
                        listenToUpdates = true;
                    }
                }
            });
        }

    }
}