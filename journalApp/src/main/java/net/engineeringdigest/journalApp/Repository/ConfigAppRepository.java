package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}



//controller -->service -->repository