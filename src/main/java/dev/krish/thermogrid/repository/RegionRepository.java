package dev.krish.thermogrid.repository;

import dev.krish.thermogrid.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
