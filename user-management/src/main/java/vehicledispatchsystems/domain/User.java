package vehicledispatchsystems.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import vehicledispatchsystems.UserManagementApplication;
import vehicledispatchsystems.domain.UserCreated;
import vehicledispatchsystems.domain.UserDeleted;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    private String name;

    private String registrationNumber;

    private String dateOfBirth;

    private String address;

    @PostPersist
    public void onPostPersist() {
        UserCreated userCreated = new UserCreated(this);
        userCreated.publishAfterCommit();

        UserDeleted userDeleted = new UserDeleted(this);
        userDeleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static UserRepository repository() {
        UserRepository userRepository = UserManagementApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }

    //<<< Clean Arch / Port Method
    public void updateUser(UpdateUserCommand updateUserCommand) {
        //implement business logic here:

        UserUpdated userUpdated = new UserUpdated(this);
        userUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
