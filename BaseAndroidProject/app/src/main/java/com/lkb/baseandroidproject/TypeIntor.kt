const val TOTAL_EXPERIENCE = 7.5
fun main() {
//    val name:String="Lalit" // type inference
//    val sName="Behera" // compiler assigned a type
//    var age = 35
//    casteFireball(x=6)
    val count = "Mississippi".count { l -> l == 'i' }
    print(count)
}


fun casteFireball(n: Int = 4, x: Int) {
    println("a glass of fireball springs into existance.$n")
}