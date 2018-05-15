package org.footballdata.v2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class Match {
    public final int id;
    public final Optional<LocalDate> utcDate;
    public final MatchStatus status;
    public final int matchday;
    public final MatchStage stage;
    public final MatchTeam homeTeam;
    public final MatchTeam awayTeam;
    public final Score score;
    public final Collection<Goals> goals;
    public final Collection<Booking> bookings;
    public final Collection<Substitution> substitutions;

    public Match(@JsonProperty("id")
                         int id,
                 @JsonProperty("utcDate")
                 @JsonDeserialize(using = LocalDateDeserializer.class)
                         LocalDate utcDate,
                 @JsonProperty("status")
                         MatchStatus status,
                 @JsonProperty("matchday")
                         int matchday,
                 @JsonProperty("stage")
                         MatchStage stage,
                 @JsonProperty("homeTeam")
                         MatchTeam homeTeam,
                 @JsonProperty("awayTeam")
                         MatchTeam awayTeam,
                 @JsonProperty("score")
                         Score score,
                 @JsonProperty("goals")
                         Collection<Goals> goals,
                 @JsonProperty("bookings")
                         Collection<Booking> bookings,
                 @JsonProperty("substitutions")
                         Collection<Substitution> substitutions) {
        this.id = id;
        this.utcDate = Optional.ofNullable(utcDate);
        this.status = status;
        this.matchday = matchday;
        this.stage = stage;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.goals = Utils.getUnmodifiableOrEmptyCollection(goals);
        this.bookings = Utils.getUnmodifiableOrEmptyCollection(bookings);
        this.substitutions = Utils.getUnmodifiableOrEmptyCollection(substitutions);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    public static class Score {
        public final MatchDuration duration;
        public final GoalTotal halfTime;
        public final GoalTotal fullTime;
        public final GoalTotal extraTime;
        public final GoalTotal penalties;

        public Score(@JsonProperty("duration") MatchDuration duration,
                     @JsonProperty("halfTime") GoalTotal halfTime,
                     @JsonProperty("fullTime") GoalTotal fullTime,
                     @JsonProperty("extraTime") GoalTotal extraTime,
                     @JsonProperty("penalties") GoalTotal penalties) {
            this.duration = duration;
            this.halfTime = halfTime;
            this.fullTime = fullTime;
            this.extraTime = extraTime;
            this.penalties = penalties;
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

    public static class GoalTotal {
        public final int homeTeam;
        public final int awayTeam;

        public GoalTotal(@JsonProperty("homeTeam") int homeTeam,
                         @JsonProperty("awayTeam") int awayTeam) {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
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

    public static class Goals {
        public final int minute;
        public final MatchPlayer scorer;
        public final Collection<MatchPlayer> assists;

        public Goals(@JsonProperty("minute") int minute,
                     @JsonProperty("scorer") MatchPlayer scorer,
                     @JsonProperty("assists") Collection<MatchPlayer> assists) {
            this.minute = minute;
            this.scorer = scorer;
            this.assists = Utils.getUnmodifiableOrEmptyCollection(assists);
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

    public enum Card {
        RED_CARD,
        YELLOW_CARD
    }

    public static class Booking {
        public final int minute;
        public final SimpleTeam team;
        public final MatchPlayer player;
        public final Card card;

        public Booking(@JsonProperty("minute") int minute,
                       @JsonProperty("team") SimpleTeam team,
                       @JsonProperty("player") MatchPlayer player,
                       @JsonProperty("card") Card card) {
            this.minute = minute;
            this.player = player;
            this.team = team;
            this.card = card;
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

    public static class Substitution {
        public final int minute;
        public final SimpleTeam team;
        public final MatchPlayer playerOut;
        public final MatchPlayer playerIn;

        public Substitution(@JsonProperty("minute") int minute,
                            @JsonProperty("team") SimpleTeam team,
                            @JsonProperty("playerOut") MatchPlayer playerOut,
                            @JsonProperty("playerIn") MatchPlayer playerIn) {
            this.minute = minute;
            this.team = team;
            this.playerOut = playerOut;
            this.playerIn = playerIn;
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
