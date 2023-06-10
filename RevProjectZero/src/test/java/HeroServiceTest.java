import com.revature.daos.HeroDAO;
import com.revature.models.Hero;
import com.revature.service.HeroService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HeroServiceTest {

    HeroDAO mockHeroDAO = mock(HeroDAO.class);
    HeroService heroService = new HeroService(mockHeroDAO);

    @Test
    public void getHeroByIdZero(){
        // What should we get when we pass zero to this method?
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertNull(heroService.getHeroById(0));
    }

    @Test
    public void getHeroByIdNegativeInt(){
        // What should we get when we pass zero to this method?
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertNull(heroService.getHeroById(-3));
    }

    // We need fake a call to the roleDAO and essentially return it with a return value

    @Test
    public void getHeroByIdPositiveInt(){
        // Let's create a fake Role object we want to return from the db
        Hero hero = new Hero(4, "He-man","Sword of Grayskull");

        // Now we need to make sure our dao returns this when called
        when(mockHeroDAO.getHeroById(4)).thenReturn(hero);

        assertEquals(hero, heroService.getHeroById(4));
    }
}
