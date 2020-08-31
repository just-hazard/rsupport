package just.hazard.rsupport.mapper;

import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.domain.NoticeListDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticeListMapper extends EntityMapper<NoticeListDto, Notice>{
}
