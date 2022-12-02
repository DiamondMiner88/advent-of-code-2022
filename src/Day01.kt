fun main() {
    d1Part1()
    d1Part2()
}

fun d1Part1() {
    val input = readInput("input/day01.txt")
    val elves = input.split("\n\n")
        .map { it.split("\n").filter { it != "" } }
        .map { it.map { it.toInt() } }
    val maxCalories = elves.map { it.sum() }.max()
    println(maxCalories)
}

fun d1Part2() {
    val input = readInput("input/day01.txt")
    val elves = input.split("\n\n")
        .map { it.split("\n").filter { it != "" } }
        .map { it.map { it.toInt() } }
        .map { it.sum() }
    val max3 = elves.sorted().takeLast(3).sum()
    println(max3)
}
