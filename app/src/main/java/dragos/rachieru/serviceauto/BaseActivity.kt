package dragos.rachieru.serviceauto

import androidx.appcompat.app.AppCompatActivity
import dragos.rachieru.serviceauto.api.ServiceAutoManagerApi
import dragos.rachieru.serviceauto.extensions.toast

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    val api: ServiceAutoManagerApi
        get() = (application as ServiceAutoApp).api

    override fun onError(t: Throwable) {
        t.printStackTrace()
        t.message?.let(::toast) ?: toast(R.string.error_occured)
    }
}