package by.itacademy.myapp.dz11.mvp.model

import by.itacademy.myapp.dz6.Dz6Student

object Dz11DataStudent {

    private var studentsList: MutableList<Dz6Student> = mutableListOf()

    fun getStudentsExplorerList(): MutableList<Dz6Student> {
        createStudentsList()
        return studentsList
    }

    fun getStudentsList(): MutableList<Dz6Student> {
        return studentsList
    }

    private fun createStudentsList() {
        studentsList = mutableListOf(
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG25.png", "Petr", 22),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG11.png", "Sacha", 21),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG3.png", "Vania", 30),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG17.png", "Lesha", 55),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG22.png", "Maxim", 30),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG29.png", "Danila", 21),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG31.png", "Sergey", 30),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG39.png", "Nikolai", 55),
            Dz6Student(creatureNewId(), "http://pngimg.com/uploads/trollface/trollface_PNG44.png", "Gosha", 45)
        )
    }

    fun addNewStudent(student: Dz6Student) {
        studentsList.add(student)
    }

    fun findStudentById(id: Long): Dz6Student {
        return studentsList.find { it.id == id }!!
    }

    fun editStudent(studentDataOld: Dz6Student, studentDataEdited: Dz6Student) {
        var numberStudent = studentsList.indexOf(studentDataOld)
        // deleteStudentOfList(studentDataOld)
        studentsList.set(numberStudent, studentDataEdited)
    }

    fun deleteStudentOfList(student: Dz6Student) {
        studentsList.remove(student)
    }

    fun getId(student: Dz6Student): Long {
        return student.id
    }

    fun creatureNewId(): Long {
        return ((Math.random() * 100000).toLong() * (Math.random() * 100000)
            .toLong()) / (((Math.random() * 10000).toLong() + Math.random() * 10000).toLong())
    }

    fun search(name: String): List<Dz6Student> =
        studentsList.filter { it.name.toUpperCase().contains(name.toUpperCase()) }
}