package br.com.petshelter.petshelter.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import br.com.petshelter.petshelter.R
import br.com.petshelter.petshelter.utils.signOut
import kotlinx.android.synthetic.main.activity_signed_in.*
import kotlinx.android.synthetic.main.content_signed_in.*
import org.jetbrains.anko.startActivity

class SignedInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed_in)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        sign_out_button.setOnClickListener {
            signOut {
                startActivity<MainActivity>()
                finish()
            }
        }
    }
}
