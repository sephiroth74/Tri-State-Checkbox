package it.sephiroth.android.library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import it.sephiroth.android.library.checkbox3state.CheckBox3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var checkbox_array: Array<CheckBox>
    var listenToUpdates = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (listenToUpdates) {
                listenToUpdates = false
                if (!isChecked) {
                    checkbox_array.forEach { it.isChecked = false }
                } else if (isChecked) {
                    checkbox_array.forEach { it.isChecked = true }
                }
                checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly)
                listenToUpdates = true
            }
        }

    }

    override fun onContentChanged() {
        super.onContentChanged()

        checkbox_array = arrayOf(checkBox2, checkBox3, checkBox4)

        checkbox_array.forEach {
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                if (listenToUpdates) {
                    listenToUpdates = false
                    val checked_size = checkbox_array.filter { it.isChecked }.size

                    if (checked_size == checkbox_array.size) {
                        checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly)
                        checkBox1.setChecked(true, false)
                    } else if (checked_size == 0) {
                        checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly)
                        checkBox1.setChecked(false, false)
                    } else {
                        checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleIndeterminate)
                        checkBox1.setChecked(true, true)
                    }
                    listenToUpdates = true
                }
            }
        }
    }
}
