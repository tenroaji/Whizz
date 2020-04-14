package id.magau.whizz.utils



fun passPhrase(value : String):String{
    var data = ""
    for (a : Int in value.indices){
        val char =  value[a]
        val str = char.plus(20)
        data += str
    }
    return data

}

