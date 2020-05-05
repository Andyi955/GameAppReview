package org.wit.gameapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameModel(var id: Long = 0,
                          var image: String = "",
                          var title: String = "",
                          var genre: String="",
                          var comment: String = "") : Parcelable


