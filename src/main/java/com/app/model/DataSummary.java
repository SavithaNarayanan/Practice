package com.app.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DataSummary.Builder.class)
public class DataSummary {
    private Account account;
    private Client client;
    private Product product;
    private Transaction transaction;

    public DataSummary() {
    }

    public DataSummary(Builder builder) {
        this.account = builder.account;
        this.client = builder.client;
        this.product = builder.product;
        this.transaction = builder.transaction;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private Account account;
        private Client client;
        private Product product;
        private Transaction transaction;

        public Builder self() {
            return this;
        }

        public Builder account(Account account) {
            this.account = account;
            return this;
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Builder transaction(Transaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public DataSummary build() {
            return new DataSummary(this);
        }
    }

    public Account getAccount() {
        return account;
    }

    public Client getClient() {
        return client;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Product getProduct() {
        return product;
    }
}
