package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collection;
import java.util.Optional;

public class MatchList {
    public final int count;
    public final Optional<Filters> filters;
    public final Optional<Season> season;
    public final Collection<Match> matches;

    public MatchList(@JsonProperty("count") int count,
                     @JsonProperty("filters") Filters filters,
                     @JsonProperty("season") Season season,
                     @JsonProperty("matches") Collection<Match> matches) {
        this.count = count;
        this.filters = Optional.ofNullable(filters);
        this.season = Optional.ofNullable(season);
        this.matches = Utils.getUnmodifiableOrEmptyCollection(matches);
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
