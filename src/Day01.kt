fun main() {
    fun part1(input: List<Long>): Int {
        println("Day01 - part1")
        return input.zipWithNext().count { (a, b) -> a < b }
    }

    fun part2(input: List<Long>): Int {
        println("Day01 - part2")
        return input.windowed(3).map { (a, b, c) -> a + b + c }.zipWithNext().count { (a, b) -> a < b }
    }

    val testInput = readInput("Day01_test").map { it.toLong() }
    val input = readInput("Day01").map { it.toLong() }

    check(part1(testInput).also { println("Answer test input part1: $it") } == 7)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 5)
    println("Answer part2: " + part2(input))
}
