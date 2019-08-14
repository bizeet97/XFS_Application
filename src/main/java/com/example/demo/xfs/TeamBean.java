package com.example.demo.xfs;


import com.example.demo.xfs.XfsRepository;
import com.example.demo.xfs.Xfs;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.client.RestTemplate;
import java.util.List;

//@Document (collection="XfsStatus")

public class TeamBean {
  @Id
  public ObjectId _id;
  public String teamName;
	/* public String memberName; */
  public List<Member> member;
  public String gitUserName;
  public String gitRepoName;
  public String getGitRepoName() {
	return gitRepoName;
}

public void setGitRepoName(String gitRepoName) {
	this.gitRepoName = gitRepoName;
}

public String jobtitle;
  public String bNumber;
  public String bUrl;
  public String buildStatus;
  public String l_commit;
 
 
  //public String l_commit;
 
  
  // Constructors
  public TeamBean() {}
  
  public TeamBean(ObjectId id,String teamName, List<Member> member ,String gitRepoName,String gitUserName,String jobtitle,String bNumber,String buildStatus,String l_commit) 
  {
	  super();
    this._id = id;
    this.teamName= teamName;
    this.member= member;
    this.gitUserName= gitUserName;
    this.gitRepoName= gitRepoName;
   
    
  }



public String getJobtitle() {
	return jobtitle;
}

public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}

public String getbNumber() {
	return bNumber;
}

public void setbNumber(String bNumber) {
	this.bNumber = bNumber;
}

public String getbUrl() {
	return bUrl;
}

public void setbUrl(String bUrl) {
	this.bUrl = bUrl;
}

public String getBuildStatus() {
	return buildStatus;
}

public void setBuildStatus(String buildStatus) {
	this.buildStatus = buildStatus;
}

public String getL_commit() {
	return l_commit;
}

public void setL_commit(String l_commit) {
	this.l_commit = l_commit;
}

public  String get_id() {
	  return _id.toHexString(); 
  }
  public  void set_id(ObjectId _id) {
	  this._id = _id; 
}

public String getTeamName() {
	return teamName;
}

public void setTeamName(String teamName) {
	this.teamName = teamName;
}

public List<Member> getMember() {
	return member;
}

public void setMember(List<Member> member) {
	this.member = member;
}






	/*
	 * public String getL_commit() { String URL =
	 * "https://api.github.com/repos/bizeet97/XFS_Spring/commits";
	 * System.out.println(URL); RestTemplate restTemplate = new RestTemplate();
	 * String result = restTemplate.getForObject(URL, String.class);
	 * 
	 * String arr[] = result.split(":"); System.out.println(arr.length);
	 * 
	 * String str=arr[5]; String arr2[] = str.split(",");
	 * System.out.println(arr2[0]);
	 * 
	 * return arr2[0].replaceAll("^\"|\"$",""); }
	 * 
	 * public void setL_commit(String l_commit) { this.l_commit = l_commit; }
	 */


  
  
}