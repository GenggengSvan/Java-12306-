package pojo;

import java.util.Objects;

public class log {

	private String �û���;
	private String passId;
	private String ���;
	
	public log(String �û���, String passId, String ���) {
		super();
		this.�û��� = �û���;
		this.passId = passId;
		this.��� = ���;
	}
	

	public log() {
		super();
	}


	public String get�û���() {
		return �û���;
	}

	public void set�û���(String �û���) {
		this.�û��� = �û���;
	}

	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}

	public String get���() {
		return ���;
	}

	public void set���(String ���) {
		this.��� = ���;
	}

	@Override
	public int hashCode() {
		return Objects.hash(passId, �û���, ���);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		log other = (log) obj;
		return Objects.equals(passId, other.passId) && Objects.equals(�û���, other.�û���) && Objects.equals(���, other.���);
	}

	@Override
	public String toString() {
		return "log [�û���=" + �û��� + ", passId=" + passId + ", ���=" + ��� + "]";
	};
	

}
