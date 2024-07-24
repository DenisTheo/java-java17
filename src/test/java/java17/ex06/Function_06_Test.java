package java17.ex06;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 6 - java.util.function.Function and java.util.function.Supplier
 */
public class Function_06_Test
{
    // tag::functions[]
    // TODO Compléter la fonction
    // TODO retourner un nom complet basé sur le prénom et le nom de famille
    Function<Person, String> getFullName = p -> p.getFirstname() + " " + p.getLastname();

    // TODO Compléter la fonction
    // TODO créer un nouvel objet Person avec des valeurs par défaut
    Supplier<Person> createDefaultPerson = () -> new Person("John", "Doe", 30, null);
    // end::functions[]

    @Test
    public void test_functions() throws Exception
    {
        List<Person> personList = Data.buildPersonList();

        // TODO utiliser getFullName pour obtenir le nom complet d'une personne
        Person person = personList.get(0);
        String fullName = getFullName.apply(person);

        assert fullName.equals(person.getFirstname() + " " + person.getLastname());

        // TODO utiliser createDefaultPerson pour créer une nouvelle personne avec des valeurs par défaut
        Person defaultPerson = createDefaultPerson.get();

        assert defaultPerson.getFirstname().equals("John");
        assert defaultPerson.getLastname().equals("Doe");
        assert defaultPerson.getAge() == 30;
        assert defaultPerson.getPassword() == null;
    }
}
