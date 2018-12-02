package dragos.rachieru.serviceauto

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface IBaseView {
    fun onError(t:Throwable)
    fun showProgress()
    fun hideProgress()
}