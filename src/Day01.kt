class Day01Solver: TaskSolver {
    override fun solvePart1(input: List<String>): String {
        var lastValue = Int.MAX_VALUE
        var counter = 0
        for (value in input.map(String::toInt)) {
            if (value > lastValue) counter += 1
            lastValue = value
        }
        return counter.toString()
    }

    override fun solvePart2(input: List<String>): String =
        solvePart1(input.map(String::toInt).windowed(3).map(List<Int>::sum).map(Int::toString))
}

fun main() {
    println("Day 01")

    check(runPart1<Day01Solver>("day01-test").toInt() == 7)
    println("Part 1: ${runPart1<Day01Solver>("day01-input").toInt()}")

    check(runPart2<Day01Solver>("day01-test").toInt() == 5)
    println("Part 2: ${runPart2<Day01Solver>("day01-input").toInt()}")
}
