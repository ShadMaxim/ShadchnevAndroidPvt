package by.itacademy.myapp.dz12.student.model.datasingleton

object Dz12StudentsSinglton {

    private var studentsList: MutableList<Dz12StudentData> = mutableListOf()

    fun getStudentsList(): MutableList<Dz12StudentData> {
        return studentsList
    }

    fun getStudentsExplorerList(): MutableList<Dz12StudentData> {
        createStudentsList(studentsList)
        return studentsList
    }

    fun createStudentsList(list: List<Dz12StudentData>) {
        studentsList = list as MutableList<Dz12StudentData>
    }

    fun addNewStudent(student: Dz12StudentData) {
        studentsList.add(student)
    }

    fun findStudentById(id: String): Dz12StudentData {
        return studentsList.find { it.id == id }!!
    }

    fun editStudent(studentDataOld: Dz12StudentData, studentDataEdited: Dz12StudentData) {
        val numberStudent = studentsList.indexOf(studentDataOld)
        studentsList.set(numberStudent, studentDataEdited)
    }

    fun deleteStudentOfList(student: Dz12StudentData) {
        studentsList.remove(student)
    }

    fun creatureNewId(): String {
        return(((Math.random() * 100000).toLong() * (Math.random() * 100000)
            .toLong()) / (((Math.random() * 10000).toLong() + Math.random() * 10000).toLong())).toString()
    }

    fun search(name: String): List<Dz12StudentData> =
        studentsList.filter { it.name.toUpperCase().contains(name.toUpperCase()) }
}