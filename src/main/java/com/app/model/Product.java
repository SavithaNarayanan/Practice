package com.app.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Product.Builder.class)
public class Product {
    private String exchangeCode;
    private String groupCode;
    private String symbol;
    private String expirationDate;

    public Product() {
    }

    public Product(Builder builder) {
        this.exchangeCode = builder.exchangeCode;
        this.groupCode = builder.groupCode;
        this.symbol = builder.symbol;
        this.expirationDate = builder.expirationDate;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private String exchangeCode;
        private String groupCode;
        private String symbol;
        private String expirationDate;

        public Builder exchangeCode(String exchangeCode) {
            this.exchangeCode = exchangeCode;
            return this;
        }

        public Builder groupCode(String groupCode) {
            this.groupCode = groupCode;
            return this;
        }

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder expirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
