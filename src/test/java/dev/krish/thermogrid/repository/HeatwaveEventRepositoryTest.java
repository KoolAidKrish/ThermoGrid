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
        var events = repository.findProjected(null);

        assertThat(events).hasSize(10);
        assertThat(events).allMatch(HeatwaveEvent::isProjected);
    }

    @Test
    void findProjected_forYear_returnsOnlyThatYear() {
        var events = repository.findProjected(2035);

        assertThat(events).hasSize(5);
        assertThat(events).extracting(HeatwaveEvent::getTargetYear).containsOnly(2035);
    }

    @Test
    void findProjected_joinsEachEventToItsRegion() {
        var events = repository.findProjected(2028);

        assertThat(events).extracting(e -> e.getRegion().getName())
            .containsExactlyInAnyOrder("Edmonton", "Calgary", "Red Deer", "Lethbridge", "Fort McMurray");
    }

    @Test
    void findProjected_forUnknownYear_returnsEmpty() {
        assertThat(repository.findProjected(2099)).isEmpty();
    }
}
