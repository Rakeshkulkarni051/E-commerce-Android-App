package com.ababilexpress.saiconsult.Data

data class Translation(
    val category_id: String,
    val description: String,
    val id: Int,
    val locale: String,
    val locale_id: String,
    val meta_description: String,
    val meta_keywords: String,
    val meta_title: String,
    val name: String,
    val slug: String,
    val url_path: String
)