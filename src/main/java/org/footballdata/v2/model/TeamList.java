package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collection;
import java.util.Optional;

public class TeamList {
    public final int count;
    public final Optional<Filters> filters;
    public final Collection<Team> teams;

    public TeamList(@JsonProperty("count") int count,
                    @JsonProperty("filters") Filters filters,
                    @JsonProperty("teams") Collection<Team> teams) {
        this.count = count;
        this.filters = Optional.ofNullable(filters);
        this.teams = Utils.getUnmodifiableOrEmptyCollection(teams);
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
