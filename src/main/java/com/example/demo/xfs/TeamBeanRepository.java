package com.example.demo.xfs;

import com.example.demo.xfs.TeamBean;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface TeamBeanRepository extends MongoRepository <TeamBean, String>{

List<TeamBean> findAll();



}
 


