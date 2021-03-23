package com.tiger.fare.domain.comparator;


import com.tiger.fare.domain.ZoneTravelDetail;
import java.util.Comparator;

/**
 * Compares in reverse. Used for ZoneTravelDetail comparisons by distance between Zone. Calculated
 * by zone number comparison
 */
public class ZoneTravelDetailComparator implements Comparator<ZoneTravelDetail> {

  @Override
  public int compare(ZoneTravelDetail zoneTravelDetail1, ZoneTravelDetail zoneTravelDetail2) {
    return -Integer.compare(zoneTravelDetail1.distance(), zoneTravelDetail2.distance());
  }
}
