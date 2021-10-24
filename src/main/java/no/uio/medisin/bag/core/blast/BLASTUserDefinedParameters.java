package no.uio.medisin.bag.core.blast;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BLASTUserDefinedParameters {
  static Logger                       logger                      = LogManager.getLogger();

  public final static     String BLAST_PROGRAM               			= "blastProgram";
  public final static     String BLAST_TASK                    	 	= "task";
  public final static     String BLAST_QUERY                    	= "query";
  public final static     String BLAST_DB                         = "db";
  public final static     String BLAST_EVALUE            					= "evalue";
  public final static     String BLAST_GAP_OPEN             			= "gapopen";
  public final static     String BLAST_GAP_EXTEND             		= "gapextend";
  public final static     String BLAST_PENALTY            				= "penalty";
  public final static     String BLAST_REWARD             				= "reward";
  public final static     String BLAST_PERCENT_IDENTITY           = "perc_identity";
  public final static     String BLAST_PERCENT_COVERAGE           = "qcov_hsp_perc";
  public final static     String BLAST_MAXNUM_HSPS             		= "max_hsps";
  public final static     String BLAST_NUM_THREADS             		= "num_threads";
  
  
  private                 String queryFile         = "";
  private                 String blastRefDatabase  = "";

  private                 String blastPath;
  private                 String blastSoftwareFolder;
  private                 String blastProgram;
  private                 String blastQuery;
  private                 String blastDB;
  private                 String blastTask;
  private                 String blastEvalue;
  private                 String blastGapOpen;
  private                 String blastGapExtend;
  private                 String blastPenalty;
  private                 String blastReward;
  private                 String blastPercentIdentity;
  private                 String blastPercentCoverage;
  private                 String blastNumThreads;
  private									String blastMaxNumHSPS;

  
  public BLASTUserDefinedParameters(){
  
  }
  
     
  
  public BLASTUserDefinedParameters(HashMap<String, Object> options) {
      
  	//if(options.get(BLASTUserDefinedParameters.BLAST_SOFTWARE_FOLDER)!=null) {
  	//	setBlastSoftwareFolder((String)options.get(BLASTUserDefinedParameters.BLAST_SOFTWARE_FOLDER));
  	//}else {
  	//	logger.error("missing [" + BLASTUserDefinedParameters.BLAST_SOFTWARE_FOLDER + "] definition in parameter set");
  	//}
  	if(options.get(BLASTUserDefinedParameters.BLAST_PROGRAM)!=null) {
  		setBlastProgram((String)options.get(BLASTUserDefinedParameters.BLAST_PROGRAM));
  	}else {
  		logger.error("missing [" + BLASTUserDefinedParameters.BLAST_PROGRAM + "] definition in parameter set");
  	}
  	if(options.get(BLASTUserDefinedParameters.BLAST_TASK)!=null)
  		setBlastTask((String)options.get(BLASTUserDefinedParameters.BLAST_TASK));

  	if(options.get(BLASTUserDefinedParameters.BLAST_EVALUE)!=null)
  		setBlastEvalue(Double.toString((Double)options.get(BLASTUserDefinedParameters.BLAST_EVALUE)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_GAP_OPEN)!=null)
  		setBlastGapOpen(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_GAP_OPEN)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_GAP_EXTEND)!=null)
  		setBlastGapExtend(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_GAP_EXTEND)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_PENALTY)!=null)
  		setBlastPenalty(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_PENALTY)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_REWARD)!=null)
  		setBlastReward(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_REWARD)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_PERCENT_IDENTITY)!=null)
  		setBlastPercentIdentity(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_PERCENT_IDENTITY)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_PERCENT_COVERAGE)!=null)
  		setBlastPercentCoverage(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_PERCENT_COVERAGE)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_MAXNUM_HSPS)!=null)
  		setBlastMaxNumHSPS(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_MAXNUM_HSPS)));
  	if(options.get(BLASTUserDefinedParameters.BLAST_NUM_THREADS)!=null)
  		setBlastNumThreads(Integer.toString((Integer)options.get(BLASTUserDefinedParameters.BLAST_NUM_THREADS)));
  }
  
  
  /**
   * in this method we are simply checking that the configuration file 
   * has the core entries we need to run a BLAST query. 
   * 
   * @param configData
   * @throws Exception 
   */
  
  /**
   * 
   * @param paramString 
   */
  public void parseBLASTCmdString(String paramString) throws ParseException{
    /**
     * example string
     * blastn -db HCMV2 -query MIMAT0001574.fa -task "blastn-short"  -out chacha2.xml -outfmt 5
     * 
     * grab path to Blast executable first, then parse out all the parameters
     * 
     */     	
    blastPath = paramString.split("-")[0].trim();
    String blastParams = paramString.split("-")[1].trim();
    String params[] = blastParams.split("-");
    
    for (String param: params){
      String paramKey = param.split(" ")[0].trim();
      String paramVal = param.split(" ")[1].trim();
      
      if(paramKey.equals(BLAST_TASK)){
        this.blastTask = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_QUERY)){
        this.blastQuery = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_DB)){
        this.blastDB = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_EVALUE)){
        this.blastEvalue = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_GAP_OPEN)){
        this.blastGapOpen = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_GAP_EXTEND)){
        this.blastGapExtend = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_PENALTY)){
        this.blastPenalty = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_REWARD)){
        this.blastReward = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_PERCENT_IDENTITY)){
        this.blastPercentIdentity = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_PERCENT_COVERAGE)){
        this.blastPercentCoverage = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_MAXNUM_HSPS)){
        this.blastNumThreads = paramVal.trim();
        continue;
      }

      if(paramKey.equals(BLAST_NUM_THREADS)){
        this.blastMaxNumHSPS = paramVal.trim();
        continue;
      }

      // we don't know what param we have
      throw new ParseException("Unknown BLAST parameter <" + paramKey + "|" + paramVal + ">", 0);
    }
  
  }
  
  

  
  /** 
   * Write out values as a YAML string
   * @return 
   */
  public String toYAMLString(){
    
    String yamlString = "---\n"
            + "# Required Parameters:\n"
            + "blastSoftwareFolder: " + this.getBlastSoftwareFolder() + "\n"
            + "blastProgram: " + this.getBlastProgram() + "\n"
            + "blastTask: " + this.getBlastTask() + "\n"
            + "# Optional Parameters\n"
            + "blastGapOpen: " + this.getBlastGapOpen() + "\n"
            + "blastGapExtend: " + this.getBlastGapExtend() + "\n"
            + "blastPenalty: " + this.getBlastPenalty() + "\n"
            + "blastReward: " + this.getBlastReward() + "\n"
            + "blastEvalue: " + this.getBlastEvalue() + "\n"
            + "# Filtering Parameters\n"
            + "blastPercentIdentity: " + this.getBlastPercentIdentity() + "\n"
            + "blastPercentCoverage: " + this.getBlastPercentCoverage() + "\n"
            + "blastMaxNumHSPS: " + this.getBlastMaxNumHSPS() + "\n"
            + "# Other Parameters\n"
            + "blastNumberOfThreads: " + this.getBlastNumThreads() + "\n";
            

    return yamlString;
  }
  
  /**
   * 
   * @throws IOException 
   */
  public void verifyReferenceData() throws IOException{
      if(new File(getBlastPath()).exists()==false){
          throw new IOException("BLAST software path <" + getBlastPath() + "> not found");
      }
      
  }

	public String getBlastSoftwareFolder() {
		return blastSoftwareFolder;
	}

	public void setBlastSoftwareFolder(String blastSoftwareFolder) {
		this.blastSoftwareFolder = blastSoftwareFolder;
	}

	public String getBlastProgram() {
		return blastProgram;
	}

	public void setBlastProgram(String blastProgram) {
		this.blastProgram = blastProgram;
	}

	public String getBlastTask() {
		return blastTask;
	}

	public void setBlastTask(String blastTask) {
		this.blastTask = blastTask;
	}

	public String getBlastEvalue() {
		return blastEvalue;
	}

	public void setBlastEvalue(String blastEvalue) {
		this.blastEvalue = blastEvalue;
	}

	public String getBlastGapOpen() {
		return blastGapOpen;
	}

	public void setBlastGapOpen(String blastGapOpen) {
		this.blastGapOpen = blastGapOpen;
	}

	public String getBlastGapExtend() {
		return blastGapExtend;
	}

	public void setBlastGapExtend(String blastGapExtend) {
		this.blastGapExtend = blastGapExtend;
	}

	public String getBlastPenalty() {
		return blastPenalty;
	}

	public void setBlastPenalty(String blastPenalty) {
		this.blastPenalty = blastPenalty;
	}

	public String getBlastReward() {
		return blastReward;
	}

	public void setBlastReward(String blastReward) {
		this.blastReward = blastReward;
	}

	public String getBlastPercentIdentity() {
		return blastPercentIdentity;
	}

	public void setBlastPercentIdentity(String blastPercentIdentity) {
		this.blastPercentIdentity = blastPercentIdentity;
	}

	public String getBlastPercentCoverage() {
		return blastPercentCoverage;
	}

	public void setBlastPercentCoverage(String blastPercentCoverage) {
		this.blastPercentCoverage = blastPercentCoverage;
	}

	public String getBlastNumThreads() {
		return blastNumThreads;
	}

	public void setBlastNumThreads(String blastNumThreads) {
		this.blastNumThreads = blastNumThreads;
	}

	public String getBlastMaxNumHSPS() {
		return blastMaxNumHSPS;
	}

	public void setBlastMaxNumHSPS(String blastMaxNumHSPS) {
		this.blastMaxNumHSPS = blastMaxNumHSPS;
	}

  /**
   * @return the blastPath
   */
  public String getBlastPath() {
    return blastPath;
  }

  /**
   * @param blastPath the blastPath to set
   */
  public void setBlastPath(String blastPath) {
    this.blastPath = blastPath;
  }

  /**
   * @return the blastQuery
   */
  public String getBlastQuery() {
    return blastQuery;
  }

  /**
   * @param blastQuery the blastQuery to set
   */
  public void setBlastQuery(String blastQuery) {
    this.blastQuery = blastQuery;
  }

  /**
   * @return the blastDB
   */
  public String getBlastDB() {
    return blastDB;
  }

  /**
   * @param blastDB the blastDB to set
   */
  public void setBlastDB(String blastDB) {
    this.blastDB = blastDB;
  }
  
}


