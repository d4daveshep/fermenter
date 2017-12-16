package nz.net.daveshep.fermenter.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemperatureReading implements Serializable {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.S");

	private Date timestamp;
	private String brewId, action;
	private double targetTemp, minTemp, maxTemp, nowTemp, avgTemp;

	public TemperatureReading() {
		super();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public String getTimestampString() {
		if (timestamp == null) {
			return "";
		} else {
			return sdf.format(this.timestamp);
		}
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(String timestamp) throws ParseException {
		this.timestamp = sdf.parse(timestamp);
	}

	@JsonProperty("brewid")
	public String getBrewId() {
		return brewId;
	}

	@JsonProperty("brewid")
	public void setBrewId(String brewId) {
		this.brewId = brewId;
	}

	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}

	@JsonProperty("target")
	public double getTargetTemp() {
		return targetTemp;
	}

	@JsonProperty("target")
	public void setTargetTemp(double targetTemp) {
		this.targetTemp = targetTemp;
	}

	@JsonProperty("min")
	public double getMinTemp() {
		return minTemp;
	}

	@JsonProperty("min")
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	@JsonProperty("max")
	public double getMaxTemp() {
		return maxTemp;
	}

	@JsonProperty("max")
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	@JsonProperty("now")
	public double getNowTemp() {
		return nowTemp;
	}

	@JsonProperty("now")
	public void setNowTemp(double nowTemp) {
		this.nowTemp = nowTemp;
	}

	@JsonProperty("avg")
	public double getAvgTemp() {
		return avgTemp;
	}

	@JsonProperty("avg")
	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}

	@Override
	public boolean equals(Object obj) {

		TemperatureReading tr = (TemperatureReading) obj;

		if (!super.equals(obj)) {
			return false;
		}

		if (tr.getTargetTemp() != targetTemp) {
			return false;
		}
		if (tr.getMaxTemp() != maxTemp) {
			return false;
		}
		if (tr.getMinTemp() != minTemp) {
			return false;
		}
		if (tr.getNowTemp() != nowTemp) {
			return false;
		}
		if (tr.getAvgTemp() != avgTemp) {
			return false;
		}

		if (!tr.getBrewId().equalsIgnoreCase(brewId)) {
			return false;
		}

		if (!tr.getAction().equalsIgnoreCase(action)) {
			return false;
		}

		if (!tr.getTimestamp().equals(timestamp)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "TemperatureReading [timestamp=" + getTimestampString()
				+ ", brewId=" + brewId + ", targetTemp=" + targetTemp
				+ ", avgTemp=" + avgTemp + ", action=" + action + ", minTemp="
				+ minTemp + ", maxTemp=" + maxTemp + ", nowTemp=" + nowTemp
				+ "]";
	}

}
