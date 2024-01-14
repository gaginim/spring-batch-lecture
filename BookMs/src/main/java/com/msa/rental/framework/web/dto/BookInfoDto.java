package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.vo.Classification;
import com.msa.rental.domain.model.vo.Location;
import com.msa.rental.domain.model.vo.Source;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BookInfoDto {

    private String title;
    private String author;
    private String isbn;
    private String description;
    private LocalDateTime publicationDate;
    private Source source;
    private Classification classification;
    private Location location;
}
