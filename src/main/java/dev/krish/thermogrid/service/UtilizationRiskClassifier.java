package dev.krish.thermogrid.service;

import dev.krish.thermogrid.domain.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class UtilizationRiskClassifier implements RiskClassifier {

    @Override
    public RiskLevel classify(double u) {
        if (u >= 1.15) return RiskLevel.CRITICAL;
        if (u >= 1.00) return RiskLevel.HIGH;
        if (u >= 0.85) return RiskLevel.MODERATE;
        return RiskLevel.LOW;
    }
}
