package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HubAddReqBody {
    /*
    {
    "name": "Fatma",
    "phone": "123456789",
    "address": "Antalya, Turkey"
     }
     */
    private String name;
    private String phone;
    private String address;

}
