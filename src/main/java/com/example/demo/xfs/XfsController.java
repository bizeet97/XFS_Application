package com.example.demo.xfs;


import java.util.List;
import java.util.Map;

import com.example.demo.xfs.TeamBeanRepository;

import com.twilio.sdk.TwilioRestClient;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
 
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins="http://ec2-52-66-245-186.ap-south-1.compute.amazonaws.com:4200")
@RequestMapping("/api")
public class XfsController {  
	public static final String ACCOUNT_SID = "AC8f66f031fd4cc49c090d4a6f93731357";
    public static final String AUTH_TOKEN = "9dc63e1919aa6b1a4854a08328723109";
    public static final String TWILIO_NUMBER = "+19167401802";
    
    
    /*public void sendSMS(String bUrl,String bNumber,String buildStatus,String l_commit,String jobtitle ) {
    	System.out.println("M being called");
    	try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", "Jenkins job called "+jobtitle+"available at "+bUrl+"with build number "+bNumber+"has been failed by "+l_commit));
            params.add(new BasicNameValuePair("To", "+917978059987")); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            
            System.out.println(message.getSid());
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }*/
	@Autowired
	private  MongoTemplate mongoTemplate; 
	
	
	@Autowired
	private  XfsRepository xfsRepository; 
	
	@Autowired
	private  TeamBeanRepository TeamBeanRepository; 
	
	public XfsController(XfsRepository xfsRepository, TeamBeanRepository TeamBeanRepository) {
		this.xfsRepository=xfsRepository;
		this.TeamBeanRepository=TeamBeanRepository;
	}
	
		
	
	
	@GetMapping("/xfs")
	public Xfs getXFS() {
	  List<Xfs> xfs = xfsRepository.findAll();
	  
	  return xfs.get(xfs.size() - 1);
	  
	}
	
	@GetMapping("/teamlist")
	public  List<TeamBean> getTeamBean() {
	  List<TeamBean> TeamBean = TeamBeanRepository.findAll();
	  
	  return TeamBean;
	  
	}
	
	@PostMapping("/addteam")
	public TeamBean addXFSData(@RequestBody TeamBean TeamBean) {
		TeamBean.set_id(ObjectId.get());
		TeamBeanRepository.save(TeamBean);
		return TeamBean;
	}
		

	@PostMapping("/addbuildinfo")
	public TeamBean addTeamBeanData(@RequestBody Map<String,Object> buildInfo) {
		try {
		TeamBean p= mongoTemplate.findOne(
				  Query.query(Criteria.where("teamName").is((buildInfo).get("teamName"))), TeamBean.class);
		String phoneNumber=p.member.get(0).getMember_no();
		String gitUserName=p.gitUserName;
		String gitRepoName=p.gitRepoName;
		 String URL="https://api.github.com/repos/"+gitUserName+"/"+gitRepoName+"/commits";
		 System.out.println(URL);
		  RestTemplate restTemplate = new RestTemplate();
		      String result = restTemplate.getForObject(URL.toString(), String.class);
		      
		      
		      String arr[] = result.split(":");
		      System.out.println(arr.length);

		      String str=arr[5];
		      String arr2[] = str.split(",");
		 System.out.println(arr2[0]);

		p.bUrl= buildInfo.get("bUrl").toString();
		p.bNumber= buildInfo.get("bNumber").toString();
		p.buildStatus= buildInfo.get("buildStatus").toString();
		p.l_commit=arr2[0].replaceAll("^\"|\"$","");
		p.jobtitle= buildInfo.get("jobtitle").toString();
		
		if(p.buildStatus.equals("FAILURE")) {
			
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build a filter for the MessageList
		System.out.println("About to Call");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body", "Jenkins job called "+p.jobtitle+" available at "+p.bUrl+" with build number "+p.bNumber+" has been failed by "+p.l_commit));
        params.add(new BasicNameValuePair("To", "+917978059987")); //Add real number here
        params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        
        System.out.println(message.getSid());
		}
		TeamBeanRepository.save(p); 
		
		return p;
		
		
		}
		 catch (TwilioRestException e) {
	            System.out.println(e.getErrorMessage());
	            return null;
	        }
		
	}
}



		
   
	
	

