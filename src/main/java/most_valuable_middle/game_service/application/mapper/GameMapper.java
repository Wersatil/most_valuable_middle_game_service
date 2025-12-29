package most_valuable_middle.game_service.application.mapper;

import most_valuable_middle.game_service.persistence.jpa.entity.Game;
import most_valuable_middle.game_service.persistence.redis.model.GameModel;
import most_valuable_middle.game_service.web.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GameMapper {
    GameDto toGameDto(GameModel game);
}
