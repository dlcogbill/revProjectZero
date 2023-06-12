import com.revature.daos.VillainDAO;
import com.revature.models.Villain;
import com.revature.service.VillainService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VillainServiceTest {

    VillainDAO mockVillainDAO = mock(VillainDAO.class);
    VillainService villainService = new VillainService(mockVillainDAO);

    @Test
    // What should we get when we pass zero to this method?
    public void getVillainByIdZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertNull(villainService.getVillainById(0));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void removeVillainByIdZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null

        assertFalse(villainService.removeVillain(0));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateVillainZero(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Villain villain = new Villain(0, "He-man","Sword of Grayskull");
        when(mockVillainDAO.updateVillain(villain)).thenReturn(villain);

        assertNull(villainService.updateVillain(villain));
    }


    @Test
    // What should we get when we pass a negative integer to the get by id method?
    public void getVillainByIdNegativeInt(){

        // We need fake a call to the roleDAO and essentially return it with a return value
        assertNull(villainService.getVillainById(-3));
    }

    @Test
    // What should we get when we pass a negative integer to the remove method?
    public void removeVillainByIdNegativeInt(){

        // We need fake a call to the roleDAO and essentially return it with a return value
        assertFalse(villainService.removeVillain(-3));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateVillainByNegativeInt(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Villain villain = new Villain(-3, "He-man","Sword of Grayskull");
        when(mockVillainDAO.updateVillain(villain)).thenReturn(villain);

        assertNull(villainService.updateVillain(villain));
    }



    @Test
    public void getVillainByIdPositiveInt(){

        // Let's create a fake Villain object we want to return from the db
        Villain villain = new Villain(4, "He-man","Sword of Grayskull");

        // Now we need to make sure our dao returns this when called
        when(mockVillainDAO.getVillainById(4)).thenReturn(villain);

        assertEquals(villain, villainService.getVillainById(4));
    }

    @Test
    public void removeVillainByIdPositiveInt(){

        // Let's create a fake Villain object we want to return from the db
        Villain villain = new Villain(4, "He-man","Sword of Grayskull");

        // Now we need to make sure our dao returns this when called
        when(mockVillainDAO.removeVillain(villain.getVillain_id())).thenReturn(true);

        assertTrue(villainService.removeVillain(4));
    }

    @Test
    // What should we get when we pass zero to the remove method?
    public void updateVillainByPositiveInt(){
        // Zero shouldn't pass the if condition, so we should be able to just verify null
        Villain villain = new Villain(4, "He-man","Sword of Grayskull");
        when(mockVillainDAO.updateVillain(villain)).thenReturn(villain);

        assertEquals(villain,villainService.updateVillain(villain));
    }

    @Test
    // Let's create a fake null Villain object we want to return from the db
    public void insertNullVillain(){

        Villain villain = new Villain( null,null);
        assertNull(villainService.insertVillain(villain));
    }

    @Test
    // Let's create a fake null Villain object we want to return from the db
    public void updateNullVillain(){

        Villain villain = new Villain(4, null,null);
        assertNull(villainService.updateVillain(villain));
    }

    @Test
    // Let's create a fake null Villain object we want to return from the db
    public void insertEmptyVillain(){

        Villain villain = new Villain( "","");
        assertNull(villainService.insertVillain(villain));
    }

    @Test
    // Let's create a fake null Villain object we want to return from the db
    public void updateEmptyVillain(){

        Villain villain = new Villain(4, "","");
        assertNull(villainService.updateVillain(villain));
    }
}
