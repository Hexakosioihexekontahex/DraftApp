package com.test.hex.draftapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import com.mikepenz.materialdrawer.DrawerBuilder
import com.test.hex.draftapp.aggregate.operations.logAllAggregateOperations
import com.test.hex.draftapp.numbered.*
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {
    lateinit var gvMain: GridView
    private lateinit var aAdapter: ArrayAdapter<String>
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.l000)

        DrawerBuilder().apply {
            withActivity(this@MainActivity)
            withActionBarDrawerToggleAnimated(true)
            withSliderBackgroundColor(Color.GRAY)
        }.build()

        val data: MutableList<String> = mutableListOf("004", "005", "6_layout", "6_linear", "6_table",
                "7_gravity", "7_margin", "7_weight")
        for (i in 8..129) {//TODO inc second
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
            remove("091")
            remove("107")//same as 014
            remove("117")//widget
            remove("118")//widget
            remove("120")//widget
            remove("121")//widget
            remove("122")//widget
            remove("123")//sign apk
            remove("124")//package
            //TODO if need skip something
        }
        data.reverse()

        val coordinator = find<View>(R.id.content)

        find<FloatingActionButton>(R.id.fabButton).apply {
            setOnClickListener {
                Snackbar.make(coordinator, "Press long to bring up the last lesson",
                        Snackbar.LENGTH_SHORT).show()
            }

            setOnLongClickListener {
                    clickGridElem(TextView(this@MainActivity).apply { text = data[0] })
                    logAllAggregateOperations()
                true
            }
        }

        aAdapter = ArrayAdapter(this, R.layout.l000_item, R.id.tvText,
                data.toTypedArray())
        gvMain = find(R.id.gvMain)
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
            "004" -> { startActivity<L004ActivityMain>() }
            "005" -> { startActivity<L005Myscreen>() }
            "6_layout" -> { startActivity<L006Layout>() }
            "6_linear" -> { startActivity<L006LinearInLinear>() }
            "6_relative" -> { startActivity<L006RelativeLayout>() }
            "6_table" -> { startActivity<L006TableLayout>() }
            "7_gravity" -> { startActivity<L007LayoutGravity>() }
            "7_margin" -> { startActivity<L007LayoutMargin>() }
            "7_weight" -> { startActivity<L007LayoutWeight>() }
            "008" -> { startActivity<L008ViewById>() }
            "009" -> { startActivity<L009ClickButton>() }
            "010" -> { startActivity<L010ClickButtons>() }
            "011" -> { startActivity<L011Resources>() }
            "014" -> { startActivity<L014Menu>() }
            "015" -> { startActivity<L015ContextMenu>() }
            "016" -> { startActivity<L016>() }
            "017" -> { startActivity<L017CreateDeleteDynamicElements>() }
            "018" -> { startActivity<L018DynamicWeight>() }
            "019" -> { startActivity<L019Calculator>() }
            "020" -> { startActivity<L020ComponentAnimation>() }
            "023" -> { startActivity<L023>() }
            "026" -> { startActivity<L026>() }
            "027" -> { startActivity<L027>() }
            "028" -> { startActivity<L028>() }
            "029" -> { startActivity<L029>() }
            "030" -> { startActivity<L030>() }
            "031" -> { startActivity<L031>() }
            "032" -> { startActivity<L032>() }
            "033" -> { startActivity<L033>() }
            "034" -> { startActivity<L034>() }
            "036" -> { startActivity<L036>() }
            "037" -> { startActivity<L037>() }
            "038" -> { startActivity<L038>() }
            "039" -> { startActivity<L039>() }
            "040" -> { startActivity<L040>() }
            "041" -> { startActivity<L041>() }
            "042" -> { startActivity<L042>() }
            "043" -> { startActivity<L043>() }
            "044" -> { startActivity<L044>() }
            "045" -> { startActivity<L045>() }
            "046" -> { startActivity<L046>() }
            "048" -> { startActivity<L048>() }
            "049" -> { startActivity<L049>() }
            "050" -> { startActivity<L050>() }
            "051" -> { startActivity<L051>() }
            "052" -> { startActivity<L052>() }
            "053" -> { startActivity<L053>() }
            "054" -> { startActivity<L054>() }
            "055" -> { startActivity<L055>() }
            "056" -> { startActivity<L056>() }
            "057" -> { startActivity<L057>() }
            "058" -> { startActivity<L058>() }
            "059" -> { startActivity<L059>() }
            "060" -> { startActivity<L060>() }
            "061" -> { startActivity<L061>() }
            "062" -> { startActivity<L062>() }
            "063" -> { startActivity<L063>() }
            "064" -> { startActivity<L064>() }
            "065" -> { startActivity<L065>() }
            "066" -> { startActivity<L066>() }
            "067" -> { startActivity<L067>() }
            "068" -> { startActivity<L068>() }
            "069" -> { startActivity<L069>() }
            "070" -> { startActivity<L070>() }
            "071" -> { startActivity<L071>() }
            "072" -> { startActivity<L072>() }
            "073" -> { startActivity<L073>() }
            "074" -> { startActivity<L074>() }
            "075" -> { startActivity<L075>() }
            "076" -> { startActivity<L076>() }
            "077" -> { startActivity<L077>() }
            "078" -> { startActivity<L078>() }
            "079" -> { startActivity<L079>() }
            "080" -> { startActivity<L080>() }
            "081" -> { startActivity<L081>() }
            "082" -> { startActivity<L082>() }
            "083" -> { startActivity<L083>() }
            "084" -> { startActivity<L084>() }
            "085" -> { startActivity<L085>() }
            "086" -> { startActivity<L086>() }
            "087" -> { startActivity<L087>() }
            "088" -> { startActivity<L088>() }
            "089" -> { startActivity<L089>() }
            "090" -> { startActivity<L090>() }
/*            "091" -> {
                  intention = Intent(this, L091::class.java)
                  startActivity(intention)
              }
*/
            "092" -> { startActivity<L092>() }
            "093" -> { startActivity<L093>() }
            "094" -> { startActivity<L094>() }
            "095" -> { startActivity<L095>() }
            "096" -> { startActivity<L096>() }
            "097" -> { startActivity<L097>() }
            "098" -> { startActivity<L098>() }
            "099" -> { startActivity<L099>() }
            "100" -> { startActivity<L100>() }
            "101" -> { startActivity<L101>() }
            "102" -> { startActivity<L102>() }
            "103" -> { startActivity<L103>() }
            "104" -> { startActivity<L104>() }
            "105" -> { startActivity<L105>() }
            "106" -> { startActivity<L106>() }
            "108" -> { startActivity<L108>() }
            "109" -> { startActivity<L109>() }
            "110" -> { startActivity<L110>() }
            "111" -> { startActivity<L111>() }
            "112" -> { startActivity<L112>() }
            "113" -> { startActivity<L113>() }
            "114" -> { startActivity<L114>() }
            "115" -> { startActivity<L115>() }
            "116" -> { startActivity<L116A>()}
            "119" -> { startActivity<L119>() }
            "125" -> { startActivity<L125>() }
            "126" -> { startActivity<L126>() }
            "127" -> { startActivity<L127>() }
            "128" -> { startActivity<L128>() }
            "129" -> { startActivity<L129>() }
            //TODO add new one
            else -> Toast.makeText(this,
                    "Select exist activity name",
                    Toast.LENGTH_SHORT)
                    .show()
        }
    }
}
