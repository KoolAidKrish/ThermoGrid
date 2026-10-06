package dev.krish.thermogrid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name = "region_id")
    private Long id;

    private String name;

    private int population;

    @Column(name = "base_grid_capacity_mw")
    private double baseGridCapacityMw;

    protected Region() {
        // required by JPA
    }

    public Region(Long id, String name, int population, double baseGridCapacityMw) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.baseGridCapacityMw = baseGridCapacityMw;
    }

    // getters only; entities are read-only here

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public double getBaseGridCapacityMw() {
        return baseGridCapacityMw;
    }
}
