package it.esdebitamiretake.retake_ir.scraping.model;

public class Content {

	private final String text;
	private final String resource;
	
	public Content(String text,String resource) {

		this.text=text;
		this.resource=resource;
	}

	public String getText() {
		
		return text;
	}
	
	public String getResource() {
		
		return resource;
	}

	@Override
	public boolean equals(Object o) {

		if (o != null && o instanceof Content)
			return this.toString().equalsIgnoreCase(((Content)o).toString());
		
		return false;
	}
	
	@Override
	public int hashCode() {

		return getText().hashCode();
	}

	
}