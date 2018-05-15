package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Optional;

public class Competition {
    public final int id;
    public final Optional<Area> area;
    public final String name;
    public final String code;
    public final Season currentSeason;
    public final int numberOfAvailableSeasons;
    public final Optional<LocalDateTime> lastUpdated;

    public Competition(@JsonProperty("id")
                               int id,
                       @JsonProperty("area")
                               Area area,
                       @JsonProperty("name")
                               String name,
                       @JsonProperty("code")
                               String code,
                       @JsonProperty("currentSeason")
                               Season currentSeason,
                       @JsonProperty("numberOfAvailableSeasons")
                               int numberOfAvailableSeasons,
                       @JsonProperty("lastUpdated")
                       @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                               LocalDateTime lastUpdated) {
        this.id = id;
        this.area = Optional.ofNullable(area);
        this.name = name;
        this.code = Utils.getValueOrEmptyString(code);
        this.currentSeason = currentSeason;
        this.numberOfAvailableSeasons = numberOfAvailableSeasons;
        this.lastUpdated = Optional.ofNullable(lastUpdated);
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
