package lk.ijse.hostelmanagementsystem.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String city;
    private String gmail;
}
