package datamodel;

public class OrderItem {
  private String description;
  private final Article article;
  private int unitsOrdered;
  

  protected OrderItem(String description, Article article, int units ){
	  setDescription(description);
	  this.article = article;
	  setUnitsOrdered(units);
	   
	  
	  
  }
  
  public String getDescription() {
	  
	  return this.description;
	  
  }
  
  
public void setDescription(String descr) {
	if(descr == null) {
		this.description = "";
	} else {
	  this.description = descr;
	} 
  }
	 
	  
  
  
  
  public Article getArticle() {
	
	return this.article;
}
  
  public int getUnitsOrdered() {
	  
	  return this.unitsOrdered;
	  
  }

  
public void setUnitsOrdered(int number) {
	if(number < 0 ) {
		this.unitsOrdered = 0;
	} else {
	this.unitsOrdered = number;
	}
	  
  }


















}
