package fr.nathanpasdutout.riotapi.elements.league;

public class Rank {

    public static final String SOLO_QUEUE_TYPE = "RANKED_SOLO_5x5";
    public static final String FLEX_QUEUE_TYPE = "RANKED_FLEX_SR";

    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String puuid;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;

    public String getLeagueId() {
        return this.leagueId;
    }

    public String getQueueType() {
        return this.queueType;
    }

    public String getTier() {
        return this.tier;
    }

    public String getRank() {
        return this.rank;
    }

    public String getPUUID() {
        return this.puuid;
    }

    public int getLeaguePoints() {
        return this.leaguePoints;
    }

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public boolean isVeteran() {
        return this.veteran;
    }

    public boolean isInactive() {
        return this.inactive;
    }

    public boolean isFreshBlood() {
        return this.freshBlood;
    }

    public boolean isHotStreak() {
        return this.hotStreak;
    }

    public static String getQueueName(String queueType) {
        return switch(queueType) {
            case SOLO_QUEUE_TYPE -> "Solo-Q";
            case FLEX_QUEUE_TYPE -> "Flex";
            default -> "Unknown";
        };
    }
}
