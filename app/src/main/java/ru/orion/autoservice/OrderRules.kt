package ru.orion.autoservice

/** Pure domain rules, shared by UI and unit tests. */
object OrderRules {
    val stages = listOf("Приём", "Диагностика", "Согласование", "Ремонт", "Готово", "Выдан")

    fun nextStage(current: Int): Int? =
        if (current in 0 until stages.lastIndex) current + 1 else null

    fun validNewOrder(client: String, car: String, issue: String, cost: String): Boolean =
        client.isNotBlank() && car.isNotBlank() && issue.isNotBlank() &&
            cost.toLongOrNull()?.let { it >= 0L } == true

    fun stageLabel(stage: Int): String = stages.getOrElse(stage) { "Неизвестный этап" }
}
