package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collection;
import java.util.Optional;

public class MatchTeam extends SimpleTeam {
    public final Optional<Coach> coach;
    public final Optional<MatchPlayer> captain;
    public final Collection<MatchPlayer> lineup;

    public MatchTeam(@JsonProperty("id") int id,
                     @JsonProperty("name") String name,
                     @JsonProperty("coach") Coach coach,
                     @JsonProperty("captain") MatchPlayer captain,
                     @JsonProperty("lineup") Collection<MatchPlayer> lineup) {
        super(id, name);
        this.coach = Optional.ofNullable(coach);
        this.captain = Optional.ofNullable(captain);
        this.lineup = Utils.getUnmodifiableOrEmptyCollection(lineup);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }


    public static class Coach {
        public final int id;
        public final String name;
        public final String countryOfBirth;
        public final String nationality;

        public Coach(@JsonProperty("id") int id,
                     @JsonProperty("name") String name,
                     @JsonProperty("countryOfBirth") String countryOfBirth,
                     @JsonProperty("nationality") String nationality) {
            this.id = id;
            this.name = Utils.getValueOrEmptyString(name);
            this.countryOfBirth = Utils.getValueOrEmptyString(countryOfBirth);
            this.nationality = Utils.getValueOrEmptyString(nationality);
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
}
