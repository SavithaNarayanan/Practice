package com.app.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Account.Builder.class)
public class Account {

    private String number;
    private String subAccountNumber;


    public Account() {
    }

    public Account(Builder builder) {
        this.number = builder.number;
        this.subAccountNumber = builder.subAccountNumber;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {

        private String number;
        private String subAccountNumber;

        public Builder self() {
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder subAccountNumber(String subAccountNumber) {
            this.subAccountNumber = subAccountNumber;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    public String getNumber() {
        return number;
    }

    public String getSubAccountNumber() {
        return subAccountNumber;
    }
}
