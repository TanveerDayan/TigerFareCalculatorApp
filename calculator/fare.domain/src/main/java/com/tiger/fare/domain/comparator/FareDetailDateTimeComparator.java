package com.tiger.fare.domain.comparator;

import com.tiger.fare.domain.FareDetail;
import java.util.Comparator;

/** User for FareDetail Comparision by dateTime. */
public class FareDetailDateTimeComparator implements Comparator<FareDetail> {

  @Override
  public int compare(FareDetail o1, FareDetail o2) {
    return o1.journey().dateTime().dateTime().compareTo(o2.journey().dateTime().dateTime());
  }
}
