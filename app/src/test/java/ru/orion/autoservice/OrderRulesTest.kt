package ru.orion.autoservice

import org.junit.Assert.*
import org.junit.Test

class OrderRulesTest {
    @Test fun advancesThroughKnownStages() {
        for (stage in 0..4) assertEquals(stage + 1, OrderRules.nextStage(stage))
        assertNull(OrderRules.nextStage(5))
        assertNull(OrderRules.nextStage(-1))
        assertNull(OrderRules.nextStage(99))
    }

    @Test fun rejectsInvalidOrders() {
        assertFalse(OrderRules.validNewOrder("", "Largus", "Шум", "100"))
        assertFalse(OrderRules.validNewOrder("Иван", "", "Шум", "100"))
        assertFalse(OrderRules.validNewOrder("Иван", "Largus", "", "100"))
        assertFalse(OrderRules.validNewOrder("Иван", "Largus", "Шум", "-1"))
        assertFalse(OrderRules.validNewOrder("Иван", "Largus", "Шум", "1.5"))
        assertFalse(OrderRules.validNewOrder("Иван", "Largus", "Шум", "999999999999999999999"))
        assertTrue(OrderRules.validNewOrder("Иван", "Largus", "Шум", "0"))
    }

    @Test fun unknownStageIsSafe() {
        assertEquals("Неизвестный этап", OrderRules.stageLabel(100))
    }
}
