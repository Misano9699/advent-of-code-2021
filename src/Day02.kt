fun main() {
    fun part1(input: List<String>): Int {
        println("Day02 - part1")
        var position = 0
        var depth = 0
        input.map { it.split(" ") }
            .forEach {
                when (it.first()) {
                    "forward" -> position += it.last().toInt()
                    "down" -> depth += it.last().toInt()
                    "up" -> depth -= it.last().toInt()
                }
            }
        return position * depth;
    }

    fun part2(input: List<String>): Int {
        println("Day02 - part2")
        var aim = 0
        var position = 0
        var depth = 0
        input.map { it.split(" ") }
            .forEach {
                when (it.first()) {
                    "forward" -> {
                        position += it.last().toInt()
                        depth += aim * it.last().toInt()
                    }
                    "down" -> aim += it.last().toInt()
                    "up" -> aim -= it.last().toInt()
                }
            }
        return position * depth;
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 150)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 900)
    println("Answer part2: " + part2(input))
}