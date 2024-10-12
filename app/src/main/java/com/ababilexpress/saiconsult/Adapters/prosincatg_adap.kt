package com.ababilexpress.saiconsult.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ababilexpress.saiconsult.Data.Products.Data_products
import com.ababilexpress.saiconsult.R
import com.squareup.picasso.Picasso


//Creating Dedicated Adapter for Products in categories redirecting from home page so that i can implement filters in query

class prosincatg_adap(val context: Activity, val catproList:List<Data_products>): RecyclerView.Adapter<prosincatg_adap.Myviewholder>() {
    class Myviewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val product_name: TextView =itemView.findViewById(R.id.catpro_name)
        val product_price: TextView =itemView.findViewById(R.id.catpro_price)
        val product_img: ImageView =itemView.findViewById(R.id.catprod_img)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Myviewholder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_prosincatg, parent, false)
        return Myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
       val currentdata=catproList[position]

        holder.product_name.text=currentdata.name
        holder.product_price.text=currentdata.min_price

        currentdata.base_image.medium_image_url.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.product_img)
        }
    }


    override fun getItemCount(): Int {
       return catproList.size
    }

}