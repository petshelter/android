package br.com.petshelter.petshelter.utils

import android.content.Context
import android.widget.ArrayAdapter

fun generateStringArrayAdapter(stringList: List<String>, context: Context) =
        ArrayAdapter(context,
                android.R.layout.simple_spinner_item,
                stringList)
                .apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }