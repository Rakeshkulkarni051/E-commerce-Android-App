package com.ababilexpress.saiconsult.Data.Products

data class Data_products(
    val avg_ratings: Int,
    val base_image: BaseImage,
    val description: String,
    val id: Int,
    val images: List<Image>,
    val is_featured: Boolean,
    val is_new: Boolean,
    val is_saleable: Boolean,
    val is_wishlist: Boolean,
    val min_price: String,
    val name: String,
    val on_sale: Boolean,
    val price_html: String,
    val prices: Prices,
    val sku: String,
    val url_key: String
)