package dev.krish.thermogrid.service;

/** Thrown when a query names a region that does not exist. Mapped to 404 by the web layer. */
public class RegionNotFoundException extends RuntimeException {

    private final long regionId;

    public RegionNotFoundException(long regionId) {
        super("No region with id " + regionId + ".");
        this.regionId = regionId;
    }

    public long getRegionId() {
        return regionId;
    }
}
