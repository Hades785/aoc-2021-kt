class Day04Solver: TaskSolver {
    class BingoBoard(contents: List<String>) {
        private val matrix = contents.flatMap { it.split(" ") }.map(String::toInt).withIndex()
        private val marks = MutableList(25) { false }
        private var lastDraw = -1

        fun enterDraw(draw: Int) {
            lastDraw = draw
            matrix.filter { it.value == draw }.forEach { marks[it.index] = true }
        }

        fun check(): Boolean {
            val rows = marks.chunked(5)
            val columns = marks
                .withIndex()
                .groupBy { it.index % 5 }
                .map { it.value }
                .map { it.map(IndexedValue<Boolean>::value) }

            return rows.any { it.all { b -> b } } || columns.any { it.all { b -> b } }
        }

        fun score(): Int =
            matrix.map(IndexedValue<Int>::value).zip(marks).filter { !it.second }.map(Pair<Int, Boolean>::first).sum() * lastDraw
    }

    private lateinit var sequencedInput: Sequence<String>
    private lateinit var draws: Iterator<Int>
    private val boards = mutableListOf<BingoBoard>()

    private fun setup(input: List<String>) {
        sequencedInput = input.asSequence()
        draws = sequencedInput.take(1).first().split(",").map(String::toInt).iterator()
        sequencedInput = sequencedInput.drop(2)

        while (sequencedInput.any()) {
            boards.add(BingoBoard(sequencedInput.take(5).map(String::trim).map { it.replace("  ", " ") }.toList()))
            sequencedInput = sequencedInput.drop(6)
        }
    }

    override fun solvePart1(input: List<String>): String {
        setup(input)

        while (draws.hasNext()) {
            val draw = draws.next()
            boards.forEach { it.enterDraw(draw) }

            val winningBoard = boards.find { it.check() }
            if (winningBoard != null) return winningBoard.score().toString()
        }

        return "N/A"
    }

    override fun solvePart2(input: List<String>): String {
        setup(input)

        val completedBoards = mutableListOf<BingoBoard>()
        while (draws.hasNext()) {
            val draw = draws.next()
            boards.forEach { it.enterDraw(draw) }

            val winningBoards = boards.filter { it.check() }
            if (winningBoards.isNotEmpty()) {
                completedBoards.addAll(winningBoards)
                boards.removeAll(winningBoards)
            }
        }

        return completedBoards.last().score().toString()
    }
}

fun main() {
    println("Day 04")

    check(runPart1<Day04Solver>("day04-test").toInt() == 4512)
    println("Part 1: ${runPart1<Day04Solver>("day04-input")}")

    check(runPart2<Day04Solver>("day04-test").toInt() == 1924)
    println("Part 2: ${runPart2<Day04Solver>("day04-input")}")
}
