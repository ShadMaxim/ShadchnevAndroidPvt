package by.itacademy.myapp.dz6

class StudentsSinglton private constructor() {

    private lateinit var studentsList: ArrayList<Student>

    companion object {
        val instance = StudentsSinglton()
    }

    init
    {
        studentsList = createStudentsList()
    }

    fun getStudents(): ArrayList<Student> {
        if (studentsList.isEmpty()) {
            createStudentsList()
        }
        return studentsList
    }

    private fun createStudentsList(): ArrayList<Student> {
        var list = arrayListOf(
            Student("1", "http://pngimg.com/uploads/trollface/trollface_PNG25.png", "Petr", 22),
            Student("2", "http://pngimg.com/uploads/trollface/trollface_PNG11.png", "Sacha", 21),
            Student("3", "http://pngimg.com/uploads/trollface/trollface_PNG3.png", "Vania", 30),
            Student("4", "http://pngimg.com/uploads/trollface/trollface_PNG17.png", "Lesha", 55),
            Student("5", "http://pngimg.com/uploads/trollface/trollface_PNG22.png", "Maxim", 30),
            Student("6", "http://pngimg.com/uploads/trollface/trollface_PNG29.png", "Danila", 21),
            Student("7", "http://pngimg.com/uploads/trollface/trollface_PNG31.png", "Sergey", 30),
            Student("8", "http://pngimg.com/uploads/trollface/trollface_PNG39.png", "Nikolai", 55),
            Student("9", "http://pngimg.com/uploads/trollface/trollface_PNG44.png", "Gosha", 45)
        )
        return list
    }

    fun addNewStudent(id: String, url: String, name: String, age: Int) {
        studentsList.add(Student(id, url, name, age))
    }

    fun findStudentById(id: String): Student {
        lateinit var temp: Student
        for (student in studentsList) {
            if (student.id == id) {
                temp = student
            }
        }
        return temp
    }

    fun deleteStudentByIdFromList(student: Student) {
        for (temp in studentsList) {
            if (temp.id == student.id) {
                studentsList.remove(student)
            }
        }
    }

    fun editStudent(student: Student, id: String, url: String, name: String, age: Int) {
        student.id = id
        student.url = url
        student.name = name
        student.age = age
    }
}