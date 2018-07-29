package br.com.petshelter.petshelter.models

import android.content.Context
import br.com.petshelter.petshelter.R
import java.util.*

data class Animal(var name: String = "",
                  var species: Species = Species.DOG,
                  var sex: Sex = Sex.MALE,
                  var size: Size = Size.SMALL,
                  var ageMonths: Int = 0,
                  var userId: String = "",
                  var createDate: Date = Date()) {
    enum class Species {
        CAT, DOG;

        fun getPrettyString(context: Context): String =
                when(this) {
                    CAT -> context.getString(R.string.cat)
                    DOG -> context.getString(R.string.dog)
                }
    }

    enum class Sex {
        MALE, FEMALE;

        fun getPrettyString(context: Context): String =
                when(this) {
                    MALE -> context.getString(R.string.animal_male)
                    FEMALE -> context.getString(R.string.animal_female)
                }
    }

    enum class Size {
        SMALL, MEDIUM, LARGE;

        fun getPrettyString(context: Context): String =
                when(this) {
                    SMALL -> context.getString(R.string.small)
                    MEDIUM -> context.getString(R.string.medium)
                    LARGE -> context.getString(R.string.large)
                }
    }
}