package agenda

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TelefonoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def telefonoService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Telefono.list(params), model:[telefonoCount: Telefono.count()]
    }

    def show(Telefono telefono) {
        respond telefono
    }

    def create() {
        respond new Telefono(params)
    }

    @Transactional
    def save(Telefono telefono) {

        telefonoService.saveTelefono(telefono)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])
                redirect telefono
            }
            '*' { respond telefono, [status: CREATED] }
        }
    }

    def edit(Telefono telefono) {
        respond telefono
    }

    @Transactional
    def update(Telefono telefono) {

        telefonoService.updateTelefono(telefono)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])
                redirect telefono
            }
            '*'{ respond telefono, [status: OK] }
        }
    }

    @Transactional
    def delete(Telefono telefono) {

        telefonoService.deleteTelefono(telefono)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'telefono.label', default: 'Telefono'), telefono.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'telefono.label', default: 'Telefono'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
