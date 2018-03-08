/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoDB {
    private Statement statement; //Responsavel por realizar a pesquisa no banco de dados
    private ResultSet resultSet; //Armazena o resultado da pesquisa
    private String caminho = "jdbc:mysql://localhost/projetoclinica"; 
    private String usuario = "kainan";
    private String senha = "kainanxd";
    private Connection connection; //Responsavel por realizar a conexao
    
    //Metodo responsavel por realizar a conexao com a base de dados
    public void conectar()
    {
        try {
            connection = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexão efetuada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com o banco de dados:\n" + ex.getMessage());
        }
    }
    
    public void executaSql(String sql)
    {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro executaSql: \n" + ex.getMessage());
        }
    }
    
    //Metodo responsavel por realizar a desconexao
    public void desconectar()
    {
        try {
            connection.close(); // Fecha a conexao
            //JOptionPane.showMessageDialog(null, "BD desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados:\n" + ex.getMessage());
        }
    }

    //Retorna Conexao

    public Connection getConnection() {
        return connection;
    }
    
    public ResultSet getResultSet()
    {
        return resultSet;
    }
    
         
}//Fim da classe ConexaoDB
