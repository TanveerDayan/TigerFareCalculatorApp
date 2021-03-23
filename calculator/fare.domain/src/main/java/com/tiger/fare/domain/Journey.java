package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.notNull;

import java.time.LocalDate;

/** Journey contains the journey details with dateTime and zoneTravelDetails. */
public final class Journey {

  private DateTime dateTime;
  private ZoneTravelDetail zoneTravelDetail;

  private Journey(DateTime dateTime, ZoneTravelDetail zoneTravelDetail) {
    notNull(dateTime, "Invalid dateTime");
    notNull(zoneTravelDetail, "Invalid from ZoneTravelDetail");

    this.dateTime = dateTime;
    this.zoneTravelDetail = zoneTravelDetail;
  }

  public DateTime dateTime() {
    return dateTime;
  }

  public Date localDate() {
    return dateTime.date();
  }

  public ZoneTravelDetail zoneTravelDetail() {
    return zoneTravelDetail;
  }

  public static class Builder {

    private DateTime dateTime;
    private ZoneTravelDetail zoneTravelDetail;

    public Builder withDateTime(DateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    public Builder withZoneTravelDetail(ZoneTravelDetail zoneTravelDetail) {
      this.zoneTravelDetail = zoneTravelDetail;
      return this;
    }

    public Journey build() {
      return new Journey(this.dateTime, this.zoneTravelDetail);
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Journey{");
    sb.append("dateTime=").append(dateTime);
    sb.append(", zoneTravelDetail=").append(zoneTravelDetail);
    sb.append('}');
    return sb.toString();
  }
}
