package by.itacademy.myapp.dz12.student.edit

import android.util.Patterns
import java.util.regex.Pattern
import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12PresenterEdit : Dz12BasePresenterEdit {

    private var view: Dz12ViewEdit? = null
    private val pattern = Patterns.WEB_URL
    private var patternAge: Pattern = Pattern.compile("[1-9]([0-9]*)")
    private val repository = provideStudentRepository()
    private var disposable: Disposable? = null
    var student: Dz12StudentData? = null

    override fun setView(view: Dz12ViewEdit) {
        this.view = view
    }

    override fun getStudentById(idStudent: String) {

        disposable = repository
            .getById(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                student = it
                view?.showStudent(student!!)
            }, {
                view?.showErrorLoadById("Error: $it")
            })
    }

    override fun detachView() {
        this.view = null
        disposable!!.dispose()
    }

    private fun update(idStudent: String, name: String, imageUrl: String, age: Int) {

        student = Dz12StudentData(idStudent, name, imageUrl, age)

        disposable = repository
            .update(student!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.showToastUpdateOk(it.name + " edited successfully")
            }, {

                view?.showToastUpdateError("Error: $it")
            })
        view?.updatePage()
    }

    private fun create(name: String, imageUrl: String, age: Int) {

        val studentNewForServer = Dz12StudentData("", name, imageUrl, age)

        disposable = repository
            .create(studentNewForServer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.showToastCreateOk(it.name + "created successfully")
            }, {

                view?.showToastCreateError("Error : $it")
            })
        view?.updatePage()
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
            if (idStudent != Dz12EditFragment.NEW_STUDENT) {

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