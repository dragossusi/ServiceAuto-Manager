package dragos.rachieru.serviceauto.login

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dragos.rachieru.serviceauto.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(this)
        presenter = LoginPresenter(this)
    }

    override fun onClick(p0: View) {
        val email = edit_email.text.toString()
        val password = edit_password.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            Toast.makeText(this, R.string.bad_email, Toast.LENGTH_SHORT).show()
        else if (email.isEmpty() || password.isEmpty())
            Toast.makeText(this, R.string.complete_all_fields, Toast.LENGTH_SHORT).show()
        else if (password.length < 6)
            Toast.makeText(this, R.string.short_password, Toast.LENGTH_SHORT).show()
        else presenter.login(email, password)
    }

}