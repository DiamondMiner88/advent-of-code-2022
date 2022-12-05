fun main() {
    d5part1()
    d5part2()
}

fun d5part1() {
    val input = readInput("input/day05.txt")
        .split("\n\n")

    val stackSplits = input[0].split("\n")
    val stacksCount = stackSplits.last().count { it.isDigit() }
    val stacks = MutableList<MutableList<Char>>(9) { mutableListOf() }

    for (stack in stackSplits.take(stackSplits.size - 1)) {
        val indexes = (0 until stacksCount).map { 1 + it * 4 }

        indexes.mapIndexed { i, index ->
            val char = stack.getOrNull(index)
            if (char != null && char != ' ') {
                stacks[i] += char
            }
        }
    }
    stacks.forEachIndexed { index, stack ->
        stacks[index] = stack.reversed().toMutableList()
    }

    val regex = "move (\\d{1,2}) from (\\d) to (\\d)".toRegex()
    for (inst in input[1].split("\n").filter { it.isNotBlank() }) {
        val (_, count, from, to) = regex.find(inst)!!.groupValues

        val src = stacks[from.toInt() - 1]
        val dst = stacks[to.toInt() - 1]

        val removed = src.takeLast(count.toInt()).reversed()
        for (i in (0 until count.toInt())) src.removeLast()
        dst.addAll(removed.toTypedArray())
    }


    println(stacks.joinToString("") { it.last().toString() })
}

fun d5part2() {
    val input = readInput("input/day05.txt")
        .split("\n\n")

    val stackSplits = input[0].split("\n")
    val stacksCount = stackSplits.last().count { it.isDigit() }
    val stacks = MutableList<MutableList<Char>>(9) { mutableListOf() }

    for (stack in stackSplits.take(stackSplits.size - 1)) {
        val indexes = (0 until stacksCount).map { 1 + it * 4 }

        indexes.mapIndexed { i, index ->
            val char = stack.getOrNull(index)
            if (char != null && char != ' ') {
                stacks[i] += char
            }
        }
    }
    stacks.forEachIndexed { index, stack ->
        stacks[index] = stack.reversed().toMutableList()
    }

    val regex = "move (\\d{1,2}) from (\\d) to (\\d)".toRegex()
    for (inst in input[1].split("\n").filter { it.isNotBlank() }) {
        val (_, count, from, to) = regex.find(inst)!!.groupValues

        val src = stacks[from.toInt() - 1]
        val dst = stacks[to.toInt() - 1]

        val removed = src.takeLast(count.toInt())
        for (i in (0 until count.toInt())) src.removeLast()
        dst.addAll(removed.toTypedArray())
    }


    println(stacks.joinToString("") { it.last().toString() })
}
