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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        if (updateUserCommand.getName() != null) {
            this.name = updateUserCommand.getName();
        }
        if (updateUserCommand.getRegistrationNumber() != null) {
            this.registrationNumber = updateUserCommand.getRegistrationNumber();
        }
        if (updateUserCommand.getDateOfBirth() != null) {
            this.dateOfBirth = updateUserCommand.getDateOfBirth();
        }
        if (updateUserCommand.getAddress() != null) {
            this.address = updateUserCommand.getAddress();
        }

        UserUpdated userUpdated = new UserUpdated(this);
        userUpdated.publishAfterCommit();
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
