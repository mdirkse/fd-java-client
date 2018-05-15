package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Area {
    public final int id;
    public final String name;
    public final String countryCode;
    public final int parentAreaId;
    public final String parentArea;

    public Area(@JsonProperty("id") int id,
                @JsonProperty("name") String name,
                @JsonProperty("countryCode") String countryCode,
                @JsonProperty("parentAreaId") int parentAreaId,
                @JsonProperty("parentArea") String parentArea) {
        this.id = id;
        this.name = Utils.getValueOrEmptyString(name);
        this.countryCode = Utils.getValueOrEmptyString(countryCode);
        this.parentAreaId = parentAreaId;
        this.parentArea = Utils.getValueOrEmptyString(parentArea);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }
}
