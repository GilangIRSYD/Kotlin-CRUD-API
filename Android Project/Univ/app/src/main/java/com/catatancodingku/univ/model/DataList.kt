package com.catatancodingku.univ.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class DataList (

    val id : String? = null,
    val name : String? = null,
    val noHp : String? = null,
    val address : String? = null
) : Parcelable