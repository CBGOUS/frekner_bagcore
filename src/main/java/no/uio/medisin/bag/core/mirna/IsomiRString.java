/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.uio.medisin.bag.core.mirna;

/**
 *
 * @author simonray
 */
public class IsomiRString {
    private static final int NAMEPOS = 0;
    private static final int STARTPOS = 1;
    private static final int CIGARPOS = 2;
    private static final int MDPOS = 3;
    private static final int SEQPOS = 4;
    private static final int COUNTPOS = 5;
    private static final int ATTRIBSPOS = 6;
    
  
    String isomiRString = "";
    
    public IsomiRString(String name, int start, String cigar, String md, String seq, int count){
       isomiRString = name + "|" +  seq + ";" + start + ";" + cigar + ";" + md + ";" + seq + ";" + Integer.toString(count);
    }
    
    private String buildIsomiRString(String name, int start, String cigar, String md, String seq, int count)  {
      isomiRString = name + ";" + start + ";" + cigar + ";" + md + ";" + seq + ";" + Integer.toString(count);
      return isomiRString;
    }  
    
    
    private String addToIsomiRString(String name, int start, String cigar, String md, String seq, int count)  {
      isomiRString = isomiRString.concat(name + ";" + start + ";" + cigar + ";" + md + ";" + Integer.toString(count));
      return isomiRString;
    }  
    
    /**
     * 
     * @return 
     */
    public String mergeIsomiRString(IsomiRString qIsoString){
      if(!qIsoString.getName().equals(this.getName())){
        
      }
      return this.isomiRString;
    }
    
    
    /**
     * increment the readCount for this isomiR
     * @param dCount counts to add
     * @return the updated isomiR string
     */
    public String incCount( int dCount)  {
      int thisCount = this.getCount();
      isomiRString = isomiRString.concat(this.getName() + ";" + Integer.toString(this.getStart()) + ";" 
              + this.getCigarString() + ";" + this.getMDString() + ";" + Integer.toString(thisCount + dCount))
              + ";" + this.getAttribs();
      return isomiRString;
    }  
  
    
    
    
    /**
     * get the isomiR name from the isomiR string
     * @return 
     */
    public String getName(){
      return isomiRString.split(";")[NAMEPOS].trim();
    }
    
    /**
     * get the isomiR name from the isomiR string
     * @return 
     */
    public String getCigarString(){
     if((isomiRString.split(";")).length-1 >= CIGARPOS){
      return isomiRString.split(";")[CIGARPOS].trim();
     }
     return "";
    }
    
    /**
     * get the isomiR name from the isomiR string
     * @return 
     */
    public String getMDString(){
     if((isomiRString.split(";")).length-1 >= MDPOS){
      return isomiRString.split(";")[MDPOS].trim();
     }
     return "";
    }
    
    /**
     * get the isomiR sequence from the isomiR string
     * @return sequence String
     */
    public String getSeq(){
      return isomiRString.split(";")[SEQPOS].trim();
    }
    
    /**
     * get the isomiR attributes from the isomiR string
     * @return Attrib string
     */
    public String getAttribs(){
      if((isomiRString.split(";")).length-1 >= ATTRIBSPOS){
        return isomiRString.split(";")[ATTRIBSPOS].trim();
      }
      return "";
    }
    
    /**
     * get the isomiR read counts from the isomiR string
     * @return readCounts as int
     */
    public int getCount(){
      return Integer.valueOf(isomiRString.split(";")[COUNTPOS].trim());
    }
    
    /**
     * get the isomiR start position from the isomiR string
     * @return start position as int
     */
    public int getStart(){
      return Integer.valueOf(isomiRString.split(";")[STARTPOS].trim());
    }
    
    public String toString(){
      return this.getName() + "\n"
              + this.getSeq() + "\n" 
              + this.getStart() + "-->"
              + (this.getStart() + this.getSeq().length() - 1)
              + "[" + this.getCount() + "] reads \n"
              + this.getMDString() + "|" 
              + this.getCigarString() + "\n"
              + this.getAttribs() + "\n";
    }
    
    public String toTSV(){
      String isomiRStr = "";
      return isomiRStr.concat("name: " + this.getName() + "\t"
              + "start: " + this.getStart() + "\t"
              + "cigar: " + this.getCigarString() + "\t"
              + "MD: " + this.getMDString() + "\t" 
              + "SQ: " + this.getSeq() + "\n");

    }
    
}
