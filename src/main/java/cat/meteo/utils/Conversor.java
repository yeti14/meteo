package cat.meteo.utils;

import cat.meteo.model.Temperatura;

public class Conversor {

	public static Temperatura celsiusToFahrenheit(Temperatura temp) {

		if (temp.getUnitat().equals(Temperatura.Unitat.CELSIUS)) {

			temp.setUnitat(Temperatura.Unitat.FAHRENHEIT);
			float result = (temp.getValor().floatValue() * 9) / 5 + 32;
			
			temp.setValor(Float.valueOf(result));

		}

		return temp;
	}

	public static Temperatura fahrenheitToCelsius(Temperatura temp) {

		if (temp.getUnitat().equals(Temperatura.Unitat.FAHRENHEIT)) {

			temp.setUnitat(Temperatura.Unitat.CELSIUS);
			float result = ((temp.getValor().floatValue() - 32) * 5) / 9;
			temp.setValor(Float.valueOf(result));
		}

		return temp;
	}

}
