package tommy.study.batch.lecture.springbatchlecture.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public abstract class MyJobTestDto {

  @Getter
  public static class CreateDto {
    @NotBlank private String name;
    @NotNull private LocalDateTime createdAt;
    @NotNull private String createdBy;

    @Builder
    private CreateDto(String name, LocalDateTime createdAt, String createdBy) {
      this.name = name;
      this.createdAt = createdAt;
      this.createdBy = createdBy;
    }
  }

  @Getter
  public static class CreateResponseDto {
    @NotNull private Long id;
    @NotBlank private String name;

    @Builder
    private CreateResponseDto(Long id, String name) {
      this.id = id;
      this.name = name;
    }
  }
}
