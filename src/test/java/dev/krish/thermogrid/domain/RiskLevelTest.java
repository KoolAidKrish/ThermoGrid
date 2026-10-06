package dev.krish.thermogrid.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RiskLevelTest {

    @Test
    void higherLevelIsAtLeastLowerLevel() {
        assertThat(RiskLevel.HIGH.isAtLeast(RiskLevel.MODERATE)).isTrue();
    }

    @Test
    void levelIsAtLeastItself() {
        assertThat(RiskLevel.HIGH.isAtLeast(RiskLevel.HIGH)).isTrue();
    }

    @Test
    void lowerLevelIsNotAtLeastHigherLevel() {
        assertThat(RiskLevel.HIGH.isAtLeast(RiskLevel.CRITICAL)).isFalse();
    }
}
