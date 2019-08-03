package by.itacademy.myapp.dz12.student.details

import by.itacademy.myapp.dz12.student.model.datasingleton.Dz12StudentsSinglton
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12PresenterDetails : Dz12BasePresenterDetails {

    private var view: Dz12ViewDetails? = null
    var disposable: Disposable? = null
    private val repository = provideStudentRepository()

    override fun setView(view: Dz12ViewDetails) {
        this.view = view
    }

    override fun getStudentById(idStudent: String) {
        val student = Dz12StudentsSinglton.findStudentById(idStudent)
        view?.showStudent(student)
    }

    override fun deleteOfServerAndSingleton(idStudent: String) {

        val student = Dz12StudentsSinglton.findStudentById(idStudent)

        disposable = repository
            .delete(idStudent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.showToastOk("deletion of " + student.name + " on server was successful")
                Dz12StudentsSinglton.deleteStudentOfList(student)
                view?.updatePage()
                disposable?.dispose()
            }, {

                view?.showToastError("""Error : $it""")
                disposable?.dispose()
            })
    }

    override fun goEdit(idStudent: String) {
        view?.goToEdit(idStudent)
    }

    override fun goBackStack() {
        view?.ifNullStudent()
    }

    override fun detachView() {
        this.view = null
    }
}