package org.example.cards.form;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserFormBean {

    private Integer id;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String password;

    // You can add more fields as needed, such as:
    // private String phoneNumber;
    // private String address;
    // private Boolean isActive;

    // If you want to allow password changes, you might include:
    // private String newPassword;
    // private String confirmNewPassword;

    // If you have user roles, you might include:
    // private String role;

    // Default constructor
    public EditUserFormBean() {
    }

    // Constructor with fields
    public EditUserFormBean(Integer id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // toString method for logging/debugging
    @Override
    public String toString() {
        return "EditUserFormBean{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}