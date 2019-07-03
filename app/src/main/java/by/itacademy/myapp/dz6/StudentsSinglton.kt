package by.itacademy.myapp.dz6

object StudentsSinglton {

    private var studentsList: MutableList<Student> = mutableListOf()

    fun getStudentsExprorerList(): MutableList<Student> {
        createStudentsList()
        return studentsList
    }

    fun getStudentsList(): MutableList<Student> {
        return studentsList
    }

    private fun createStudentsList() {
        studentsList = mutableListOf(
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG25.png", "Petr", 22),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG11.png", "Sacha", 21),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG3.png", "Vania", 30),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG17.png", "Lesha", 55),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG22.png", "Maxim", 30),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG29.png", "Danila", 21),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG31.png", "Sergey", 30),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG39.png", "Nikolai", 55),
            Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG44.png", "Gosha", 45)
        )
    }

    fun addNewStudent(student: Student) {
        studentsList.add(student)
    }

    fun findStudentById(id: Long): Student {
        return studentsList.find { it.id == id }!!
    }

    fun editStudent(studentDataOld: Student, studentDataEdited: Student) {
        deleteStudentOfList(studentDataOld)
        addNewStudent(studentDataEdited)
    }

    fun deleteStudentOfList(student: Student) {
        studentsList.remove(student)
    }

    fun getId(student: Student): Long {
        return student.id
    }

    fun creatureNewId(): Long {
        return ((Math.random() * 100000).toLong() * (Math.random() * 100000)
            .toLong()) / (((Math.random() * 10000).toLong() + Math.random() * 10000).toLong())
    }

    fun search(name: String): List<Student> =
        studentsList.filter { it.name.toUpperCase().contains(name.toUpperCase()) }
}