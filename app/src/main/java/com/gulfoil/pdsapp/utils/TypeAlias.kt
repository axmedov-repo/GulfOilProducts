package com.gulfoil.pdsapp.utils

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

typealias TripleMap<K, V, C> = java.util.HashMap<K, HashMap<V, C>>

fun TripleMap<Int, Int, Int>.getItem(i1: Int, i2: Int): Int? {
    return this[i1]?.get(i2)
}

