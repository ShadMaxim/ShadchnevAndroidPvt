package by.itacademy.myapp.fruits

class FruitsTask {

    fun checkWin(codeList: List<List<String>>, shoppingList: List<String>): Int {

        val sizeCodeList = codeList.sumBy { list -> list.size }
        val sizeShoppingCart = shoppingList.size

        var number = 0
        var numberOfList = 0
        var counter = 0

        if (sizeShoppingCart < sizeCodeList) {

        } else {
            while (number < sizeShoppingCart && numberOfList < codeList.size) {
                if (shoppingList[number] == codeList[numberOfList][0] || codeList[numberOfList][0] == "anything") {

                    var sizeThisList = codeList[numberOfList].size

                    if (shoppingList.size < (number + sizeThisList)) {
                        break
                    }

                    var newList = shoppingList.subList(number, number + sizeThisList)

                    for (i in 0 until newList.size) {
                        if (codeList[numberOfList][i] != newList[i] && codeList[numberOfList][i] != "anything") {
                            break
                        }
                    }

                    counter++
                    numberOfList++
                }
                number++
            }
        }

        return if (counter == codeList.size) 1 else 0
    }
}