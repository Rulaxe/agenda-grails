package agenda

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def personaService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Persona.list(params), model:[personaCount: Persona.count()]
    }

    def show(Persona persona) {
        respond persona
    }

    def create() {
        respond new Persona(params)
    }

    @Transactional
    def save(Persona persona) {
        personaService.savePersona(persona)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*' { respond persona, [status: CREATED] }
        }
    }

    def edit(Persona persona) {
        respond persona
    }

    @Transactional
    def update(Persona persona) {
        personaService.updatePersona(persona)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*'{ respond persona, [status: OK] }
        }
    }

    @Transactional
    def delete(Persona persona) {
        personaService.deletePersona(persona)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }

    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
