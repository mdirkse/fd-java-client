package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public class Team extends SimpleTeam {
    public final Area area;
    public final String shortName;
    public final String tla;
    public final String address;
    public final String phone;
    public final String website;
    public final String email;
    public final int founded;
    public final String clubColors;
    public final String venue;
    public final Collection<Player> squad;
    public final Optional<LocalDateTime> lastUpdated;

    public Team(@JsonProperty("id")
                        int id,
                @JsonProperty("name")
                        String name,
                @JsonProperty("area")
                        Area area,
                @JsonProperty("shortName")
                        String shortName,
                @JsonProperty("tla")
                        String tla,
                @JsonProperty("address")
                        String address,
                @JsonProperty("phone")
                        String phone,
                @JsonProperty("website")
                        String website,
                @JsonProperty("email")
                        String email,
                @JsonProperty(value = "founded", defaultValue = "-1")
                        int founded,
                @JsonProperty("clubColors")
                        String clubColors,
                @JsonProperty("venue")
                        String venue,
                @JsonProperty("squad")
                        Collection<Player> squad,
                @JsonProperty("lastUpdated")
                @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                        LocalDateTime lastUpdated) {
        super(id, name);
        this.area = area;
        this.shortName = Utils.getValueOrEmptyString(shortName);
        this.tla = Utils.getValueOrEmptyString(tla);
        this.address = Utils.getValueOrEmptyString(address);
        this.phone = Utils.getValueOrEmptyString(phone);
        this.website = Utils.getValueOrEmptyString(website);
        this.email = Utils.getValueOrEmptyString(email);
        this.founded = founded;
        this.clubColors = Utils.getValueOrEmptyString(clubColors);
        this.venue = Utils.getValueOrEmptyString(venue);
        this.squad = Utils.getUnmodifiableOrEmptyCollection(squad);
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
