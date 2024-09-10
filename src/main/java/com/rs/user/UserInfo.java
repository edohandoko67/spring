package com.rs.user;

import com.rs.product.cart.Cart;
import com.rs.role.Role;
import com.rs.user.toko.JadwalTokoSales;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "user")
public class UserInfo implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50,unique = true)
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    @Column(nullable = false, length = 64)
    @NotNull
    @Length(min = 8, max = 64)
    private String password;

    @Nullable
    @Length(max = 50)
    private String name;

    @Nullable
    @Length(max = 50)
    private String address;

    @NotNull
    @Length(max = 16)
    private String number;

    @Column(name = "gender")
    private Integer gender;

    @OneToMany(mappedBy = "userInfo")
    private Set<Cart> cart;

    @OneToMany(mappedBy = "userInfo")
    private Set<JadwalTokoSales> jadwalTokoSales;

    public UserInfo(){

    }

    public UserInfo(Integer id, String username, String password, @Nullable String name, @Nullable String address, String number, Integer gender, Set<Cart> cart, Set<JadwalTokoSales> jadwalTokoSales) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.number = number;
        this.gender = gender;
        this.cart = cart;
        this.jadwalTokoSales = jadwalTokoSales;
    }

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Set<Cart> getCart() {
        return cart;
    }

    public void setCart(Set<Cart> cart) {
        this.cart = cart;
    }

    public Set<JadwalTokoSales> getJadwalTokoSales() {
        return jadwalTokoSales;
    }

    public void setJadwalTokoSales(Set<JadwalTokoSales> jadwalTokoSales) {
        this.jadwalTokoSales = jadwalTokoSales;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}