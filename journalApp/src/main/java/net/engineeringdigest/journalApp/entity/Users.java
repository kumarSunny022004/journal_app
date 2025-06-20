package net.engineeringdigest.journalApp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username ;
    @NonNull
    private String password;

    private String Email;

    private boolean SentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalentries = new ArrayList<>();

    private List<String> roles;
}
