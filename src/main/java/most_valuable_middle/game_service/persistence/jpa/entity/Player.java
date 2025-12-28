package most_valuable_middle.game_service.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Integer id;
    private String name;
    private String avatar;
    private Integer offerSum = 0;
}
