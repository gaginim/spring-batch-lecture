package com.msa.rental.rental.framework.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ClearOverDueInfoDto {
  private String userId;
  private Long points;
}
