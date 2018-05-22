package com.test.hex.draftapp

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*

private const val TAG = "myLogs"

private const val MENU_COLOUR_RED = 1
private const val MENU_COLOUR_GREEN = 2
private const val MENU_COLOUR_BLUE = 3

private const val MENU_SIZE_22 = 4
private const val MENU_SIZE_26 = 5
private const val MENU_SIZE_30 = 6

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var checkBox: CheckBox
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var textView1: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useLayout(R.layout.l015_context_menu)
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        checkBox2.setOnCheckedChangeListener { _, _ ->
            invalidateOptionsMenu() }
        menu?.add(2, 4, 4, "item4")?.isCheckable = true
        return true
    }
*//*
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.action_settings -> {
                Toast.makeText(this,
                        getString(R.string.action_settings),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item1 -> {
                Toast.makeText(this,
                        getString(R.string.action_item1),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item2 -> {
                Toast.makeText(this,
                        getString(R.string.action_item2),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item3 -> {
                Toast.makeText(this,
                        getString(R.string.action_item3),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            4 -> {
                item.isChecked = !item.isChecked
            }
        }
        return super.onOptionsItemSelected(item)
    }
*/
    private fun useLayout(layout: Int){
        when (layout) {
            R.layout.l008_view_by_id -> {
                setContentView(layout)

                textView = findViewById(R.id.helloWorld)
                textView.text = getString(R.string.easy_android)

                button = findViewById(R.id.mybtn)
                button.text = getString(R.string.my_button)
                button.isEnabled = false

                checkBox = findViewById(R.id.checkBox)
                checkBox.isChecked = true
            }
            R.layout.l009_click_button -> {
                setContentView(layout)

                textView = findViewById(R.id.textView)
                textView.text = getString(R.string.click_any_button)
                button1 = findViewById(R.id.button1)
                button2 = findViewById(R.id.button2)
                button3 = findViewById(R.id.button3)

                button1.setOnClickListener {
                    textView.text = "Нажата кнопка ${button1.text}"
                }

//                button2.setOnClickListener(this)
            }
            R.layout.l010_click_buttons -> {
                setContentView(layout)

                textView = findViewById(R.id.textView)
                textView.text = getString(R.string.click_any_button)

                Log.d(TAG, "Найдем три кнопки")

                button1 = findViewById(R.id.button1)
                Log.d(TAG, "Найдена кнопка 1")
                button2 = findViewById(R.id.button2)
                Log.d(TAG, "Найдена кнопка 2")
                button3 = findViewById(R.id.button3)
                Log.d(TAG, "Найдена кнопка 3")

                button1.setOnClickListener {
                    textView.text = getString(R.string.text1)
                    Log.d(TAG, "Нажата кнопка 1")
                    Toast.makeText(this, "Нажата кнопка 1",
                            Toast.LENGTH_SHORT)
                            .centerGravitation()
                            .show()
                }

                button2.setOnClickListener {
                    textView.text = getString(R.string.text2)
                    Log.d(TAG, "Нажата кнопка 2")
                    Toast.makeText(this, "Нажата кнопка 2",
                            Toast.LENGTH_SHORT)
                            .centerGravitation()
                            .show()
                }
                button3.setOnClickListener {
                    textView.text = getString(R.string.text3)
                    Log.d(TAG, "Нажата кнопка 3")
                    Toast.makeText(this, "Нажата кнопка 3",
                            Toast.LENGTH_SHORT)
                            .centerGravitation()
                            .show()
                }
                textView.setOnClickListener {
                    textView.text = getString(R.string.click_any_button)
//                    button3.text = getString(R.string.button_number_three)
                    val toast = Toast.makeText(this, "Нажат текст",
                            Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP, 0, 0)
                    val toastImage = toast.view as LinearLayout
                    val imageView = ImageView(this)
                    imageView.setImageResource(R.drawable.image001)
                    toastImage.addView(imageView, 0)
                    toast.show()
                }
            }
            R.layout.l014_menu -> {
                setContentView(layout)

                checkBox1 = findViewById(R.id.checkBox1)
                checkBox2 = findViewById(R.id.checkBox2)

            }
            R.layout.l015_context_menu -> {
                setContentView(layout)
                textView1 = findViewById(R.id.textView1)
                textView2 = findViewById(R.id.textView2)

                registerForContextMenu(textView1)
                registerForContextMenu(textView2)
            }

            else -> setContentView(layout)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        when (v?.id) {
            R.id.textView1 -> {
                menu?.add(0, MENU_COLOUR_RED, 0, "Red")
                menu?.add(0, MENU_COLOUR_GREEN, 0, "Green")
                menu?.add(0, MENU_COLOUR_BLUE, 0, "Blue")
            }
            R.id.textView2 -> {
                menu?.add(0, MENU_SIZE_22, 0, "22")
                menu?.add(0, MENU_SIZE_26, 0, "26")
                menu?.add(0, MENU_SIZE_30, 0, "30")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_COLOUR_RED -> {
                textView1.setTextColor(Color.RED)
                textView1.setText("Text colour = red")
            }
            MENU_COLOUR_GREEN -> {
                textView1.setTextColor(Color.GREEN)
                textView1.setText("Text colour = green")
            }
            MENU_COLOUR_BLUE -> {
                textView1.setTextColor(Color.BLUE)
                textView1.setText("Text colour = blue")
            }
            MENU_SIZE_22 -> {
                textView2.setTextSize(22F)
                textView2.setText("Text size = 22")
            }
            MENU_SIZE_26 -> {
                textView2.setTextSize(26F)
                textView2.setText("Text size = 26")
            }
            MENU_SIZE_30 -> {
                textView2.setTextSize(30F)
                textView2.setText("Text size = 30")
            }
        }

        return super.onContextItemSelected(item)
    }
/*
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_mail)?.isVisible = checkBox2.isChecked

        menu?.setGroupVisible(R.id.group1, checkBox1.isChecked)

        return super.onPrepareOptionsMenu(menu)
    }*/
}

private fun Toast.centerGravitation(): Toast {
    this.setGravity(Gravity.CENTER, 0, 0)
    return this
}
