/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import modeloConection.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansMedico;

public class DaoMedico {
    private ConexaoDB conexao = new ConexaoDB();   

    public void salvar(BeansMedico modMedico)
    {
        conexao.conectar();
        try {
            PreparedStatement pst = conexao.getConnection().prepareStatement("INSERT INTO Medico (nome_medico, especialidade_medico, crm_medico) VALUES (?, ?, ?)");
            pst.setString(1, modMedico.getNome());
            pst.setString(2, modMedico.getEspecialidade());
            pst.setInt(3, modMedico.getCrm());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados:\n" + ex);
        }
        
        conexao.desconectar();
    }
    
    public BeansMedico buscaMedico(String nomeMedico)
    {
        conexao.conectar();
        
        conexao.executaSql("SELECT * FROM Medico WHERE nome_medico LIKE'%" + nomeMedico + "%'");
        try {
            conexao.getResultSet().first();
            
            String nome = conexao.getResultSet().getString("nome_medico");
            String especialidade = conexao.getResultSet().getString("especialidade_medico");
            int crm = conexao.getResultSet().getInt("crm_medico");
            
            BeansMedico modMedico = new BeansMedico(nome, especialidade, crm);
            
            return modMedico;

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao buscar medico dados:\n" + ex);
        }
        
        conexao.desconectar();
        return null;    
    }
    
}//Fim da classe Medico
