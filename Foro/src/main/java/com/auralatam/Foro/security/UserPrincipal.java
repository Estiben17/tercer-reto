package com.auralatam.Foro.security;

import com.auralatam.Foro.entidades.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String nombre;
    private String contrasena;
    private String correoElectronico;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String nombre, String contrasena, String correoElectronico, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(usuario.getPerfil().getNombre()));
        return new UserPrincipal(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getContrasena(),
                usuario.getCorreoElectronico(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
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

