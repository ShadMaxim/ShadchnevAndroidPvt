package by.itacademy.myapp.dz12.student.details

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12PresenterDetails : Dz12BasePresenterDetails {

    private var view: Dz12ViewDetails? = null
    var disposable: Disposable? = null
    private val repository = provideStudentRepository()
    var student: Dz12StudentData? = null

    override fun setView(view: Dz12ViewDetails) {
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

                view?.showToastError("""Error : $it""")
            })
    }

    override fun delete(idStudent: String) {

        disposable = repository
            .delete(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.showToastOk("deletion of " + student!!.name + " on server was successful")
            }, {

                view?.showToastError("""Error : $it""")
            })
        view?.updatePage()
    }

    override fun goEdit(idStudent: String) {
        view?.goToEdit(idStudent)
    }

    override fun goBackStack() {
        view?.ifNullStudent()
    }

    override fun detachView() {
        this.view = null
        disposable!!.dispose()
    }
}