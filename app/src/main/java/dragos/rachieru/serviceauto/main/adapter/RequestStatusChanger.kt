package dragos.rachieru.serviceauto.main.adapter

import dragos.rachieru.serviceauto.entities.IssueRequest

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface RequestStatusChanger {
    fun changeAt(issue: IssueRequest, position: Int)
}