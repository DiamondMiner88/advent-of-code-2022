import java.math.BigInteger

fun main() {
    d11part1()
//    d11part2()
}

val regex =
    "Monkey (\\d):\\n  Starting items: (.+?)\\n  Operation: new = old (.+?)\\n  Test: divisible by (\\d{1,2})\\n    If true: throw to monkey (\\d)\\n    If false: throw to monkey (\\d)".toRegex()

data class Monkey(
    var items: MutableList<BigInteger?>,
    var operation: String,
    var divisibleBy: BigInteger,
    var targetMonkeyTrue: Int,
    var targetMonkeyFalse: Int,
    var totalInspections: Long = 0,
)

fun d11part1() {
    val input = readInput("input/day11.txt")

    val monkeys: MutableList<Monkey?> = MutableList(10) { null }

    for (match in regex.findAll(input)) {
        val (_, monkeyId, items, operation, divisibleBy, targetMonkeyTrue, targetMonkeyFalse) = match.groupValues

        monkeys[monkeyId.toInt()] = Monkey(
            items = items.split(",").map { it.trim().toBigInteger() }.toMutableList(),
            operation = operation,
            divisibleBy = divisibleBy.toBigInteger(),
            targetMonkeyTrue = targetMonkeyTrue.toInt(),
            targetMonkeyFalse = targetMonkeyFalse.toInt(),
        )
    }

    for (round in 1..20) {
        for (monkey in monkeys.filterNotNull()) {
            val opParts = monkey.operation.split(" ")
            val opNum = opParts[1].toBigIntegerOrNull() ?: BigInteger.valueOf(-1L)

            monkey.items.forEach {
                var newLevel = it ?: return@forEach

                val num = if (opNum == BigInteger.valueOf(-1L)) newLevel else opNum
                when (opParts[0]) {
                    "*" -> newLevel *= num
                    "+" -> newLevel += num
                }

                newLevel /= BigInteger.valueOf(3)

                if (newLevel % monkey.divisibleBy == BigInteger.ONE) {
                    monkeys[monkey.targetMonkeyTrue]!!.items += newLevel
                } else {
                    monkeys[monkey.targetMonkeyFalse]!!.items += newLevel
                }

                monkey.totalInspections++
            }
            monkey.items.clear()
        }
    }

    println(
        monkeys.sortedBy { it?.totalInspections }
            .takeLast(2)
            .let { it[0]!!.totalInspections * it[1]!!.totalInspections }
    )
}

private operator fun <E> List<E>.component7(): E {
    return this[6]
}

private operator fun <E> List<E>.component6(): E {
    return this[5]
}

fun d11part2() {
    val input = readInput("input/day11.txt")

    val monkeys: MutableList<Monkey?> = MutableList(8) { null }

    for (match in regex.findAll(input)) {
        val (_, monkeyId, items, operation, divisibleBy, targetMonkeyTrue, targetMonkeyFalse) = match.groupValues
        val parsedItems = items.split(",").map { it.trim().toBigInteger() }
        monkeys[monkeyId.toInt()] = Monkey(
            items = parsedItems.toMutableList(),
            operation = operation,
            divisibleBy = divisibleBy.toBigInteger(),
            targetMonkeyTrue = targetMonkeyTrue.toInt(),
            targetMonkeyFalse = targetMonkeyFalse.toInt(),
        )
    }

    val totalItems = monkeys.sumOf { it?.items?.size ?: 0 }
    for (monkey in monkeys) {
        monkey?.items = MutableList(totalItems) { monkey?.items?.getOrNull(it) }
    }

    val monkeyOpParts = monkeys.map {
        it ?: return@map null
        val parts = it.operation.split(" ")
        parts[0] to (parts[1].toBigIntegerOrNull() ?: BigInteger.valueOf(-1L))
    }
    val bigIntNegativeOne = BigInteger.valueOf(-1L)
    for (round in 1..10000) {
        println("round $round")

        for (monkeyId in monkeys.indices) {
            val monkey = monkeys[monkeyId] ?: continue
            val (op, opNum) = monkeyOpParts[monkeyId] ?: continue

            monkey.items.forEach {
                var newLevel = it ?: return@forEach

                val num = if (opNum == bigIntNegativeOne) newLevel else opNum
                when (op) {
                    "*" -> newLevel *= num
                    "+" -> newLevel += num
                }
                num.abs()

                if (newLevel % monkey.divisibleBy == BigInteger.ONE) {
                    val ind = monkeys[monkey.targetMonkeyTrue]!!.items.indexOf(null)
                    monkeys[monkey.targetMonkeyTrue]!!.items[ind] = newLevel
                } else {
                    val ind = monkeys[monkey.targetMonkeyFalse]!!.items.indexOf(null)
                    monkeys[monkey.targetMonkeyFalse]!!.items[ind] = newLevel
                }

                monkey.totalInspections++
            }
            monkey.items.replaceAll { null }
        }
    }

    println(monkeys)
    println(
        monkeys.sortedBy { it?.totalInspections }
            .takeLast(2)
            .let { it[0]!!.totalInspections * it[1]!!.totalInspections }
    )
}
