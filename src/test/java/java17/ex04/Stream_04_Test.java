package java17.ex04;

import static org.hamcrest.Matchers.arrayContaining;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

/**
 * Exercice 04 - Stream
 */
public class Stream_04_Test
{
    @Test
    public void test_of() throws Exception
    {
        Stream<String> result = Stream.of("Alexandra", "Cyril", "Johnny", "Marion", "Sophie");

        assertThat(result.toArray(), arrayContaining("Alexandra", "Cyril", "Johnny", "Marion", "Sophie"));
    }

    @Test
    public void test_builder() throws Exception
    {
        Stream<String> result = Stream.<String>builder()
            .add("Alexandra")
            .add("Cyril")
            .add("Johnny")
            .add("Marion")
            .add("Sophie")
            .build();

        assertThat(result.toArray(), arrayContaining("Alexandra", "Cyril", "Johnny", "Marion", "Sophie"));
    }

    @Test
    public void test_concat() throws Exception
    {
        Stream<String> s1 = Stream.of("Alexandra", "Cyril");
        Stream<String> s2 = Stream.of("Johnny", "Marion", "Sophie");

        Stream<String> result = Stream.concat(s1, s2);

        assertThat(result.toArray(), arrayContaining("Alexandra", "Cyril", "Johnny", "Marion", "Sophie"));
    }

    @Test
    public void test_iterate() throws Exception
    {
        Stream<Integer> result1 = Stream.iterate(1, n -> 1).limit(5);
        Stream<Integer> result2 = Stream.iterate(1, n -> n + 1).limit(5);

        assertThat(result1.toArray(), arrayContaining(1, 1, 1, 1, 1));
        assertThat(result2.toArray(), arrayContaining(1, 2, 3, 4, 5));
    }
}
