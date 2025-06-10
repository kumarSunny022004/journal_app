package net.engineeringdigest.journalApp.entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
@EqualsAndHashCode
@Builder
public class JournalEntry {


    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

}
