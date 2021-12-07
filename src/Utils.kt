import java.io.File

inline fun <reified T: TaskSolver> runPart1(inputFile: String): String =
    T::class.constructors.find { it.parameters.isEmpty() }!!.call()
        .solvePart1(File("src", "$inputFile.txt").readLines())

inline fun <reified T: TaskSolver> runPart2(inputFile: String): String =
    T::class.constructors.find { it.parameters.isEmpty() }!!.call()
        .solvePart2(File("src", "$inputFile.txt").readLines())

interface TaskSolver {
    fun solvePart1(input: List<String>): String
    fun solvePart2(input: List<String>): String
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val list = MutableList<MutableList<T>>(get(0).size) { mutableListOf() }

    forEach {
        it.forEachIndexed { idx, t -> list[idx].add(t) }
    }

    return list
}
