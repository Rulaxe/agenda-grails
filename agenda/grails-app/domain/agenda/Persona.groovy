package agenda

class Persona {
    static constraints = {
        nombre(size:3..50)
        direccion(maxSize:300)
    }
    String nombre
    String direccion
    static hasMany = [telefonos:Telefono]
    List telefonos
    static mapping = {
        phones cascade:"all-delete-orphan"
    }
    def String toString() {
        "$nombre"
    }
}
