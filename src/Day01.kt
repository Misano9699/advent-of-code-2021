fun main() {
    fun part1(input: List<String>): Int {
        println("Day01 - part1")
        return input.map { it.toLong() }.zipWithNext().count { (a, b) -> a < b }
    }

    fun part2(input: List<String>): Int {
        println("Day01 - part2")
        return input.map { it.toLong() }.windowed(3).map { (a, b, c) -> a + b + c }.zipWithNext().count { (a, b) -> a < b }
    }

    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 7)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 5)
    println("Answer part2: " + part2(input))
}
