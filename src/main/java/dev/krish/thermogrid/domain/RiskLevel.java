package dev.krish.thermogrid.domain;

/**
 * Ordered from least to most severe. Declaration order matters: {@link #isAtLeast}
 * relies on {@code compareTo}, so {@code minRiskLevel=HIGH} matches HIGH and CRITICAL.
 */
public enum RiskLevel {
    LOW, MODERATE, HIGH, CRITICAL;

    public boolean isAtLeast(RiskLevel other) {
        return this.compareTo(other) >= 0;
    }
}
