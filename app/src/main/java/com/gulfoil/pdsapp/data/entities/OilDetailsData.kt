package com.gulfoil.pdsapp.data.entities

import androidx.annotation.Keep

@Keep
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
