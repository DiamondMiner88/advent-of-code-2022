fun main() {
    d4part1()
    d4part2()
}

fun d4part1() {
    val input = readInput("input/day04.txt")
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.split(",").map { val range = it.split("-"); (range[0].toInt()..range[1].toInt()) } }

    var total = 0

    for ((elf1, elf2) in input) {
        if (elf1.first >= elf2.first && elf1.last <= elf2.last)
            total++
        else if (elf2.first >= elf1.first && elf2.last <= elf1.last)
            total++
    }

    println(total)
}

fun d4part2() {
    val input = readInput("input/day04.txt")
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.split(",").map { val range = it.split("-"); (range[0].toInt()..range[1].toInt()) } }

    var total = 0

    loop@ for ((elf1, elf2) in input) {
        for (it in elf1) {
            if (elf2.contains(it)) {
                total++
                continue@loop
            }
        }

        for (it in elf2) {
            if (elf1.contains(it)) {
                total++
                continue@loop
            }
        }
    }

    println(total)
}
