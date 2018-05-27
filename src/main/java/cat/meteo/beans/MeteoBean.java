package cat.meteo.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cat.meteo.service.MeteoService;
import cat.meteo.domain.DadesMeteo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "meteoBean")
@Component
@Scope("session")
public class MeteoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dateFrom;
	private Date dateTo;
	private String location;
	private String mesura;
	private LineChartModel dateModel;

	@Autowired
	MeteoService meteoService;

	private DadesMeteo dadesMeteo;
	private float temperaturaMaxima;
	private float temperaturaMinima;

	@PostConstruct
	public void init() {

		Calendar d1 = Calendar.getInstance();
		Calendar d2 = Calendar.getInstance();
		d2.add(Calendar.DAY_OF_MONTH, -1);

		dateFrom = d2.getTime();
		dateTo = d1.getTime();
		location = "S1";

		dateModel = new LineChartModel();
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		List<DadesMeteo> dades = meteoService.cerca(dateFrom, dateTo, location);
		for (DadesMeteo registre : dades) {

			series1.set(registre.getData().toString(),
					registre.getTemperatura());
		}
		dateModel.addSeries(series1);
		dateModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%H:%#M:%S");
		dateModel.getAxes().put(AxisType.X, axis);

	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}

	public void setDateModel(LineChartModel dateModel) {
		this.dateModel = dateModel;
	}

	public String getMesura() {
		return mesura;
	}

	public void setMesura(String mesura) {
		this.mesura = mesura;
	}

	public void actualitzaGrafica() {

		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel(location);
		List<DadesMeteo> dades = meteoService.cerca(dateFrom, dateTo, location);

		for (DadesMeteo registre : dades) {

			if (mesura.equals("0"))
				series1.set(registre.getData().toString(),
						registre.getTemperatura());
			else
				series1.set(registre.getData().toString(),
						registre.getPressio());
		}

		if (dades.size() == 0)
			series1.set("0", 0);

		dateModel.addSeries(series1);
		dateModel.getAxis(AxisType.Y).setLabel("Values");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%H:%#M:%S");
		dateModel.getAxes().put(AxisType.X, axis);
		
		temperaturaMaxima = meteoService.cercaMaximaPerRang(dateFrom, dateTo);
		temperaturaMinima = meteoService.cercaMinimaPerRang(dateFrom, dateTo);

	}

	public void actualitzaDades() {
		dadesMeteo = meteoService.getDadesSensor();
		actualitzaGrafica();
	}

	public DadesMeteo getDadesMeteo() {
		return dadesMeteo;
	}

	public void setDadesMeteo(DadesMeteo dadesMeteo) {
		this.dadesMeteo = dadesMeteo;
	}

	public float getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(float temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public float getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(float temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

}
