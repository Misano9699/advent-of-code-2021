fun main() {

    fun convertToBoard(input: List<String>): MutableList<Board> {
        val boards = mutableListOf<Board>()
        var board = mutableListOf<List<Int>>()
        input.forEach {
            when (it) {
                "" -> {
                    boards.add(Board(board))
                    board = mutableListOf()
                }
                else -> {
                    val row = it.trim().split(Regex(" +")).map { s -> s.toInt() }
                    board.add(row)
                }
            }
        }
        boards.add(Board(board))
        return boards
    }

    fun part1(input: List<String>): Int {
        println("Day04 - part1")
        val numbers = input.first().split(",").map { it.toInt() }.also { println(it) }
        val boards = convertToBoard(input.drop(2))
        println(boards)

        val numbersDrawn = mutableListOf<Int>()
        return numbers.firstNotNullOf { number ->
            numbersDrawn += number
            boards.firstOrNull { it.isWinner(numbersDrawn) }?.let { winner ->
                number * winner.getSumExcludingDrawnNumbers(numbersDrawn)
            }
        }
    }

    fun part2(input: List<String>): Int {
        println("Day04 - part2")
        val numbers = input.first().split(",").map { it.toInt() }.also { println("Numbers: $it") }
        val boards = convertToBoard(input.drop(2))
        println("Boards : $boards")

        val numbersDrawn = mutableListOf<Int>()

        var i = 0
        var winners: List<Board>
        do {
            numbersDrawn.add(numbers[i])
            winners = boards.filter { it.isWinner(numbersDrawn) }
            if (winners.isNotEmpty()) {
                boards.removeAll(winners)
            }
            i++
        } while (i < numbers.size && boards.isNotEmpty())
        println("Winners: $winners")

        val lastNUmber = numbersDrawn.last().also { println("last: $it") }
        val sum = when {
            winners.isNotEmpty() -> winners[0].getSumExcludingDrawnNumbers(numbersDrawn)
            else -> 0
        }.also { println("sum: $it") }
        return lastNUmber * sum
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 4512)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 1924)
    println("Answer part2 " + part2(input))
}

private data class Board(val numbers: List<List<Int>>) {

    fun isWinner(numbersDrawn: List<Int>) =
        checkRowsForWinner(numbersDrawn) || checkColumnsForWinner(numbersDrawn)

    private fun checkColumnsForWinner(numbersDrawn: List<Int>): Boolean {
        var isWinner = false
        (0 until numbers[0].size).forEach { i ->
            var completeColumn = true
            numbers.forEach {
                completeColumn = completeColumn && it[i] in numbersDrawn
            }
            isWinner = isWinner || completeColumn
        }
        return isWinner
    }

    private fun checkRowsForWinner(numbersDrawn: List<Int>): Boolean =
        numbers.find { row ->
            row.all { it in numbersDrawn }
        } != null

    fun getSumExcludingDrawnNumbers(numbersDrawn: List<Int>) =
        numbers.sumOf { row ->
            row.filterNot { it in numbersDrawn }.sum()
        }
}
