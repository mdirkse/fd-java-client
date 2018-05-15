package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Optional;

public class Player extends MatchPlayer {
    public final String position;
    public final int jerseyNumber;
    public final Optional<LocalDate> dateOfBirth;
    public final String countryOfBirth;
    public final String nationality;
    public final String contractUntil;
    public final String height;
    public final String preferredFoot;

    public Player(@JsonProperty("id")
                          int id,
                  @JsonProperty("name")
                          String name,
                  @JsonProperty("position")
                          String position,
                  @JsonProperty("jerseyNumber")
                          int jerseyNumber,
                  @JsonProperty("dateOfBirth")
                  @JsonDeserialize(using = LocalDateDeserializer.class)
                          LocalDate dateOfBirth,
                  @JsonProperty("countryOfBirth")
                          String countryOfBirth,
                  @JsonProperty("nationality")
                          String nationality,
                  @JsonProperty("contractUntil")
                          String contractUntil,
                  @JsonProperty("height")
                          String height,
                  @JsonProperty("preferredFoot")
                          String preferredFoot) {
        super(id, name);
        this.position = Utils.getValueOrEmptyString(position);
        this.jerseyNumber = jerseyNumber;
        this.dateOfBirth = Optional.ofNullable(dateOfBirth);
        this.countryOfBirth = Utils.getValueOrEmptyString(countryOfBirth);
        this.nationality = Utils.getValueOrEmptyString(nationality);
        this.contractUntil = Utils.getValueOrEmptyString(contractUntil);
        this.height = Utils.getValueOrEmptyString(height);
        this.preferredFoot = Utils.getValueOrEmptyString(preferredFoot);
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
