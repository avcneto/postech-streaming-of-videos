package com.postechvideostreaming.videostreaming.domain.video;

import com.postechvideostreaming.videostreaming.exception.BadRequestException;

import java.util.Arrays;

import static java.lang.String.format;

public enum Order {
  ASC,
  DESC;

  private static final String INVALID_SORT = "invalid Sort, the valid Sort are: %s";

  public static Order convertStringToOrder(String str) {
    try {
      return Order.valueOf(str.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new BadRequestException(format(INVALID_SORT, Arrays.toString(Order.values())));
    }
  }

}
