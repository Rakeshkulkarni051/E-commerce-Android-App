package com.ababilexpress.saiconsult.Adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ababilexpress.saiconsult.Adapters.Categories_adap.MyViewHolder
import com.ababilexpress.saiconsult.Data.Products.Data_products
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.pages.ProductPage
import com.squareup.picasso.Picasso

class Productsnew_adap(val context:Activity,val newproList:List<Data_products>):RecyclerView.Adapter<Productsnew_adap.Myviewholder>() {
    class Myviewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val product_name:TextView=itemView.findViewById(R.id.pro_name)
            val product_price:TextView=itemView.findViewById(R.id.pro_price)
            val product_img:ImageView=itemView.findViewById(R.id.newprod_img)
            val product_layout:LinearLayout=itemView.findViewById(R.id.product_layout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_newprod, parent, false)
        return Myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        val currentData = newproList[position]

        holder.product_name.text=currentData.name
        holder.product_price.text=currentData.prices.regular.formatted_price

        holder.product_layout.setOnClickListener {

            val intent=Intent(context,ProductPage::class.java)
            intent.putExtra("productID",currentData.id)
            intent.putExtra("productName",currentData.name)
            intent.putExtra("Description",currentData.description)
            intent.putExtra("Avgratings",currentData.avg_ratings)
            intent.putExtra("cartImage",currentData.base_image.medium_image_url)
            intent.putExtra("price", currentData.prices.regular.formatted_price)


            // Pass the list of image URLs
            val imageUrls = currentData.images.map { it.original_image_url }.toTypedArray()
            intent.putExtra("imageUrls", imageUrls)



            context.startActivity(intent)
        }


        currentData.base_image.medium_image_url.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.product_img)
        }
    }

    override fun getItemCount(): Int {
       return newproList.size
    }
}