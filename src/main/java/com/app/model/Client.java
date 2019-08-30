package com.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Client.Builder.class)
public class Client {

    private String type;
    private String number;

    public Client() {
    }

    public Client(Builder builder) {
        this.type = builder.type;
        this.number = builder.number;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {

        private String type;
        private String number;

        public Builder self() {
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
}
