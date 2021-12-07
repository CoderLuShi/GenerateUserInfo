package domain;

public class AreaCode {
	private String province;
	private String city;
	private String country;
	private String town;
	private String village;
	private long countryCode;

	public AreaCode() {
	}
	public AreaCode(String province, String city, String country, String town, String village, long countryCode) {
		this.province = province;
		this.city = city;
		this.country = country;
		this.town = town;
		this.village = village;
		this.countryCode = (long) Math.floor(countryCode/1000000);
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(long countryCode) {
		this.countryCode = (long) Math.floor(countryCode/1000000);
	}

	@Override
	public String toString() {
		return "AreaCode{" +
				"province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", town='" + town + '\'' +
				", village='" + village + '\'' +
				", countryCode=" + countryCode +
				'}';
	}
}

