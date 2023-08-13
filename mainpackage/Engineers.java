package mainpackage;

public class Engineers {
	
	private ComputerEngineer ce1;
	private ComputerEngineer ce2;
	private ComputerEngineer ce3;
	private ComputerEngineer ce4;
	private ComputerEngineer ce5;
	private ComputerEngineer ce6;
	private ComputerEngineer ce7;
	private Engineer e1;
	private Engineer e2;
	private Engineer e3;
	private Engineer e4;
	
	public ComputerEngineer getCe1() {
		return ce1;
	}

	public void setCe1(ComputerEngineer ce1) {
		this.ce1 = ce1;
	}

	public ComputerEngineer getCe2() {
		return ce2;
	}

	public void setCe2(ComputerEngineer ce2) {
		this.ce2 = ce2;
	}

	public ComputerEngineer getCe3() {
		return ce3;
	}

	public void setCe3(ComputerEngineer ce3) {
		this.ce3 = ce3;
	}

	public ComputerEngineer getCe4() {
		return ce4;
	}

	public void setCe4(ComputerEngineer ce4) {
		this.ce4 = ce4;
	}

	public ComputerEngineer getCe5() {
		return ce5;
	}

	public void setCe5(ComputerEngineer ce5) {
		this.ce5 = ce5;
	}

	public ComputerEngineer getCe6() {
		return ce6;
	}

	public void setCe6(ComputerEngineer ce6) {
		this.ce6 = ce6;
	}

	public ComputerEngineer getCe7() {
		return ce7;
	}

	public void setCe7(ComputerEngineer ce7) {
		this.ce7 = ce7;
	}

	public Engineer getE1() {
		return e1;
	}

	public void setE1(Engineer e1) {
		this.e1 = e1;
	}

	public Engineer getE2() {
		return e2;
	}

	public void setE2(Engineer e2) {
		this.e2 = e2;
	}

	public Engineer getE3() {
		return e3;
	}

	public void setE3(Engineer e3) {
		this.e3 = e3;
	}

	public Engineer getE4() {
		return e4;
	}

	public void setE4(Engineer e4) {
		this.e4 = e4;
	}

	public boolean login(String inputname, String inputpw) {
		boolean ifNotAnyE = IfNotE(inputname, inputpw, e1) && IfNotE(inputname, inputpw, e2) && IfNotE(inputname, inputpw, e3) && IfNotE(inputname, inputpw, e4);
		boolean ifNotAnyCE = IfNotCE(inputname, inputpw, ce1) && IfNotCE(inputname, inputpw, ce2) && IfNotCE(inputname, inputpw, ce3)
		&& IfNotCE(inputname, inputpw, ce4) && IfNotCE(inputname, inputpw, ce5) && IfNotCE(inputname, inputpw, ce6) && IfNotCE(inputname, inputpw, ce7);
		return ifNotAnyE && ifNotAnyCE;
	}
	
	public boolean IfNotCE(String inputname, String inputpw, ComputerEngineer computerEngineer) {
		if (inputname.equals(computerEngineer.getName())) {
			if (inputpw.equals(computerEngineer.getPw())) {
				return false;
			}
			return true;
		}
		return true;
	}
	
	public boolean IfNotE(String inputname, String inputpw, Engineer engineer) {
		if (inputname.equals(engineer.getName())) {
			if (inputpw.equals(engineer.getPw())) {
				return false;
			}
			return true;
		}
		return true;
	}
}
