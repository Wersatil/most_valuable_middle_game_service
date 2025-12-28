package most_valuable_middle.game_service.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "game")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Здесь пока сделали так, потому что ооочень не хотим собирать статистику по игрокам, но если вдруг придется, чтобы
    // потом можно было накатить миграцию, добавить сюда полей, и в путь. Больше ничего не менять, только тут добавить
    // поля. Но пока то, что мы не собираем статистику - это наш плюс, никто не будет переживать.
}
