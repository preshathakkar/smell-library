package smell;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Presha Thakkar
 */
public class SmellBean {
     private String smcId = null;
    private String smcName = null;
    private String smcDesc = null;
    private String smcChemFormula = null;
    private String smcPin = null;
    
    public  SmellBean(String smcId,String smcName,String smcDesc,String smcChemFormula,String smcPin){
        this.smcId = smcId;
        this.smcName = smcName;
        this.smcDesc = smcDesc;
        this.smcChemFormula = smcChemFormula;
        this.smcPin = smcPin;
        
    }

    public SmellBean(){}
    
    public String getSmcChemFormula() {
        return smcChemFormula;
    }

    public void setSmcChemFormula(String smcChemFormula) {
        this.smcChemFormula = smcChemFormula;
    }

    public String getSmcDesc() {
        return smcDesc;
    }

    public void setSmcDesc(String smcDesc) {
        this.smcDesc = smcDesc;
    }

    public String getSmcId() {
        return smcId;
    }

    public void setSmcId(String smcId) {
        this.smcId = smcId;
    }

    public String getSmcName() {
        return smcName;
    }

    public void setSmcName(String smcName) {
        this.smcName = smcName;
    }

    public String getSmcPin() {
        return smcPin;
    }

    public void setSmcPin(String smcPin) {
        this.smcPin = smcPin;
    }
    
    @Override
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("ID : "+this.smcId+"\n");
        
        s.append("Name : "+this.smcName+"\n");
        s.append("Description : "+this.smcDesc+"\n");
        s.append("Chemical Formula : "+this.smcChemFormula+"\n");
        s.append("Pin : "+this.smcPin+"");
       
        return s.toString();
    }
    
}
