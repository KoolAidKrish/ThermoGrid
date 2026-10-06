package dev.krish.thermogrid.service;

import dev.krish.thermogrid.domain.RiskLevel;

/** Strategy for turning utilization (demand / effective capacity) into a risk label. */
public interface RiskClassifier {

    RiskLevel classify(double utilization);
}
