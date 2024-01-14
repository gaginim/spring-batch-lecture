package com.msa.rental.rental.framework.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInputDto {
  private String userId;
  private String userName;

  public UserInputDto(String userId, String userName) {
    this.userId = userId;
    this.userName = userName;
  }
}
