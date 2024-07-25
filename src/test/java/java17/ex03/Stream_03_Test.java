package java17.ex03;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import java17.data.Data;
import java17.data.domain.Customer;
import java17.data.domain.Order;
import java17.data.domain.Pizza;

/**
 * Exercice 03 - Collectors
 */
public class Stream_03_Test
{
	@Test
	public void test_max() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		Optional<Order> result = orders.stream()
			.max((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

		assertThat(result.isPresent(), is(true));
		assertThat(result.get().getPrice(), is(2200.0));
	}

	@Test
	public void test_min() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		Optional<Order> result = orders.stream()
			.min((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

		assertThat(result.isPresent(), is(true));
		assertThat(result.get().getPrice(), is(1000.0));
	}

	@Test
	public void test_map_collect_joining() throws Exception
	{
		List<Customer> customers = new Data().getCustomers();

		String result = customers.stream()
			.map(Customer::getFirstname)
			.sorted()
			.collect(Collectors.joining("|"));

		assertThat(result, is("Alexandra|Cyril|Johnny|Marion|Sophie"));
	}

	@Test
	public void test_flatMap() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		List<Pizza> result = orders.stream()
			.flatMap(order -> order.getPizzas().stream())
			.collect(Collectors.toList());

		assertThat(result.size(), is(9));
	}

	@Test
	public void test_flatMap_distinct() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		List<Pizza> result = orders.stream()
			.flatMap(order -> order.getPizzas().stream())
			.distinct()
			.collect(Collectors.toList());

		assertThat(result.size(), is(4));
	}

	@Test
	public void test_grouping() throws Exception
	{
		List<Order> orders = new Data().getOrders();

		Map<Customer, List<Order>> result = orders.stream()
			.collect(Collectors.groupingBy(Order::getCustomer));

		assertThat(result.size(), is(2));
		assertThat(result.get(new Customer(1)), hasSize(4));
		assertThat(result.get(new Customer(2)), hasSize(4));
	}

	@Test
	public void test_partitionning() throws Exception
	{
		List<Pizza> pizzas = new Data().getPizzas();

		Map<Boolean, List<Pizza>> result = pizzas.stream()
			.collect(Collectors.partitioningBy(pizza -> pizza.getName().startsWith("L")));

		assertThat(result.get(true), hasSize(6));
		assertThat(result.get(false), hasSize(2));
	}
}
