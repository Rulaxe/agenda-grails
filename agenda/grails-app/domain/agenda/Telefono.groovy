package agenda

class Telefono {
    static constraints = {
        numero(blank:false, size:7..10)
    }
    String numero
    static belongsTo = [personas:Persona]

    def String toString(){
        "$numero"
    }
}
