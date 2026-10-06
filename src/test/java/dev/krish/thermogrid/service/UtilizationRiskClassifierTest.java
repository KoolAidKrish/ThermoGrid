package dev.krish.thermogrid.service;

import dev.krish.thermogrid.domain.RiskLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UtilizationRiskClassifierTest {

    private final RiskClassifier classifier = new UtilizationRiskClassifier();

    @ParameterizedTest(name = "utilization {0} -> {1}")
    @CsvSource({
        "0.00, LOW",
        "0.84, LOW",
        "0.85, MODERATE",
        "0.99, MODERATE",
        "1.00, HIGH",
        "1.14, HIGH",
        "1.15, CRITICAL",
        "2.00, CRITICAL"
    })
    void classifiesAtBoundaries(double utilization, RiskLevel expected) {
        assertThat(classifier.classify(utilization)).isEqualTo(expected);
    }
}
