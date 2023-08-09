package com.github.genraven1.toolrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Tool {
    private String code;

    private Type type;

    private Brand brand;

    @AllArgsConstructor
    @Getter
    public enum Type {
        CHAINSAW("Chainsaw", 1.49, true, false, true),
        JACKHAMMER("Jackhammer", 2.99, true, false, false),
        LADDER("Ladder", 1.99, true, true, false);

        private final String label;

        private final double charge;

        private final boolean weekday;

        private final boolean weekend;

        private final boolean holiday;
    }

    @AllArgsConstructor
    @Getter
    public enum Brand {
        STIHL("Stihl"), WERNER("Werner"), DEWALT("DeWalt"), RIDGID("Ridgid");

        private final String label;
    }
}
