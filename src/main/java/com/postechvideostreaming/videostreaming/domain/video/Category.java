package com.postechvideostreaming.videostreaming.domain.video;

import com.postechvideostreaming.videostreaming.exception.BadRequestException;

import java.util.Arrays;

import static java.lang.String.format;

public enum Category {
  ACTION,
  ADVENTURE,
  COMEDY,
  DRAMA,
  SCIENCE_FICTION,
  HORROR,
  ANIMATION,
  DOCUMENTARY,
  ROMANCE,
  SUSPENSE,
  MUSICAL,
  FANTASY,
  CRIME,
  HISTORICAL,
  OTHER;

  private static final String INVALID_CATEGORY = "invalid category, the valid categories are: %s";

  public static Category convertStringToCategory(String str) {
    try {
      return Category.valueOf(str.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new BadRequestException(format(INVALID_CATEGORY, Arrays.toString(Category.values())));
    }
  }
}
