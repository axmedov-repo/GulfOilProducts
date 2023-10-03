package com.axmedov.gulfapp.data.entities

data class OilDetailsData(
    val id: Int,
    val image: Int,
    val name: String,
    val header: String,
    val viscosityGradesList: List<ViscosityGradesData>,
    val availablePacksList: List<PacksData>,
    val description: String,
    val specification: String,
    val application: String,
    val benefits: String
)
