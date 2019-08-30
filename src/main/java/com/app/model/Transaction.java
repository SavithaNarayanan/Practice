package com.app.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class Transaction {

    private String longSign;
    private Long _long;
    private String shortSign;
    private Long _short;

    public Transaction() {
    }

    public Transaction(Builder builder) {
        this.longSign = builder.longSign;
        this._long = builder._long;
        this.shortSign = builder.shortSign;
        this._short = builder._short;
    }

    public String getLongSign() {
        return longSign;
    }

    public Long get_long() {
        return _long;
    }

    public String getShortSign() {
        return shortSign;
    }

    public Long get_short() {
        return _short;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private String longSign;
        private Long _long;
        private String shortSign;
        private Long _short;

        public Builder longSign(String longSign) {
            this.longSign = longSign;
            return this;
        }

        public Builder _long(Long aLong) {
            this._long = aLong;
            return this;
        }

        public Builder shortSign(String shortSign) {
            this.shortSign = shortSign;
            return this;
        }

        public Builder _short(Long aShort) {
            this._short = aShort;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
