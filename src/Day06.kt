class Day06Solver: TaskSolver {
    override fun solvePart1(input: List<String>): String = solve(input, 80)

    override fun solvePart2(input: List<String>): String = solve(input, 256)

    private fun solve(input: List<String>, generationCount: Int): String {
        var entities = MutableList(9) { 0L }
        input.first().split(",").map(String::toInt).forEach { entities[it] += 1L }

        for (i in 0 until generationCount) {
            val newEntities = MutableList(9) { 0L }
            entities.forEachIndexed { idx, it ->
                if (idx == 0) {
                    newEntities[6] = it
                    newEntities[8] = it
                } else {
                    newEntities[idx - 1] += it
                }
            }
            entities = newEntities
        }

        return  entities.sum().toString()
    }
}

fun main() {
    println("Day 06")

    check(runPart1<Day06Solver>("day06-test").toInt() == 5934)
    println("Part 1: ${runPart1<Day06Solver>("day06-input")}")

    check(runPart2<Day06Solver>("day06-test").toLong() == 26984457539)
    println("Part 2: ${runPart2<Day06Solver>("day06-input")}")
}
