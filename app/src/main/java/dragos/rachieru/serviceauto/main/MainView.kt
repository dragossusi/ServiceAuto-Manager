package dragos.rachieru.serviceauto.main

import dragos.rachieru.serviceauto.IBaseView
import dragos.rachieru.serviceauto.entities.IssueRequest

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface MainView : IBaseView {
    fun onRequests(t: MutableList<IssueRequest>)
    fun onStatusChanged(position: Int)
}