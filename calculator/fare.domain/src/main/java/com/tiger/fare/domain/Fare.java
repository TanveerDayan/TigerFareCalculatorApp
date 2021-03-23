package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.nonNegative;

import java.util.Objects;

public class Fare {

  private int cost;

  private Fare(int cost) {
    nonNegative(cost, "Cost cannot be negative.");
    this.cost = cost;
  }

  public int cost() {
    return this.cost;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Fare{");
    sb.append("cost=").append(cost);
    sb.append('}');
    return sb.toString();
  }

  public static class Builder {
    private int cost;

    public Builder withCost(int cost) {
      this.cost = cost;
      return this;
    }

    public Fare build() {
      return new Fare(this.cost);
    }
  }

  public Fare add(Fare fare) {
    return new Builder().withCost(cost() + fare.cost()).build();
  }
}
