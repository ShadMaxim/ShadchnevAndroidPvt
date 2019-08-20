package by.itacademy.myapp.dz12.student.list

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Dz12PresenterList : Dz12BasePresenterList {

    private var view: Dz12ViewList? = null
    var listOfStudents: MutableList<Dz12StudentData> = mutableListOf()
    private val repository = provideStudentRepository()
    var disposable: Disposable? = null
    var charInFilter = ""
    private val number_page = 10

    override fun setView(view: Dz12ViewList) {

        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun newListForSearch(text: String): MutableList<Dz12StudentData> {
        val list = listOfStudents
        view?.showNewList(list)
        return list
    }

    override fun loadList(text: String) {
        charInFilter = text

        listOfStudents.clear()

        disposable = repository
            .search(number_page, 0, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfStudents.clear()
                listOfStudents.addAll(data)
                val list = listOfStudents

                view?.showNewList(list)
                view?.notShowProgressBar()
                view?.showToastGetOk(" list load successfully ")
                disposable?.dispose()
            }, { throwable ->

                view?.showToastGetError(throwable.toString())
                disposable?.dispose()
            })
    }

    override fun reloadRecycler() {
        val list = listOfStudents
        view?.showNewList(list)
        view?.showProgressBar()
    }

    override fun goToAddButton() {
        view?.addButtonClick()
    }

    fun loadMore(page: Int, text: String) {

        disposable = repository
            .search(number_page, number_page*page, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfStudents.addAll(data)
                val list = listOfStudents

                view?.showNewList(list)
                view?.notShowProgressBar()
                view?.showToastGetOk(" list load successfully ")
                disposable?.dispose()
            }, { throwable ->

                view?.showToastGetError(throwable.toString())
                disposable?.dispose()
            })
    }
}
