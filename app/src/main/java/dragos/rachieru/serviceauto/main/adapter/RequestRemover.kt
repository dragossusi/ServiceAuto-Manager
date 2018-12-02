package dragos.rachieru.serviceauto.main.adapter

import dragos.rachieru.serviceauto.entities.enums.RequestStatus

/**
 * ServiceAuto by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface RequestRemover {
    fun removeAt(id: String, status: RequestStatus, position: Int)
}