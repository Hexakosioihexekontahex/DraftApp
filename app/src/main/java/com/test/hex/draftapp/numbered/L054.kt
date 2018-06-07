package com.test.hex.draftapp.numbered

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.test.hex.draftapp.R

class L054 : AppCompatActivity() {

    private lateinit var basketAdapter: BasketAdapter
    private lateinit var products: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l054)

        fillData()
        basketAdapter = BasketAdapter(this, products)
        findViewById<ListView>(R.id.lvMain).adapter = basketAdapter
        findViewById<Button>(R.id.showResult).setOnClickListener {
            val sb = StringBuilder("Products in basket:")
            var total = 0
            for (p in basketAdapter.getBasket()) {
                if (p.basket) {
                    sb.append("\n${p.describe}")
                    total += p.price
                }
            }
            sb.append("\nTotal: $total")
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun fillData(){
        products = mutableListOf()
        for (i in 1..20){
            var _price = (Math.random() * 1000).toInt()
            if (_price < 0) _price *= -1
            products.add(Product("Product $i", _price,
                    android.R.drawable.ic_menu_compass, false))
        }
    }
}

data class Product(val describe: String, val price: Int, val
image: Int, var basket: Boolean)

class BasketAdapter(context: Context, products: MutableList<Product>) : BaseAdapter() {
    private var ctx: Context = context
    private var lInflater: LayoutInflater =
            ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var objects: MutableList<Product> = products

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = lInflater.inflate(R.layout.l054_item, parent, false)
        }
        val p: Product = getProduct(position)

        view?.findViewById<TextView>(R.id.tvDescr)?.text = p.describe
        view?.findViewById<TextView>(R.id.tvPrice)?.text = "${p.price}"
        view?.findViewById<ImageView>(R.id.ivImage)?.setImageResource(p.image)

        view?.findViewById<CheckBox>(R.id.cbBasket)?.apply {
            setOnCheckedChangeListener(myCheckChangeListener)
            tag = position
            isChecked = p.basket
        }
        return view!!
    }

    override fun getItem(position: Int): Any = objects[position]

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int = objects.size

    private fun getProduct(position: Int) = getItem(position) as Product

    fun getBasket(): MutableList<Product> {
        val basket = mutableListOf<Product>()
        for (p in objects) {
            if (p.basket) {
                basket.add(p)
            }
        }
        return basket
    }

    private var myCheckChangeListener = CompoundButton.OnCheckedChangeListener {
        buttonView, isChecked ->
        getProduct(buttonView.tag as Int).basket = isChecked
    }
}
