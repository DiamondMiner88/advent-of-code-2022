fun main() {
    d5part1()
    d5part2()
}

fun d5part1() {
    val input = readInput("input/day05.txt")

    val splits = input.split("\n\n")

    val stacks = MutableList<MutableList<Char>>(9) { mutableListOf() }

    val stackSplits = splits[0].split("\n")
    for (stack in stackSplits.take(stackSplits.size - 1)) {
        val indexes = listOf(1, 5, 9, 13, 17, 21, 25, 29, 33)

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
    for (inst in splits[1].split("\n").filter { it.isNotBlank() }) {
        println(stacks)
        println(inst)
        val (_, count, from, to) = regex.find(inst)!!.groupValues

        val src = stacks[from.toInt() - 1]
        val dst = stacks[to.toInt() - 1]

        val removed = src.takeLast(count.toInt()).reversed()
        for (i in (0 until count.toInt())) src.removeLast()
        dst.addAll(removed.toTypedArray())
        println(src)
        println(dst)
    }


    println(stacks.joinToString("") { it.last().toString() })
}

fun d5part2() {
    val input = readInput("input/day05.txt")

    val splits = input.split("\n\n")

    val stacks = MutableList<MutableList<Char>>(9) { mutableListOf() }

    val stackSplits = splits[0].split("\n")
    for (stack in stackSplits.take(stackSplits.size - 1)) {
        val indexes = listOf(1, 5, 9, 13, 17, 21, 25, 29, 33)

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
    for (inst in splits[1].split("\n").filter { it.isNotBlank() }) {
        println(stacks)
        println(inst)
        val (_, count, from, to) = regex.find(inst)!!.groupValues

        val src = stacks[from.toInt() - 1]
        val dst = stacks[to.toInt() - 1]

        val removed = src.takeLast(count.toInt())
        for (i in (0 until count.toInt())) src.removeLast()
        dst.addAll(removed.toTypedArray())
        println(src)
        println(dst)
    }


    println(stacks.joinToString("") { it.last().toString() })
}
