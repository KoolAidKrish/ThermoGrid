package dev.krish.thermogrid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "heatwave_events")
public class HeatwaveEvent {

    @Id
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "target_year")
    private int targetYear;

    @Column(name = "peak_heat_index")
    private double peakHeatIndex;

    @Column(name = "is_projected")
    private boolean projected;

    protected HeatwaveEvent() {
        // required by JPA
    }

    public HeatwaveEvent(Long id, Region region, int targetYear, double peakHeatIndex, boolean projected) {
        this.id = id;
        this.region = region;
        this.targetYear = targetYear;
        this.peakHeatIndex = peakHeatIndex;
        this.projected = projected;
    }

    // getters only

    public Long getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public double getPeakHeatIndex() {
        return peakHeatIndex;
    }

    public boolean isProjected() {
        return projected;
    }
}
