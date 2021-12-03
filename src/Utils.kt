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
