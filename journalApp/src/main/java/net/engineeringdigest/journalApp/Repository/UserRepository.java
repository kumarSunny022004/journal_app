package net.engineeringdigest.journalApp.Repository;


import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
Users findByUsername(String Username);
void deleteByUsername(String Username);
}
