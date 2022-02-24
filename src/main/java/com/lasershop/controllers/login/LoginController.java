package com.lasershop.controllers.login;

import com.lasershop.dtos.request.login.UsuarioRequestDTO;
import com.lasershop.services.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;


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


    @PutMapping("/editar")
    public ResponseEntity<?> editarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO)  {
        return loginService.editarUsuario(usuarioRequestDTO);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> getTodosUsuarios(){
        return loginService.getTodosUsuarios();
    }

    @GetMapping("/porNome")
    public ResponseEntity<?> getUsuarioPorNome(@RequestParam String nome){
        return loginService.getUsuarioPorNome(nome);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarUsuario(@RequestParam Long id){
        return loginService.deletarUsuario(id);
    }


}
