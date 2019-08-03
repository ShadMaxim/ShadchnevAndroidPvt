package by.itacademy.myapp.dz12.student.edit

import android.util.Patterns
import java.util.regex.Pattern
import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentData
import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentsSinglton
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12PresenterEdit : Dz12BasePresenterEdit {

    private var view: Dz12ViewEdit? = null
    private val pattern = Patterns.WEB_URL
    private var patternAge: Pattern = Pattern.compile("[1-9]([0-9]*)")
    private val repository = provideStudentRepository()
    var disposable: Disposable? = null

    override fun setView(view: Dz12ViewEdit) {
        this.view = view
    }

    override fun getStudentById(idStudent: String) {
        val student = Dz12StudentsSinglton.findStudentById(idStudent)
        view?.showStudent(student)
    }

    override fun detachView() {
        this.view = null
    }

    private fun update(idStudent: String, name: String, imageUrl: String, age: Int) {

        val studentOld = Dz12StudentsSinglton.findStudentById(idStudent)
        val studentDataEdited = Dz12StudentData(idStudent, name, imageUrl, age)

        disposable = repository
            .update(studentDataEdited)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Dz12StudentsSinglton.editStudent(studentOld, studentDataEdited)
                view?.showToastUpdateOk(it.name + " edited successfully")
                view?.updatePage()

                disposable!!.dispose()
            }, {
                view?.showToastUpdateError("Error: $it")

                disposable!!.dispose()
            })
    }

    private fun create(name: String, imageUrl: String, age: Int) {

        val studentNewForServer = Dz12StudentData(Dz12StudentsSinglton.creatureNewId(), name, imageUrl, age)
        var studentNewForSingleton: Dz12StudentData

        disposable = repository
            .create(studentNewForServer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                studentNewForSingleton = Dz12StudentData(it.id, name, imageUrl, age)
                Dz12StudentsSinglton.addNewStudent(studentNewForSingleton)

                view?.showToastCreateOk(it.name + "created successfully")
                view?.updatePage()

                disposable?.dispose()
            }, {
                view?.showToastCreateError("Error : $it")

                disposable?.dispose()
            })
    }

    override fun goToSaveOrEdit(idStudent: String, name: String, imageUrl: String, age: Int) {

        var textErrorCorrectDataFiling = ""

        if (!pattern.matcher(imageUrl).matches()) {
            textErrorCorrectDataFiling = "ERROR( URL ): Not valid URL"
        } else if (!patternAge.matcher(age.toString()).matches() && age.toString().isEmpty()) {
            textErrorCorrectDataFiling = "ERROR( AGE ): Not valid Age, must be > 0"
        } else if (name.isEmpty()) {
            textErrorCorrectDataFiling = "ERROR( NAME ): Not valid Name, must be filled"
        } else {
            if (idStudent != Dz12EditFragment.NEW_STUDENT && idStudent != null) {

                update(idStudent, name, imageUrl, age)
            } else {

                create(name, imageUrl, age)
            }
        }
        if (textErrorCorrectDataFiling.isNotEmpty()) {
            view?.showToastErrorFilling(textErrorCorrectDataFiling)
        }
    }
}