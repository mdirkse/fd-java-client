package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Optional;

public class Season {
    public final int id;
    public final Optional<LocalDate> startDate;
    public final Optional<LocalDate> endDate;
    public final int currentMatchday;

    public Season(@JsonProperty("id")
                          int id,
                  @JsonProperty("startDate")
                  @JsonDeserialize(using = LocalDateDeserializer.class)
                          LocalDate startDate,
                  @JsonProperty("endDate")
                  @JsonDeserialize(using = LocalDateDeserializer.class)
                          LocalDate endDate,
                  @JsonProperty(value = "currentMatchday")
                          int currentMatchday) {
        this.id = id;
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
        this.currentMatchday = currentMatchday;
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
