package com.msa.rental.rental.framework.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserItemInputDto {

  private String userId;
  private String userName;
  private Integer itemId;
  private String itemTitle;
}
