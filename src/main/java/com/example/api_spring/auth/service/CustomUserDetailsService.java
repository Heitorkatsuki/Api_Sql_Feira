package com.example.api_spring.auth.service;

import com.example.api_spring.api.models.Role;
import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.services.RoleService;
import com.example.api_spring.api.services.UsuarioService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioService usuarioService;
    private final RoleService roleService;

    public CustomUserDetailsService(UsuarioService usuarioService, RoleService roleService) {
        this.usuarioService = usuarioService;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByUsername(username);
        Role role = roleService.getUserRoleById(usuario.getUserRole());

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        // Pega o primeiro papel do usuário
        String roleName = role.getRoleName();

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getSenha(),
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName)
        ));
    }

}
