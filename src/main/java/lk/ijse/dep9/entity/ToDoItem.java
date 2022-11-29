package lk.ijse.dep9.entity;

import lk.ijse.dep9.entity.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem implements SuperEntity {
    int id;
    String username;
    String description;
    Status status;

    public ToDoItem(String username, String description, Status status) {
        this.username = username;
        this.description = description;
        this.status = status;
    }
}
