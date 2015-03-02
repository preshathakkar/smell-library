package smell;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Presha Thakkar
 */
public class SmellAccessor {
    
    public boolean saveSmell(String smcId,String smcName,String smcDesc,String smcChemFormula,String smcPin){
    
        try{
        SmellBean sb = new SmellBean(smcId,smcName,smcDesc, smcChemFormula, smcPin);
        Smell sm = new Smell();
        sm.saveToXML(sb);
        return true;
        }
        
        catch(Exception e){e.printStackTrace();}
    return false;
    
    }
    
    public ArrayList<SmellBean> readAllSmell(){
        Smell sm = new Smell();
        return sm.readXML();
    }
    
    public ArrayList<String> readAllSmellName(){
        ArrayList<SmellBean> smells = readAllSmell();
        ArrayList<String> names = new ArrayList<String>();
        SmellBean sb;
        for(int i=0;i<smells.size();i++){
            sb = smells.get(i);
            names.add(sb.getSmcName());
        }
    
    return names;
    }
    
    public SmellBean getAllSmellInfoById(String id){
        ArrayList<SmellBean> smells = readAllSmell();
        SmellBean sb = null;
        int flag = 0;
        for(int i=0;i<smells.size();i++){
            
            sb = smells.get(i);
            if(sb.getSmcId().equals(id)) {
                flag = 1;
                break;
            }              
        }
    if(flag==1) return sb;
    else return null;
    }
    
    public String getNameById(String id){
        SmellBean sb = getAllSmellInfoById(id);
        String name = sb.getSmcName();
    
        return name;
    }
    
    public SmellBean getAllSmellInfoByName(String name){
        ArrayList<SmellBean> smells = readAllSmell();
        SmellBean sb = null;
        int flag = 0;
        for(int i=0;i<smells.size();i++){
            
            sb = smells.get(i);
            if(sb.getSmcName().equals(name)) {
                flag = 1;
                break;
            }              
        }
    if(flag==1) return sb;
    else return null;
    }
    
    public int getPinBySmellName(String name){
        SmellBean sb = getAllSmellInfoByName(name);
        
        return Integer.parseInt(sb.getSmcPin());
        
    }

    public int getPinBySmellId(String SmcId){
        SmellBean sb = getAllSmellInfoById(SmcId);
        
        return Integer.parseInt(sb.getSmcPin());
        
    }

    
    public String getIdBySmellName(String name){
        SmellBean sb = getAllSmellInfoByName(name);
        
        return sb.getSmcId();
        
    }
    
    public void removeSmellByName(String name){
        Smell sm = new Smell();
        sm.removeFromXMLByName(name);
    }
    
    public void removeSmellById(String Id){
        Smell sm = new Smell();
        sm.removeFromXMLById(Id);
    }
    
    public boolean updateSmellById(String smcId,String smcName,String smcDesc,String smcChemFormula,String smcPin){
        Smell sm = new Smell();
        boolean b;
        sm.removeFromXMLById(smcId);
        b = saveSmell(smcId,smcName,smcDesc,smcChemFormula,smcPin);
        return b;
    }
    
    public boolean checkSmellId(String smcId){
        ArrayList<SmellBean> smells = readAllSmell();
        SmellBean sb;
        int flag = 0;
        for(int i=0;i<smells.size();i++){
            sb = smells.get(i);
            if(sb.getSmcId().equals(smcId)){
                flag = 1;
                break;
            }
        }
        
        if(flag == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean checkSmellPin(String smcPin){
        ArrayList<SmellBean> smells = readAllSmell();
        SmellBean sb;
        int flag = 0;
        for(int i=0;i<smells.size();i++){
            sb = smells.get(i);
            if(sb.getSmcPin().equals(smcPin)){
                flag = 1;
                break;
            }
        }
        
        if(flag == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
}
