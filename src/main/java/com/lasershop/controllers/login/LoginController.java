package com.lasershop.controllers.login;

import com.lasershop.dtos.request.login.UsuarioRequestDTO;
import com.lasershop.services.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/login")
@Validated
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        return loginService.registrarUsuario(usuarioRequestDTO);
    }

    @PutMapping("/editarUsuario")
    public ResponseEntity<?> editarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) throws Exception {
        return loginService.editarUsuario(usuarioRequestDTO);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> getTodosUsuarios(){
        return loginService.getTodosUsuarios();
    }

    @GetMapping("/getUsuarioPorNome")
    public ResponseEntity<?> getUsuarioPorNome(@RequestParam String nome){
        return loginService.getUsuarioPorNome(nome);
    }
}
