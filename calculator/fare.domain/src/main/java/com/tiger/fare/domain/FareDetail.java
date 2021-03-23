package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.notNull;

/** Contains the complete fareDetails like fare and journey. */
public class FareDetail {

  private Fare fare;
  private Journey journey;

  private FareDetail(Fare fare, Journey journey) {
    notNull(fare, "fare cannot be null.");
    notNull(journey, "journey cannot be null.");
    this.fare = fare;
    this.journey = journey;
  }

  public Fare fare() {
    return fare;
  }

  public Journey journey() {
    return journey;
  }

  public static class Builder {
    private Fare fare;
    private Journey journey;

    public Builder withFare(Fare fare) {
      this.fare = fare;
      return this;
    }

    public Builder withJourney(Journey journey) {
      this.journey = journey;
      return this;
    }

    public FareDetail build() {
      return new FareDetail(fare, journey);
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FareDetail{");
    sb.append("fare=").append(fare);
    sb.append(", journey=").append(journey);
    sb.append('}');
    return sb.toString();
  }

  public FareDetail newFareDetail(int cost) {
    return new Builder()
        .withJourney(
            new Journey.Builder()
                .withDateTime(journey().dateTime())
                .withZoneTravelDetail(journey().zoneTravelDetail().newZoneTravelDetail())
                .build())
        .withFare(new Fare.Builder().withCost(cost).build())
        .build();
  }

  public FareDetail newFareDetail() {
    return new Builder()
        .withJourney(
            new Journey.Builder()
                .withDateTime(journey().dateTime())
                .withZoneTravelDetail(journey().zoneTravelDetail().newZoneTravelDetail())
                .build())
        .withFare(new Fare.Builder().withCost(fare().cost()).build())
        .build();
  }

  public static FareDetail from(Journey journey, Fare fare) {
    return new Builder().withJourney(journey).withFare(fare).build();
  }
}
