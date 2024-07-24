package java17.ex02;

import java.util.List;

import org.junit.Test;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test
{

    // tag::IDao[]
    interface IDao
{
        List<Person> findAll();

        // TODO créer une méthode String format()
        // TODO la méthode retourne une chaîne de la forme [<nb_personnes> persons]
        // TODO exemple de résultat : "[14 persons]", "[30 persons]"
        
        public default String format()
        {
        	return findAll().size() + " persons";
        }
    }
    // end::IDao[]

    // tag::DaoA[]
    class DaoA implements IDao
    {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll()
        {
            return people;
        }

        @Override
        public String format()
        {
        	return "DaoA[" + IDao.super.format() + "]";
        }
    }
    // end::DaoA[]

    @Test
    public void test_daoA_format() throws Exception
    {

        DaoA daoA = new DaoA();

        String result = daoA.format();

        assert "DaoA[20 persons]".equals(result);
    }
}
