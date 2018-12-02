package dragos.rachieru.serviceauto.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */

const val LOGIN_PREF = "login-preferences"
const val BUNDLE_LOGGED_IN = "logged-in"

var Context.isLoggedIn: Boolean
    get() = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE).getBoolean(BUNDLE_LOGGED_IN, false)
    set(value) = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE).edit().putBoolean(
        BUNDLE_LOGGED_IN,
        value
    ).apply()

fun Context.toast(@StringRes stringRes: Int) = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()