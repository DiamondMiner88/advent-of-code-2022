fun main() {
    part1()
    part2()
}

fun part1() {
    val input = readInput("input/day01.txt")
    val elves = input.split("\n\n")
        .map { it.split("\n").filter { it != "" } }
        .map { it.map { it.toInt() } }
    val maxCalories = elves.map { it.sum() }.max()
    println(maxCalories)
}

fun part2() {
    val input = readInput("input/day01.txt")
    val elves = input.split("\n\n")
        .map { it.split("\n").filter { it != "" } }
        .map { it.map { it.toInt() } }
        .map { it.sum() }
    val max3 = elves.sorted().takeLast(3).sum()
    println(max3)
}
