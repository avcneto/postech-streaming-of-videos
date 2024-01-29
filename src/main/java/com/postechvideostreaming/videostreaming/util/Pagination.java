package com.postechvideostreaming.videostreaming.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {
  private List<T> results;
  public Integer limit;
  private Integer offset;
  private Long total;
}
