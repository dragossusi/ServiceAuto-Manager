package dragos.rachieru.serviceauto.login

import dragos.rachieru.serviceauto.api.ServiceAutoManagerApi
import dragos.rachieru.serviceauto.entities.LoginBody
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class LoginPresenter(
    val loginActivity: LoginActivity,
    private val api: ServiceAutoManagerApi
) {

    private var disposable: Disposable? = null


    fun login(email: String, password: String) {
        api.login(LoginBody(email, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Boolean> {
                override fun onSuccess(t: Boolean) {
                    loginActivity.onLoggedIn(t)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    loginActivity.onError(e)
                }
            })
    }

    fun dispose() {
        disposable?.dispose()
    }

}