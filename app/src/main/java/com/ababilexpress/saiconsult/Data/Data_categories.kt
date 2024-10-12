package com.ababilexpress.saiconsult.Data

data class Data_categories(
    val additional: List<Any>,
    val description: String,
    val display_mode: String,
    val id: Int,
    val images: Images,
    val meta: Meta,
    val name: String,
    val parent_id: String,
    val position: String,
    val slug: String,
    val status: String,
    val translations: List<Translation>
)