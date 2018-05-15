package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class CompetitionList {
    public final int count;
    public final Optional<Filters> filters;
    public final Collection<Competition> competitions;

    public CompetitionList(@JsonProperty("count") int count,
                           @JsonProperty("filters") Filters filters,
                           @JsonProperty("competitions") Collection<Competition> competitions) {
        this.count = count;
        this.filters = Optional.ofNullable(filters);
        this.competitions = Collections.unmodifiableCollection(new ArrayList<>(competitions));
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
