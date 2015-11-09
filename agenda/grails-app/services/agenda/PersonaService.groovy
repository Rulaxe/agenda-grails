package agenda

import grails.transaction.Transactional

@Transactional
class PersonaService {

    @Transactional
    def savePersona(Persona persona) {
        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (persona.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond persona.errors, view:'create'
            return
        }

        persona.save flush:true

    }

    @Transactional
    def updatePersona(Persona persona) {
        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (persona.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond persona.errors, view:'edit'
            return
        }

        persona.save flush:true
    }

    @Transactional
    def deletePersona(Persona persona){
        if (persona == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        persona.delete flush:true
    }
}
