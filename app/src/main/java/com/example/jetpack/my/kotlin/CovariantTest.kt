package com.example.jetpack.my.kotlin

fun main(args: Array<String>) {
    //协变
    val student = Student("Tom", 18)
    //创建一个持有Student的Data类
    val studentData = Data<Student>(student)
//        studentData.set(student)

    //将持有Student的Data类实例传给接收Data<People>类型参数的方法
    handlePeopleData(studentData)

    //最后我们再把数据取出来看看
    val curStudentData = studentData.get()

    //逆变
    val transformer = object : Transformer<People> {

        override fun transform(t: People): String {
            return "${t.name} ${t.age}"
        }

        override fun reverse(name: String, age: Int): People {
            return Teacher(name, age)
        }
    }
    handleTransformer(transformer)

    val arr = arrayOf(People("Haha", 25), People("Jay", 18), People("Jack", 20))
    val student1 = MyComparable(Student("Haha", 25))
}

class MyComparable(private val student: Student) : Comparable<Student> {
    override fun compareTo(other: Student): Int {
        return student.age - other.age
    }

}

fun handleTransformer(transformer: Transformer<Student>) {
//    val student = Student("tom", 18)
//    val result = transformer.transform(student)

    //for test
    val student = transformer.reverse("tom", 18)
}

fun handlePeopleData(data: Data<People>) {
    //这里我们创建一个Teacher的实例，把data持有的数据替换掉
//        val teacherData = Teacher("Jason", 38)
//        data.set(teacherData)
    //只做读取逻辑
    val get = data.get()
    print("${get?.age}, ${get?.name}")
}

interface Transformer<in T> {
    fun transform(t: T): String
    fun reverse(name: String, age: Int): @UnsafeVariance T
}

interface MyClass<T> {
    fun method(params: T): T
}

open class People(val name: String, val age: Int)
class Teacher(name: String, age: Int) : People(name, age)
class Student(name: String, age: Int) : People(name, age)

//class Data<T> {
//    private var value: T? = null
//
//    fun set(value: T) {
//        this.value = value
//    }
//
//    fun get(): T? {
//        return value
//    }
//}

class Data<out T>(val value: T?) {

    fun get(): T? {
        return value
    }
}