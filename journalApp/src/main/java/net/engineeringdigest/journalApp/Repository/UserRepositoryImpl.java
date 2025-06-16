package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("Email").ne("").ne(null));
        query.addCriteria(Criteria.where("SentimentAnalysis").is(true));
       List<Users> users = mongoTemplate.find(query,Users.class);
       return users;
    }
}
