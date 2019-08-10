package by.itacademy.myapp.dz12.student.list

import by.itacademy.myapp.dz12.student.model.data.Dz12StudentData
import by.itacademy.myapp.dz12.student.model.provider.provideStudentRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Dz12PresenterList : Dz12BasePresenterList {

    private var view: Dz12ViewList? = null
    var listOfStudents: MutableList<Dz12StudentData> = mutableListOf()
    private val repository = provideStudentRepository()
    var disposable: Disposable? = null
    var charInFilter = ""
    var observable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    private val number_page = 10

    override fun setView(view: Dz12ViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable?.dispose()
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

                listOfStudents.addAll(data)
                val list = listOfStudents

                view?.showNewList(list)
                view?.showToastGetOk(" list load successfully ")
            }, { throwable ->

                view?.showToastGetError(throwable.toString())
            })
    }

    override fun reloadRecycler() {
        val list = listOfStudents
        view?.showNewList(list)
    }

    override fun timerToast() {

        disposable = observable
            .filter { it % 2 == 0.toLong() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.showTimer(it)
            }, {

                view?.showTimerError(it.toString())
            })
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
                view?.showToastGetOk(" list load successfully ")
            }, { throwable ->

                view?.showToastGetError(throwable.toString())
            })
    }
}
