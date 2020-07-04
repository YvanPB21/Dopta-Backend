package com.dopta.security.entity;

import com.dopta.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {
    private Integer id;
    private String names;
    private String username;
    private String emailAddress;
    private String password;
    private String profile_pic_url;
    private Date date_of_registration;
    private String last_names;
    private Integer dni;
    private Date date_of_birth;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String nombreUsuario, String email, String password, Integer id, String profile_pic_url, Date date_of_registration, String last_names, Integer dni, Date date_of_birth, Collection<? extends GrantedAuthority> authorities) {
        this.names = nombre;
        this.username = nombreUsuario;
        this.emailAddress = email;
        this.password = password;
        this.profile_pic_url=profile_pic_url;
        this.authorities = authorities;
        this.date_of_registration=date_of_registration;
        this.last_names=last_names;
        this.dni=dni;
        this.date_of_birth=date_of_birth;
        this.id = id;
    }

    public static UsuarioPrincipal build(User usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNames(), usuario.getUsername(), usuario.getEmailAddress(), usuario.getPassword(), usuario.getId(), usuario.getProfile_pic_url(),usuario.getDate_of_registration(),usuario.getLast_names(),usuario.getDni(),usuario.getDate_of_birth(),authorities);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
