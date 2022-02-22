package com.lasershop.services.login;

import com.lasershop.domains.Erros;
import com.lasershop.dtos.request.login.UsuarioRequestDTO;
import com.lasershop.dtos.response.login.UsuarioResponseDTO;
import com.lasershop.models.endereco.Endereco;
import com.lasershop.models.login.Usuario;
import com.lasershop.repositorys.endereco.EnderecoRepository;
import com.lasershop.repositorys.login.UsuarioRepository;
import com.lasershop.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;


    public ResponseEntity<?> registrarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws NoSuchAlgorithmException {
        Optional<Usuario> usuario = usuarioRepository.findByNome(usuarioRequestDTO.getNome());
        if(usuario.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Utils.gerarErro(Erros.USUARIO_JA_CADASTRADO.getDescricao()));
        }
        salvarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws NoSuchAlgorithmException {
        if(Objects.isNull(usuarioRequestDTO.getId())){
            return ResponseEntity.badRequest().body(Utils.gerarErro(Erros.MENSAGEM_ERRO_DEFAULT.getDescricao()));
        }
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioRequestDTO.getId());
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Utils.gerarErro(Erros.USUARIO_NAO_ENCONTRADO.getDescricao()));
        }
        salvarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok().body(Utils.converterUsuarioListToResponse(usuarios));
    }

    public ResponseEntity<?> getUsuarioPorNome(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if(usuario.isEmpty()){
            return ResponseEntity.badRequest().body(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
        }
        UsuarioResponseDTO usuarioResponse = Utils.converterUsuarioToResponse(usuario.get());
        return ResponseEntity.ok().body(usuarioResponse);
    }

    private void salvarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws NoSuchAlgorithmException {
        String senhaCriptografada = Utils.encriptarStringSHA256(usuarioRequestDTO.getSenha());
        usuarioRequestDTO.setSenha(senhaCriptografada);

        Endereco enderecoACadastrar;
        Endereco enderecoUsuario = usuarioRequestDTO.getEndereco();
        Long idEnderecoUsuario = enderecoUsuario.getId();


        if(idEnderecoUsuario == null){
            enderecoACadastrar = enderecoRepository.save(enderecoUsuario);
        }else{
            enderecoACadastrar = enderecoRepository.getById(idEnderecoUsuario);
            if(!enderecoACadastrar.equals(enderecoUsuario))
                enderecoRepository.save(enderecoUsuario);
        }

        Usuario usuarioCadastrado = Utils.converterUsuarioRequest(usuarioRequestDTO, enderecoACadastrar);
        usuarioRepository.save(usuarioCadastrado);
    }


}
