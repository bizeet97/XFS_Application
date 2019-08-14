package com.example.demo.xfs;

import com.example.demo.xfs.XfsRepository;
import com.example.demo.xfs.Xfs;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.client.RestTemplate;

//@Document (collection="XfsStatus")

public class Xfs {
  @Id
  public ObjectId _id;
  public String jobtitle;
  public String bNumber;
  public String bUrl;
  public String buildStatus;
  public String l_commit;
 
  
  // Constructors
  public Xfs() {}
  
  public Xfs(ObjectId id,String jobtitle, String bNumber ,String bUrl,String buildStatus,String l_commit ) 
  {
	  super();
    this._id = id;
    this.jobtitle= jobtitle;
    this.bNumber= bNumber;
    this.bUrl= bUrl;
    this.buildStatus=buildStatus;
    this.l_commit=l_commit;
  }

  public String getL_commit() {
	  String URL = "https://api.github.com/repos/bizeet97/XFS_Spring/commits";
      System.out.println(URL);
      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(URL, String.class);
      
      String arr[] = result.split(":");
      System.out.println(arr.length);

      String str=arr[5];
      String arr2[] = str.split(",");
		System.out.println(arr2[0]);
	
      return arr2[0].replaceAll("^\"|\"$","");
}

public void setL_commit(String l_commit) {
	this.l_commit = l_commit;
}

public String getJobtitle() {
	return jobtitle;
}

public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}

// ObjectId needs to be converted to string
  public  String get_id() {
	  return _id.toHexString(); 
  }
  public  void set_id(ObjectId _id) {
	  this._id = _id; 
}
  
  public String getNumber() { 
	  return bNumber; 
	  }
  public String getBuildStatus() {
	return buildStatus;
}

public void setBuildStatus(String buildStatus) {
	this.buildStatus = buildStatus;
}

public void setNumber(String number) { 
	  this.bNumber = number;
  }
  
  public String getUrl() {
	  return bUrl;
	 }
  public void setUrl(String url) { 
	  this.bUrl = url; 
	  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}