package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post_HubReqBody {

    /*
    {
    "name": "New York City",
    "phone": "01000000001",
    "address": "New York City, New York",
    "current_balance": "0.00",
    "status": 1
}
     */
    private String name;
    private String phone;
    private String address;
    private String current_balance;
    private int status;

}
