package br.com.petshelter.petshelter.activities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import br.com.petshelter.petshelter.R
import br.com.petshelter.petshelter.models.Animal
import br.com.petshelter.petshelter.utils.generateStringArrayAdapter
import com.github.ybq.android.spinkit.style.ThreeBounce
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_animal_register.*
import org.jetbrains.anko.toast

class AnimalRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_register)

        species_spinner.adapter = generateStringArrayAdapter(
                Animal.Species.values().map { it.getPrettyString(this) },
                this)

        sex_spinner.adapter = generateStringArrayAdapter(
                Animal.Sex.values().map { it.getPrettyString(this) },
                this)

        animal_size_spinner.adapter = generateStringArrayAdapter(
                Animal.Size.values().map { it.getPrettyString(this) },
                this)

        register_animal_button.setOnClickListener {
            if (TextUtils.isEmpty(age_months_inputText.text)) {
                age_months_inputText.error = this.getString(R.string.age_required)
                return@setOnClickListener
            }

            val ageMonths: Int
            try {
                ageMonths = age_months_inputText.text.toString().toInt()
            } catch (exception: NumberFormatException) {
                age_months_inputText.error = this.getString(R.string.invalid_format)
                return@setOnClickListener
            }

            setButtonToLoadingState()
            age_months_inputText.error = null
            val animal = Animal(name_inputText.text.toString(),
                    Animal.Species.values()[species_spinner.selectedItemPosition],
                    Animal.Sex.values()[sex_spinner.selectedItemPosition],
                    Animal.Size.values()[animal_size_spinner.selectedItemPosition],
                    ageMonths,
                    FirebaseAuth.getInstance().currentUser?.uid ?: "")

            FirebaseFirestore.getInstance().collection("animals").add(animal)
                    .addOnSuccessListener { documentReference ->
                        toast(resources.getString(R.string.animal_register_success))
                        setButtonToIdleState()
                        Log.d("ANIMAL", "DocumentSnapshot added with ID: " + documentReference.id)
                    }
                    .addOnFailureListener { e ->
                        toast(resources.getString(R.string.animal_register_fail))
                        setButtonToIdleState()
                        Log.w("ANIMAL", "Error adding document", e)
                    }
        }
    }

    private fun setButtonToLoadingState() {
        val threeBounceDrawable = ThreeBounce().apply {
            setBounds(0, 0, 100, 100)
            color = ContextCompat.getColor(this@AnimalRegisterActivity, R.color.colorAccent)
        }
        register_animal_button.setCompoundDrawables(threeBounceDrawable, null, null, null)
        register_animal_button.text = resources.getString(R.string.registering)
        threeBounceDrawable.start()
    }

    private fun setButtonToIdleState() {
        register_animal_button.setCompoundDrawables(null, null, null, null)
        register_animal_button.text = resources.getString(R.string.register)
    }
}