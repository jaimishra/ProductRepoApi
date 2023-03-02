package com.example.demo.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class ItemResponse(
      val id: Int?,
      val title: String?,
      @Embedded
      val rating: Rating,
      val image: String?,
      val category: String?,
      val description: String?,
      val price: Double?,
) {
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId: Int = 0
}

data class Rating(
    val count: Int?,
    val rate: Double?
)
