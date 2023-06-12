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
    // What should we get when we pass zero to this method?
    public void getHeroByIdZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertNull(heroService.getHeroById(0));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void removeHeroByIdZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertFalse(heroService.removeHero(0));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateHeroZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Hero hero = new Hero(0, "He-man","Sword of Grayskull");
        when(mockHeroDAO.updateHero(hero)).thenReturn(hero);

        assertNull(heroService.updateHero(hero));
    }


    @Test
    // What should we get when we pass a negative integer to the get by id method?
    public void getHeroByIdNegativeInt(){

        // We need fake a call to the roleDAO and essentially return it with a return value
        assertNull(heroService.getHeroById(-3));
    }

    @Test
    // What should we get when we pass a negative integer to the remove method?
    public void removeHeroByIdNegativeInt(){

        // We need fake a call to the roleDAO and essentially return it with a return value
        assertFalse(heroService.removeHero(-3));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateHeroByNegativeInt(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Hero hero = new Hero(-3, "He-man","Sword of Grayskull");
        when(mockHeroDAO.updateHero(hero)).thenReturn(hero);

        assertNull(heroService.updateHero(hero));
    }



    @Test
    public void getHeroByIdPositiveInt(){

        // Let's create a fake Hero object we want to return from the db
        Hero hero = new Hero(4, "He-man","Sword of Grayskull");

        // Now we need to make sure our dao returns this when called
        when(mockHeroDAO.getHeroById(4)).thenReturn(hero);

        assertEquals(hero, heroService.getHeroById(4));
    }

    @Test
    public void removeHeroByIdPositiveInt(){

        // Let's create a fake Hero object we want to return from the db
        Hero hero = new Hero(4, "He-man","Sword of Grayskull");

        // Now we need to make sure our dao returns this when called
        when(mockHeroDAO.removeHero(hero.getHero_id())).thenReturn(true);

        assertTrue(heroService.removeHero(4));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateHeroByPositiveInt(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Hero hero = new Hero(4, "He-man","Sword of Grayskull");
        when(mockHeroDAO.updateHero(hero)).thenReturn(hero);

        assertEquals(hero,heroService.updateHero(hero));
    }

    @Test
    // Let's create a fake null Hero object we want to return from the db
    public void insertNullHero(){

        Hero hero = new Hero( null,null);
        assertNull(heroService.insertHero(hero));
    }

    @Test
    // Let's create a fake null Hero object we want to return from the db
    public void updateNullHero(){

        Hero hero = new Hero(4, null,null);
        assertNull(heroService.updateHero(hero));
    }

    @Test
    // Let's create a fake null Hero object we want to return from the db
    public void insertEmptyHero(){

        Hero hero = new Hero( "","");
        assertNull(heroService.insertHero(hero));
    }

    @Test
    // Let's create a fake null Hero object we want to return from the db
    public void updateEmptyHero(){

        Hero hero = new Hero(4, "","");
        assertNull(heroService.updateHero(hero));
    }
}