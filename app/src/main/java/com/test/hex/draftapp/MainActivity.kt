package com.test.hex.draftapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
/*
private const val TAG = "myLogs"

private const val MENU_COLOUR_RED = 1
private const val MENU_COLOUR_GREEN = 2
private const val MENU_COLOUR_BLUE = 3

private const val MENU_SIZE_22 = 4
private const val MENU_SIZE_26 = 5
private const val MENU_SIZE_30 = 6
*/


class MainActivity : AppCompatActivity()/*, View.OnClickListener*/
        /*, SeekBar.OnSeekBarChangeListener*/ {
private val MENU_ALPHA_ID = 1
private val MENU_SCALE_ID = 2
private val MENU_TRANSLATE_ID = 3
private val MENU_ROTATE_ID = 4
private val MENU_COMBO_ID = 5
    lateinit var tv: TextView


    /*
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
    */
    /*
    lateinit var llMain: LinearLayout
    lateinit var rgGravity: RadioGroup
    lateinit var etName: EditText
    lateinit var btnCreate: Button
    lateinit var btnClear: Button
    var i_id = 0
*/
/*
    lateinit var sbWeight: SeekBar
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var lParams1: LinearLayout.LayoutParams
    lateinit var lParams2: LinearLayout.LayoutParams
*/
    /*
    private val MENU_QUIT_ID = 1
    private val MENU_RESET_ID = 2

    lateinit var etNum1: EditText
    lateinit var etNum2: EditText
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var tvResult: TextView
    private lateinit var oper: String
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*useLayout(R.layout.l015_context_menu)*/
        //createL016(this)
/*
        setContentView(R.layout.l017_create_delete_dynamic_elements)
        llMain = findViewById(R.id.llMain)
        rgGravity = findViewById(R.id.grGravity)
        etName = findViewById(R.id.etName)
        btnCreate = findViewById(R.id.btnCreate)
        btnClear = findViewById(R.id.btnClear)

        btnCreate.setOnClickListener(this)
        btnClear.setOnClickListener(this)
*/
/*
        setContentView(R.layout.l018_dynamic_weight)

        sbWeight = findViewById(R.id.sbWeight)
        sbWeight.setOnSeekBarChangeListener(this)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        lParams1 = button1.layoutParams as LinearLayout.LayoutParams
        lParams2 = button2.layoutParams as LinearLayout.LayoutParams
*/
/*
        setContentView(R.layout.l019_calculator)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        tvResult = findViewById(R.id.result)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
*/
        setContentView(R.layout.l020_component_animation)

        tv = findViewById(R.id.tv)

        registerForContextMenu(tv)
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        checkBox2.setOnCheckedChangeListener { _, _ ->
            invalidateOptionsMenu() }
        menu?.add(2, 4, 4, "item4")?.isCheckable = true
        return true
    }
*/
/*
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
/*
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
*/
/*
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
*/
/*
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_COLOUR_RED -> {
                textView1.setTextColor(Color.RED)
                textView1.text = "Text colour = red"
            }
            MENU_COLOUR_GREEN -> {
                textView1.setTextColor(Color.GREEN)
                textView1.text = "Text colour = green"
            }
            MENU_COLOUR_BLUE -> {
                textView1.setTextColor(Color.BLUE)
                textView1.text = "Text colour = blue"
            }
            MENU_SIZE_22 -> {
                textView2.textSize = 22F
                textView2.text = "Text size = 22"
            }
            MENU_SIZE_26 -> {
                textView2.textSize = 26F
                textView2.text = "Text size = 26"
            }
            MENU_SIZE_30 -> {
                textView2.textSize = 30F
                textView2.text = "Text size = 30"
            }
        }

        return super.onContextItemSelected(item)
    }
*/
/*
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_mail)?.isVisible = checkBox2.isChecked

        menu?.setGroupVisible(R.id.group1, checkBox1.isChecked)

        return super.onPrepareOptionsMenu(menu)
    }
    */
/*
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreate -> {
                val lParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var btnGravity = Gravity.START

                when (rgGravity.checkedRadioButtonId) {
                    R.id.rbLeft -> {
                        btnGravity = Gravity.START
                    }
                    R.id.rbCenter -> {
                        btnGravity = Gravity.CENTER
                    }
                    R.id.rbRight -> {
                        btnGravity = Gravity.END
                    }
                }

                lParams.gravity = btnGravity
                val btnNew = Button(this)
                btnNew.text = etName.text.toString()
                btnNew.id = ++i_id
                llMain.addView(btnNew, lParams)
                btnNew.setAllCaps(false)
                btnNew.setOnClickListener(this)
            }
            R.id.btnClear -> {
                llMain.removeAllViews()
                i_id = 0
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                if (v?.id != null) {
                    Toast.makeText(this,
                            "You pressed on ${v.id} button",
                            Toast.LENGTH_SHORT)
                            .show()
                    findViewById<Button>(v.id).setBackgroundColor(Color.BLACK)
                    findViewById<Button>(v.id).setTextColor(Color.WHITE)
                }
            }
        }
    }
*/
    /*
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val value = seekBar?.max!! - progress

        lParams1.weight = progress.toFloat()
        lParams2.weight = value.toFloat()

        button1.text = progress.toString()
        button2.text = value.toString()
        button1.layoutParams = lParams2
        button2.layoutParams = lParams1
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
    */
/*
    override fun onClick(v: View?) {
        var num1 = 0F
        var num2 = 0F
        var result = 0F

        num1 = etNum1.text.toString().toFloat()
        num2 = etNum2.text.toString().toFloat()
        when (v?.id) {
            R.id.button1 -> {
                oper = "+"
                result = num1 + num2
            }
            R.id.button2 -> {
                oper = "-"
                result = num1 - num2
            }
            R.id.button3 -> {
                oper = "*"
                result = num1 * num2
            }
            R.id.button4 -> {
                oper = "/"
                result = num1 / num2
            }
        }
        if (oper == "/" && num2 == 0F) {
            tvResult.text = "На ноль делить нельзя!"
        } else {
            tvResult.text = "$num1 $oper $num2 = $result"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, MENU_RESET_ID, 0, "Reset")
        menu?.add(0, MENU_QUIT_ID, 0, "Quit")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_RESET_ID -> {
                etNum1.setText("")
                etNum2.setText("")
                tvResult.text = ""
            }
            MENU_QUIT_ID -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
    */

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        when (v?.id) {
            R.id.tv -> {
                menu?.add(0, MENU_ALPHA_ID, 0, "alpha")
                menu?.add(0, MENU_SCALE_ID, 0, "scale")
                menu?.add(0, MENU_TRANSLATE_ID, 0, "translate")
                menu?.add(0, MENU_ROTATE_ID, 0, "rotate")
                menu?.add(0, MENU_COMBO_ID, 0, "combo")
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        lateinit var anim: Animation
        when (item?.itemId) {
            MENU_ALPHA_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myalpha)
            MENU_SCALE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myscale)
            MENU_TRANSLATE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.mytrans)
            MENU_ROTATE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myrotate)
            MENU_COMBO_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.mycombo)
        }
        tv.startAnimation(anim)
        return super.onContextItemSelected(item)
    }
}
/*
private fun Toast.centerGravitation(): Toast {
    this.setGravity(Gravity.CENTER, 0, 0)
    return this
}
*/
/*
private fun createL016(activity: AppCompatActivity){
    val linearLayout = LinearLayout(activity)
    linearLayout.orientation = LinearLayout.VERTICAL
    val linLayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    activity.setContentView(linearLayout, linLayoutParams)

    val lpView = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
    )

    val textView = TextView(activity)
    textView.text = activity.getString(R.string.text)
    textView.layoutParams = lpView
    linearLayout.addView(textView)

    val leftMarginParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    leftMarginParams.leftMargin = 50

    val button = Button(activity)
    button.text = activity.getString(R.string.button)
    linearLayout.addView(button, leftMarginParams)

    val gravityParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
    )
    gravityParams.gravity = Gravity.CENTER

    val button1 = Button(activity)
    button1.text = activity.getString(R.string.button1)
    linearLayout.addView(button1, gravityParams)
}
*/
