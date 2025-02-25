package src.repositories;

import src.models.Usuario;

import java.util.List;

public interface RepositorioUsuarioInterface {
    void cadastrarUsuario(Usuario usuario);

    Usuario autenticarUsuario(String login, String senha);

    boolean buscarUsuario(String login);

    void editarUsuario(int index, Usuario usuario);

    void excluirUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();
}
