package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class Filters {
    public final Collection<String> areas;
    public final Collection<MatchStage> stages;
    public final Collection<MatchStatus> status;
    public final int matchday;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public final Optional<LocalDate> dateFrom;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public final Optional<LocalDate> dateTo;

    public Filters(@JsonProperty("areas") Collection<String> areas,
                   @JsonProperty("stage") Collection<MatchStage> stages,
                   @JsonProperty("status") Collection<MatchStatus> status,
                   @JsonProperty(value = "matchday", defaultValue = "-1") int matchday,
                   @JsonProperty("dateFrom") LocalDate dateFrom,
                   @JsonProperty("dateTo") LocalDate dateTo) {
        this.areas = Utils.getUnmodifiableOrEmptyCollection(areas);
        this.stages = Utils.getUnmodifiableOrEmptyCollection(stages);
        this.status = Utils.getUnmodifiableOrEmptyCollection(status);
        this.matchday = matchday;
        this.dateFrom = Optional.ofNullable(dateFrom);
        this.dateTo = Optional.ofNullable(dateTo);
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