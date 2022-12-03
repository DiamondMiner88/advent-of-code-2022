fun main() {
    d3part1()
    d3part2()
}

fun d3part1() {
    val input = readInput("input/day03.txt")
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.take(it.length / 2) to it.takeLast(it.length / 2) }

    var total = 0

    for (rucksack in input) {
        val (comp1, comp2) = rucksack
        val common = comp1.toList().intersect(comp2.toList())

        for (char in common) {
            if (char >= 'a') total += char.code - 96
            else if (char >= 'A') total += char.code - 38
        }
    }

    println(total)
}

fun d3part2() {
    val input = readInput("input/day03.txt")
        .split("\n")
        .filter { it.isNotBlank() }
        .chunked(3)

    var total = 0

    for (group in input) {
        val (ruck1, ruck2, ruck3) = group
        val common = ruck1.toList().intersect(ruck2.toList().intersect(ruck3.toList()))

        for (char in common) {
            if (char >= 'a') total += char.code - 96
            else if (char >= 'A') total += char.code - 38
        }
    }

    println(total)
}
