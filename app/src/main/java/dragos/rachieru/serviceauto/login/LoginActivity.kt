package dragos.rachieru.serviceauto.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import dragos.rachieru.serviceauto.BaseActivity
import dragos.rachieru.serviceauto.R
import dragos.rachieru.serviceauto.extensions.isLoggedIn
import dragos.rachieru.serviceauto.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */

class LoginActivity : BaseActivity(), View.OnClickListener {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener(this)
        presenter = LoginPresenter(this, api)
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

    override fun onPause() {
        super.onPause()
        presenter.dispose()
        hideProgress()
    }

    fun onLoggedIn(t: Boolean) {
        if (t) {
            isLoggedIn = t
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.bad_credentials, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }
}