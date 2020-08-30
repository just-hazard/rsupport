package just.hazard.rsupport.mapper;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.LoginDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper extends EntityMapper<LoginDto, Account>{
}
