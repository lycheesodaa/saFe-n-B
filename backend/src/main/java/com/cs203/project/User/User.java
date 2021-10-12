package com.cs203.project.User;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "email should not be null")
    @Email(message = "Email must be valid")
    private String username;//The user email

  
    @NotNull(message = "Password should not be null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @NotNull(message = "Authorities should not be null")
    private String authorities;

    @NotNull(message = "Outlet type cannot be null")
    private String OType;

    @NotNull(message = "Company name cannot be null")
    private String CName;

    @NotNull(message = "Contact number")
    @Size(min = 8, max = 8, message = "Singapore phone numbers are 8 digits long")
    private int contNo;

    @NotNull(message = "Creation Date cannot be null")
    private Date cDate;



    public User(String username, String password, String authorities, String OType, String CName, int contNo) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.OType = OType;
        this.CName = CName;
        this.contNo = contNo;
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        this.cDate = new java.sql.Date(currentDate.getTime());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(authorities));
    }
		
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String pword) {
        password = pword;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setOType(String ot){
        OType = ot;
    }
    public void setCName(String cn){
        CName = cn;
    }
    public void setContNo(int cno){
        contNo = cno;
    }
    public void setDate(Date CD){
        cDate = CD;
    }

    public String getOType(){
        return OType;
    }
    public String getCName(){
        return CName;
    }
    public int getContNo(){
        return contNo;
    }
    public Date getDate(){
        return cDate;
    }

    

    

    

}
