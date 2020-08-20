package just.hazard.rsupport.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NoticeListDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate updateDate;
}
