package dev.krish.thermogrid.repository;

import dev.krish.thermogrid.entity.HeatwaveEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeatwaveEventRepository extends JpaRepository<HeatwaveEvent, Long> {

    /**
     * Projected events only, optionally for one year and/or one region. {@code join fetch}
     * loads each region in the same query, so mapping the results never triggers N+1 selects.
     */
    @Query("""
        select e from HeatwaveEvent e join fetch e.region r
        where e.projected = true
          and (:year is null or e.targetYear = :year)
          and (:regionId is null or r.id = :regionId)
        """)
    List<HeatwaveEvent> findProjected(@Param("year") Integer year, @Param("regionId") Long regionId);
}
