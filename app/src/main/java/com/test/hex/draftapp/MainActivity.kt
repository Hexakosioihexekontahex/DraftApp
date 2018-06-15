package com.test.hex.draftapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.test.hex.draftapp.numbered.*

class MainActivity : AppCompatActivity() {

    lateinit var gvMain: GridView
    private lateinit var aAdapter: ArrayAdapter<String>
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.l000)

        val data: MutableList<String> = mutableListOf("004", "005", "6_layout", "6_linear", "6_table",
                "7_gravity", "7_margin", "7_weight")
        for (i in 8..91) {//TODO inc second
            data.add("${if(i<100) "0" else ""}${if(i<10) "0" else ""}$i")
        }
        data.apply {
            remove("012")
            remove("013")
            remove("021")
            remove("022")
            remove("024")
            remove("025")
            remove("035")
            remove("047")
            //TODO if need skip something
        }
        data.reverse()

        aAdapter = ArrayAdapter(this, R.layout.l000_item, R.id.tvText,
                data.toTypedArray())
        gvMain = findViewById(R.id.gvMain)
        gvMain.adapter = aAdapter
        adjustGridView()
    }

    private fun adjustGridView(){
        gvMain.apply {
            numColumns = GridView.AUTO_FIT
            columnWidth = 120
            verticalSpacing = 5
            horizontalSpacing = 5
            stretchMode = GridView.STRETCH_SPACING_UNIFORM
        }
    }

    fun clickGridElem(view: View) {
        when ((view as? TextView)?.text) {
            "004" -> {
                intention = Intent(this, L004ActivityMain::class.java)
                startActivity(intention)
            }
            "005" -> {
                intention = Intent(this, L005Myscreen::class.java)
                startActivity(intention)
            }
            "6_layout" -> {
                intention = Intent(this, L006Layout::class.java)
                startActivity(intention)
            }
            "6_linear" -> {
                intention = Intent(this, L006LinearInLinear::class.java)
                startActivity(intention)
            }
            "6_relative" -> {
                intention = Intent(this, L006RelativeLayout::class.java)
                startActivity(intention)
            }
            "6_table" -> {
                intention = Intent(this, L006TableLayout::class.java)
                startActivity(intention)
            }
            "7_gravity" -> {
                intention = Intent(this, L007LayoutGravity::class.java)
                startActivity(intention)
            }
            "7_margin" -> {
                intention = Intent(this, L007LayoutMargin::class.java)
                startActivity(intention)
            }
            "7_weight" -> {
                intention = Intent(this, L007LayoutWeight::class.java)
                startActivity(intention)
            }
            "008" -> {
               intention = Intent(this, L008ViewById::class.java)
               startActivity(intention)
            }
            "009" -> {
                intention = Intent(this, L009ClickButton::class.java)
               startActivity(intention)
            }
            "010" -> {
               intention = Intent(this, L010ClickButtons::class.java)
               startActivity(intention)
            }
            "011" -> {
               intention = Intent(this, L011Resources::class.java)
               startActivity(intention)
            }
            "014" -> {
               intention = Intent(this, L014Menu::class.java)
               startActivity(intention)
            }
            "015" -> {
               intention = Intent(this, L015ContextMenu::class.java)
               startActivity(intention)
            }
            "016" -> {
               intention = Intent(this, L016::class.java)
               startActivity(intention)
            }
            "017" -> {
               intention = Intent(this, L017CreateDeleteDynamicElements::class.java)
               startActivity(intention)
            }
            "018" -> {
               intention = Intent(this, L018DynamicWeight::class.java)
               startActivity(intention)
            }
            "019" -> {
               intention = Intent(this, L019Calculator::class.java)
               startActivity(intention)
            }
            "020" -> {
               intention = Intent(this, L020ComponentAnimation::class.java)
               startActivity(intention)
            }
            "023" -> {
               intention = Intent(this, L023::class.java)
               startActivity(intention)
            }
            "026" -> {
               intention = Intent(this, L026::class.java)
               startActivity(intention)
            }
            "027" -> {
               intention = Intent(this, L027::class.java)
               startActivity(intention)
            }
            "028" -> {
               intention = Intent(this, L028::class.java)
               startActivity(intention)
            }
            "029" -> {
               intention = Intent(this, L029::class.java)
               startActivity(intention)
            }
            "030" -> {
               intention = Intent(this, L030::class.java)
               startActivity(intention)
            }
            "031" -> {
               intention = Intent(this, L031::class.java)
               startActivity(intention)
            }
            "032" -> {
               intention = Intent(this, L032::class.java)
               startActivity(intention)
            }
            "033" -> {
               intention = Intent(this, L033::class.java)
               startActivity(intention)
            }
            "034" -> {
               intention = Intent(this, L034::class.java)
               startActivity(intention)
            }
            "036" -> {
               intention = Intent(this, L036::class.java)
               startActivity(intention)
            }
            "037" -> {
               intention = Intent(this, L037::class.java)
               startActivity(intention)
            }
            "038" -> {
               intention = Intent(this, L038::class.java)
               startActivity(intention)
            }
            "039" -> {
               intention = Intent(this, L039::class.java)
               startActivity(intention)
            }
            "040" -> {
               intention = Intent(this, L040::class.java)
               startActivity(intention)
            }
            "041" -> {
               intention = Intent(this, L041::class.java)
               startActivity(intention)
            }
            "042" -> {
               intention = Intent(this, L042::class.java)
               startActivity(intention)
            }
            "043" -> {
               intention = Intent(this, L043::class.java)
               startActivity(intention)
            }
            "044" -> {
               intention = Intent(this, L044::class.java)
               startActivity(intention)
            }
            "045" -> {
               intention = Intent(this, L045::class.java)
               startActivity(intention)
            }
            "046" -> {
               intention = Intent(this, L046::class.java)
               startActivity(intention)
            }
            "048" -> {
               intention = Intent(this, L048::class.java)
               startActivity(intention)
            }
            "049" -> {
               intention = Intent(this, L049::class.java)
               startActivity(intention)
            }
            "050" -> {
               intention = Intent(this, L050::class.java)
               startActivity(intention)
            }
            "051" -> {
               intention = Intent(this, L051::class.java)
               startActivity(intention)
            }
            "052" -> {
               intention = Intent(this, L052::class.java)
               startActivity(intention)
            }
            "053" -> {
               intention = Intent(this, L053::class.java)
               startActivity(intention)
            }
            "054" -> {
               intention = Intent(this, L054::class.java)
               startActivity(intention)
            }
            "055" -> {
               intention = Intent(this, L055::class.java)
               startActivity(intention)
            }
            "056" -> {
               intention = Intent(this, L056::class.java)
               startActivity(intention)
            }
            "057" -> {
               intention = Intent(this, L057::class.java)
               startActivity(intention)
            }
            "058" -> {
               intention = Intent(this, L058::class.java)
               startActivity(intention)
            }
            "059" -> {
               intention = Intent(this, L059::class.java)
               startActivity(intention)
            }
            "060" -> {
               intention = Intent(this, L060::class.java)
               startActivity(intention)
            }
            "061" -> {
               intention = Intent(this, L061::class.java)
               startActivity(intention)
            }
            "062" -> {
                intention = Intent(this, L062::class.java)
                startActivity(intention)
            }
            "063" -> {
                intention = Intent(this, L063::class.java)
                startActivity(intention)
            }
            "064" -> {
                intention = Intent(this, L064::class.java)
                startActivity(intention)
            }
            "065" -> {
                intention = Intent(this, L065::class.java)
                startActivity(intention)
            }
            "066" -> {
                intention = Intent(this, L066::class.java)
                startActivity(intention)
            }
            "067" -> {
                intention = Intent(this, L067::class.java)
                startActivity(intention)
            }
            "068" -> {
                intention = Intent(this, L068::class.java)
                startActivity(intention)
            }
            "069" -> {
                intention = Intent(this, L069::class.java)
                startActivity(intention)
            }
            "070" -> {
                intention = Intent(this, L070::class.java)
                startActivity(intention)
            }
            "071" -> {
                intention = Intent(this, L071::class.java)
                startActivity(intention)
            }
            "072" -> {
                intention = Intent(this, L072::class.java)
                startActivity(intention)
            }
            "073" -> {
                intention = Intent(this, L073::class.java)
                startActivity(intention)
            }
            "074" -> {
                intention = Intent(this, L074::class.java)
                startActivity(intention)
            }
            "075" -> {
                intention = Intent(this, L075::class.java)
                startActivity(intention)
            }
            "076" -> {
                intention = Intent(this, L076::class.java)
                startActivity(intention)
            }
            "077" -> {
                intention = Intent(this, L077::class.java)
                startActivity(intention)
            }
            "078" -> {
                intention = Intent(this, L078::class.java)
                startActivity(intention)
            }
            "079" -> {
                intention = Intent(this, L079::class.java)
                startActivity(intention)
            }
            "080" -> {
                intention = Intent(this, L080::class.java)
                startActivity(intention)
            }
            "081" -> {
                intention = Intent(this, L081::class.java)
                startActivity(intention)
            }
            "082" -> {
                intention = Intent(this, L082::class.java)
                startActivity(intention)
            }
            "083" -> {
                intention = Intent(this, L083::class.java)
                startActivity(intention)
            }
            "084" -> {
                intention = Intent(this, L084::class.java)
                startActivity(intention)
            }
            "085" -> {
                intention = Intent(this, L085::class.java)
                startActivity(intention)
            }
            "086" -> {
                intention = Intent(this, L086::class.java)
                startActivity(intention)
            }
            "087" -> {
                intention = Intent(this, L087::class.java)
                startActivity(intention)
            }
            "088" -> {
                intention = Intent(this, L088::class.java)
                startActivity(intention)
            }
            "089" -> {
                intention = Intent(this, L089::class.java)
                startActivity(intention)
            }
            "090" -> {
                intention = Intent(this, L090::class.java)
                startActivity(intention)
            }
            "091" -> {
                intention = Intent(this, L091::class.java)
                startActivity(intention)
            }
            //TODO add new one
            else -> Toast.makeText(this,
                    "Select exist activity name",
                    Toast.LENGTH_SHORT)
                    .show()
        }
    }
}
