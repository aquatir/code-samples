package helloworld_backend.dto;

import helloworld_backend.domain.Hero;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class HeroDto {
    private Long id;
    private String name;

    public static HeroDto fromDomain(Hero hero) {
        HeroDto heroDto = new HeroDto();
        BeanUtils.copyProperties(hero, heroDto);

        return heroDto;
    }
}
