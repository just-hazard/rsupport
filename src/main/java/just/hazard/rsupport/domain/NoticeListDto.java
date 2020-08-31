package just.hazard.rsupport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.time.LocalDate;


public class NoticeListDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate updateDate;

    @JsonBackReference
    private Account user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
