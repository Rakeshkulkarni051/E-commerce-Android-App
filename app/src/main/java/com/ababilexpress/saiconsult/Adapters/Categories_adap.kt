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
import com.ababilexpress.saiconsult.Data.Data_categories
import com.ababilexpress.saiconsult.R
import com.ababilexpress.saiconsult.pages.CategoriesProd
import com.squareup.picasso.Picasso

class Categories_adap(val context:Activity,val categList:List<Data_categories>):RecyclerView.Adapter<Categories_adap.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val categorieimg: ImageView = itemView.findViewById(R.id.cate_img)
        val categoriesname:TextView = itemView.findViewById(R.id.cate_name)
        val cate_layout:LinearLayout=itemView.findViewById(R.id.categoryLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_categories, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = categList[position]

        holder.categoriesname.text=currentData.name

        currentData.images.logo_url.let { imageUrl ->
            Picasso.get().load(imageUrl).into(holder.categorieimg)
        }

        holder.cate_layout.setOnClickListener {
            val intent=Intent(context, CategoriesProd::class.java)
            intent.putExtra("category_id",currentData.id)
            intent.putExtra("category_name",currentData.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categList.size
    }
}
