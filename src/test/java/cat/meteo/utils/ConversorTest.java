package cat.meteo.utils;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import cat.meteo.model.Temperatura;

public class ConversorTest {

	@Test
	public void testcelsiusToFahrenheit() {

		Temperatura temp = new Temperatura();
		float valueCelsius = (float) 21.5;
		float valueFahrenheit = (float) 70.7;

		temp.setUnitat(Temperatura.Unitat.CELSIUS);
		temp.setValor(new Float(valueCelsius));

		assertThat(Conversor.celsiusToFahrenheit(temp).getValor(),
				is(new Float(valueFahrenheit)));
	}

	@Test
	public void testFahrenheitToCelsius() {

		Temperatura temp = new Temperatura();
		float valueFahrenheit = (float) 158;
		float valueCelsius = (float) 70;

		temp.setUnitat(Temperatura.Unitat.FAHRENHEIT);
		temp.setValor(new Float(valueFahrenheit));

		assertThat(Conversor.fahrenheitToCelsius(temp).getValor(),
				is(new Float(valueCelsius)));
	}

}
