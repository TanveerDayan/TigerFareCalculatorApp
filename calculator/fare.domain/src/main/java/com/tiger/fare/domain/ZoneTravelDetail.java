package com.tiger.fare.domain;

import static com.tiger.fare.domain.util.Validate.nonNegative;
import static com.tiger.fare.domain.util.Validate.notNull;

/** Details of travel from Zone to Zone. */
public class ZoneTravelDetail {

  private int from;
  private int to;

  private ZoneTravelDetail(int from, int to) {

    nonNegative(from, "Invalid from Zone");
    nonNegative(to, "Invalid To Zone");

    this.from = from;
    this.to = to;
  }

  public int from() {
    return from;
  }

  public int to() {
    return to;
  }

  public static class Builder {

    private int from;
    private int to;

    public Builder withFromZone(int from) {
      this.from = from;
      return this;
    }

    public Builder withToZone(int to) {
      this.to = to;
      return this;
    }

    public ZoneTravelDetail build() {
      return new ZoneTravelDetail(from, to);
    }
  }

  public int distance() {
    return Math.abs(from - to);
  }

  public ZoneTravelDetail newZoneTravelDetail() {
    return new Builder().withFromZone(from()).withToZone(to()).build();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ZoneTravelDetail{");
    sb.append("from=").append(from);
    sb.append(", to=").append(to);
    sb.append('}');
    return sb.toString();
  }
}
