package com.fox.kotlinproject.practice

class KotlinBase {
    val a: String = "hello world"

    //声明一个方法
    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    //代码块函数体
    fun sum1(x: Int, y: Int): Int = x + y

    //val 和var的区别 val = var + final
    val x = intArrayOf(1, 3, 2)

    //kotlin中的函数是头等公民
    //它允许以其他函数作为参数或返回值的函数
    fun foo(x: Int) {
        fun double(y: Int): Int {
            return y * 2
        }
        println(double(x))
    }

    //枚举申明
    enum class Day {
        MON, TUE, WEN, THU, FRI, SAT, SUN
    }

    //包含成员变量的枚举类
    enum class DayOfWeek(val day: Int) {
        MON(1), TUE(2), WEN(3), THU(4), FRI(5), SAT(6), SUN(7);

        fun getDayNum(): Int {
            return day
        }
    }

    //when else用法
    fun schedule(sunny: Boolean, day: Day) = when {
        day == Day.SAT -> println("sat")
        day == Day.SUN -> println("sun")
        sunny -> println("sunny")
        else -> println("study")
    }

    //step until downTo用法
    fun fortest() {
        for (i in 1..10 step 2) print(i)
        for (i in 1 until 10) print(i)
        for (i in 10 downTo 1 step 2) print(i)
    }

    //中綴表达式
    class Person() {
        infix fun called(name: String) {
            println("my name is $name.")
        }
    }

    fun personTest() {
        val person = Person()
        person called "fox"
    }

    //结构声明 data class自动可用 如果不是data class 需要声明component
    class NameComponents(val name: String, val extension: String) {
        operator fun component1() = name
        operator fun component2() = extension

        fun splitFileName(fullName: String): NameComponents {
            val ret = fullName.split('.', limit = 2)
            return NameComponents(ret[0], ret[1])
        }

        fun test() {
            val (name1, ext1) = splitFileName("example.kt")
        }

    }

    data class NameComponents1(val name: String, val extension: String) {
        fun splitFileName(fullName: String): NameComponents1 {
            val ret = fullName.split('.', limit = 2)
            return NameComponents1(ret[0], ret[1])
        }

        fun test() {
            val (name, ext) = splitFileName("example.kt")
            val action = ::splitFileName
        }
    }

    //lambda作为参数 并且有默认实现
    fun <T> Collection<T>.joinToString(
        separator: String = ",",
        prefix: String = "",
        postfix: String = "",
        transform: (T) -> String = { it.toString() }
    ): String {
        val result = StringBuilder(prefix)
        for ((index, element) in this.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(transform(element))
        }
        result.append(postfix)
        return result.toString()
    }

    val letters = listOf("Alpha", "Beta")
    val ret1 = letters.joinToString()
    val ret2 = letters.joinToString { it.toLowerCase() }
    val rets = letters.joinToString(
        separator = "!",
        postfix = "_",
        prefix = "/",
        transform = { it.toUpperCase() })
}
