package it.sephiroth.android.library.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
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
                checkBox1.text = if(isChecked) "Select None" else "Select All"
                checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly)
                listenToUpdates = true
            }
        }
        buttonToJava.setOnClickListener{
            val intent = Intent (this, MainActivity2::class.java)
            startActivity(intent)
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
                        checkBox1.text = "Select None"
                    } else if (checked_size == 0) {
                        checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleCheckedUncheckedOnly)
                        checkBox1.setChecked(false, false)
                        checkBox1.text = "Select All"
                    } else {
                        checkBox1.setCycle(R.array.sephiroth_checkbox3_cycleAll)
                        checkBox1.setChecked(false, true)
                        checkBox1.text = "Select All"
                    }
                    listenToUpdates = true
                }
            }
        }
    }
}
