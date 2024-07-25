package java17.ex01;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import java17.data.Data;
import java17.data.domain.Order;
import java17.data.domain.Pizza;

/**
 * Exercice 01 - Recherche
 */
public class Stream_01_Test
{
	@Test
	public void test_stream_filter() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		List<Pizza> result = pizzas.stream()
			.filter(p -> p.getPrice() >= 1300)
			.collect(Collectors.toList());

		assertThat(result, hasSize(3));
		assertThat(result, everyItem(hasProperty("price", anyOf(equalTo(1300), greaterThan(1300)))));
	}

	@Test
	public void test_stream_filter_count() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		long result = pizzas.stream()
			.filter(p -> p.getPrice() >= 1300)
			.count();

		assertThat(result, is(3L));
	}

	@Test
	public void test_stream_anyMatch() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		Boolean result1 = pizzas.stream()
			.anyMatch(p -> p.getPrice() >= 1300);

		Boolean result2 = pizzas.stream()
			.anyMatch(p -> p.getPrice() >= 2000);

		assertThat(result1, is(true));
		assertThat(result2, is(false));
	}

	@Test
	public void test_stream_allMatch() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		Boolean result1 = pizzas.stream()
			.allMatch(p -> p.getPrice() >= 1300);

		Boolean result2 = pizzas.stream()
			.allMatch(p -> p.getPrice() >= 900);

		assertThat(result1, is(false));
		assertThat(result2, is(true));
	}

	@Test
	public void test_stream_noneMatch() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		Boolean result1 = pizzas.stream()
			.noneMatch(p -> p.getPrice() >= 2000);

		assertThat(result1, is(true));
	}

	@Test
	public void test_stream_findFirst() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		Optional<Order> result = orders.stream()
			.filter(o -> o.getCustomer().getFirstname().equals("Sophie"))
			.findFirst();

		assertThat(result.isPresent(), is(false));
	}
}
