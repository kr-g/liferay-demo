package asdb.demo.liferay.primefaces;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.chart.MeterGaugeChartModel;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@ManagedBean
public class UserBackingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Log log = LogFactoryUtil.getLog(UserBackingBean.class);

	private Date date;
	private int spinnerValue;
	private int percent = 35;
	private MeterGaugeChartModel meterGauge;

	@PostConstruct
	private void init() {
		createModel();
	}

	public MeterGaugeChartModel createModel() {
		List<Number> intervals = new ArrayList<Number>() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			{
				add(20);
				add(50);
				add(80);
				add(100);
			}
		};

		return this.meterGauge = new MeterGaugeChartModel(percent, intervals);
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void onSlideEnd(SlideEndEvent event) {

		percent = event.getValue();

		FacesMessage message = new FacesMessage("Slide Ended", "Value: " + percent);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public int getSpinnerValue() {
		return spinnerValue;
	}

	public void setSpinnerValue(int spinnerValue) {
		log.info("setSpinnerValue");
		this.spinnerValue = spinnerValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPercent() {
		log.info("getPercent percent=" + percent);
		return percent;
	}

	public void setPercent(int percent) {
		log.info("setPercent percent=" + percent);
		this.percent = percent;
	}

	public MeterGaugeChartModel getMeterGauge() {
		log.info("getMeterGauge percent=" + percent);
		return createModel();
	}

	public void setMeterGauge(MeterGaugeChartModel meterGauge) {
		this.meterGauge = meterGauge;
	}

}
