package dev.krish.thermogrid.config;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Tunable model constants, bound from {@code thermogrid.*} in application.properties.
 * Validated at startup so a bad value fails fast instead of producing wrong numbers.
 *
 * @param heatThreshold         heat index above which grid stress begins
 * @param perCapitaDemandKw     baseline demand per resident, in kW
 * @param demandGrowthPerDegree fractional demand increase per degree over the threshold
 * @param deratePerDegree       fractional capacity loss per degree over the threshold
 * @param minCapacityFactor     floor on the capacity factor, however hot it gets
 */
@Validated
@ConfigurationProperties(prefix = "thermogrid")
public record GridStressProperties(
    double heatThreshold,
    @Positive double perCapitaDemandKw,
    @PositiveOrZero double demandGrowthPerDegree,
    @PositiveOrZero double deratePerDegree,
    @Positive @DecimalMax("1.0") double minCapacityFactor) {
}
