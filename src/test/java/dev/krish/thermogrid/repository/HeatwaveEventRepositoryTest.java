package dev.krish.thermogrid.repository;

import dev.krish.thermogrid.entity.HeatwaveEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HeatwaveEventRepositoryTest {

    @Autowired
    private HeatwaveEventRepository repository;

    @Test
    void findProjected_withoutYear_excludesHistoricalBenchmark() {
        var events = repository.findProjected(null, null);

        assertThat(events).hasSize(10);
        assertThat(events).allMatch(HeatwaveEvent::isProjected);
    }

    @Test
    void findProjected_forYear_returnsOnlyThatYear() {
        var events = repository.findProjected(2035, null);

        assertThat(events).hasSize(5);
        assertThat(events).extracting(HeatwaveEvent::getTargetYear).containsOnly(2035);
    }

    @Test
    void findProjected_joinsEachEventToItsRegion() {
        var events = repository.findProjected(2028, null);

        assertThat(events).extracting(e -> e.getRegion().getName())
            .containsExactlyInAnyOrder("Edmonton", "Calgary", "Red Deer", "Lethbridge", "Fort McMurray");
    }

    @Test
    void findProjected_forUnknownYear_returnsEmpty() {
        assertThat(repository.findProjected(2099, null)).isEmpty();
    }

    @Test
    void findProjected_forRegion_returnsOnlyThatRegionsProjectedEvents() {
        var events = repository.findProjected(null, 1L);

        // Edmonton has three events; the 2026 one is the historical benchmark.
        assertThat(events).extracting(HeatwaveEvent::getTargetYear).containsExactlyInAnyOrder(2028, 2035);
        assertThat(events).extracting(e -> e.getRegion().getName()).containsOnly("Edmonton");
    }

    @Test
    void findProjected_forRegionAndYear_combinesFilters() {
        assertThat(repository.findProjected(2035, 2L)).singleElement()
            .satisfies(e -> assertThat(e.getRegion().getName()).isEqualTo("Calgary"));
    }
}
