package sg.edu.nus.iss.d13.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Employee {

    @NotEmpty(message = "First name is mandatory")
    @Size(min = 3, max = 100, message = "First name must be between 3 to 100 char")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory")
    private String lastName;

    @Email(message = "come on bro it gotta be an email")
    @Size(max = 30, message = "Email lenght exceeded 30 characters")
    @NotBlank(message = "email is required")
    private String email;
    @Pattern(regexp = "(\\8|9)[0-9]{7}") // this means first number must be 8/9 <then the other 7 numbers must be
                                         // between 0-9
    private Integer phoneNo;
    @Min(value = 1500, message = "Get your money up not your funny up,1.5k bands and above only")
    @Max(value = 500000, message = "Now i know u capping aint no way you earn fomr than 50,000")
    private Integer salary;
    @Past(message = "bro really think he born in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @Digits(fraction = 0, integer = 6, message = "Postal code must be 6 digits")
    @Min(value = 111111, message = "Starts from 111111")
    @Max(value = 111111, message = "Starts from 999999")
    private Integer postalCode;

    // control + shift + p > source action >generate getters and setters.

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }
    //notice how i camel cased here* if it isnt exactly the same in templates it wont work.
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Employee(
            @NotEmpty(message = "First name is mandatory") @Size(min = 3, max = 100, message = "First name must be between 3 to 100 char") String firstName,
            @NotEmpty(message = "Last name is mandatory") String lastName,
            @Email(message = "come on bro it gotta be an email") @Size(max = 30, message = "Email lenght exceeded 30 characters") @NotBlank(message = "email is required") String email,
            @Pattern(regexp = "(\\8|9)[0-9]{7}") Integer phoneNo,
            @Min(value = 1500, message = "Get your money up not your funny up,1.5k bands and above only") @Max(value = 500000, message = "Now i know u capping aint no way you earn fomr than 50,000") int salary,
            @Past(message = "bro really think he born in the future") java.util.Date dt,
            @Digits(fraction = 0, integer = 6, message = "Postal code must be 6 digits") @Min(value = 111111, message = "Starts from 111111") @Max(value = 111111, message = "Starts from 999999") Integer postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.salary = salary;
        this.birthday = dt;
        this.postalCode = postalCode;
    }
    //this allows for a blannk form
    public Employee() {
    }
    

    
}
