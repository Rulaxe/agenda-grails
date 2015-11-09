package agenda

import grails.transaction.Transactional

@Transactional
class TelefonoService {

    def saveTelefono(Telefono telefono) {

        if (telefono == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (telefono.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond telefono.errors, view:'create'
            return
        }

        telefono.save flush:true
    }

    def updateTelefono(Telefono telefono) {

        if (telefono == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (telefono.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond telefono.errors, view:'edit'
            return
        }

        telefono.save flush:true
    }

    def deleteTelefono(Telefono telefono) {

        if (telefono == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        telefono.delete flush:true
    }
}
