package src.repositories;

import src.models.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {
    private List<Usuario> usuarios = new ArrayList<>();
    private int ultimoId = 0;
    
    private static UsuarioRepositorio instance;
    
    private UsuarioRepositorio() {};
    
    public static UsuarioRepositorio getInstance() {
    	if (instance == null) {
    		instance = new UsuarioRepositorio();
    	}
    	return instance;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticarUsuario(String login, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    public boolean buscarUsuario(String login) {
    	for (Usuario usuario : usuarios) {
    		if (usuario.getLogin() == login) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public Usuario buscarUsuario(int id) {
    	for (Usuario usuario : usuarios) {
    		if (usuario.getId() == id) {
    			return usuario;
    		}
    	}
    	return null;
    }

    public void editarUsuario(int index, Usuario usuario) {
        if (index >= 0 && index < usuarios.size()) {
            usuarios.set(index, usuario);
        }
    }

    public void excluirUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public int gerarId() {
        ultimoId++;  // Incrementa o ID
        return ultimoId; // Retorna o novo ID
    }
}
