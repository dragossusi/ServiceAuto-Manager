package dragos.rachieru.serviceauto.main

import dragos.rachieru.serviceauto.api.ServiceAutoManagerApi
import dragos.rachieru.serviceauto.entities.IssueRequest
import dragos.rachieru.serviceauto.entities.enums.RequestStatus
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
class MainPresenter(
    private val viewDelegate: MainView,
    private val api: ServiceAutoManagerApi
) {

    private val compositeDisposable = CompositeDisposable()

    fun getRequests(status: RequestStatus? = null) {
        viewDelegate.showProgress()
        api.getRequests(status?.name?.toLowerCase())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MutableList<IssueRequest>> {
                override fun onSuccess(t: MutableList<IssueRequest>) {
                    viewDelegate.hideProgress()
                    viewDelegate.onRequests(t)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    viewDelegate.hideProgress()
                    viewDelegate.onError(e)
                }
            })
    }

    fun dispose() {
        compositeDisposable.clear()
    }

    fun setStatus(issue: IssueRequest, position: Int) {
        viewDelegate.showProgress()
        api.setStatus(issue.id, issue)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    viewDelegate.hideProgress()
                    viewDelegate.onStatusChanged(position)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    viewDelegate.hideProgress()
                    viewDelegate.onError(e)
                }
            })
    }

}