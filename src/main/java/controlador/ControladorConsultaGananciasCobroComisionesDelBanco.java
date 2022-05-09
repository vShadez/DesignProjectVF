
package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logicaDeAccesoADatos.DAOOperacionCatalogoDeCuentas;
import vistaGUI.ConsultaGananciasCobroComisionesDelBanco;
import logicaDeAccesoADatos.IDAOOperacionCatalogoDeCuentas;
import logicaDeAccesoADatos.DAOOperacionCuenta;
import clasesUtilitarias.Conversion;
/**
 *
 * @author estadm
 */
public class ControladorConsultaGananciasCobroComisionesDelBanco implements ActionListener{
    public ConsultaGananciasCobroComisionesDelBanco vistaGUI;
    
    
    public ControladorConsultaGananciasCobroComisionesDelBanco(ConsultaGananciasCobroComisionesDelBanco pVistaGUI) {
        this.vistaGUI = pVistaGUI;
        this.vistaGUI.btnCancelarConsultaComision.addActionListener(this);
        this.vistaGUI.btnConsultarMontosPorCobroComision.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getActionCommand().equals("Consultar")) {
            IDAOOperacionCatalogoDeCuentas comisionesCobradas = new DAOOperacionCatalogoDeCuentas();
            double comisionesTotalesDeposito = comisionesCobradas.consultarMontoTotalCobradoComisionesPorDepositos();
            double comisionesTotalesRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetiros();
            double comisionesTotalesDepositoRetiros = comisionesCobradas.consultarMontoTotalCobradoComisionesPorRetirosYDepositos();
            vistaGUI.txtMontoTotalOperacionesDepositos.setText(""+comisionesTotalesDeposito);
            vistaGUI.txtMontoTotalOperacionesRetiros.setText(""+comisionesTotalesRetiros);
            vistaGUI.txtMontoTotalOperacionesDepositosYRetiros.setText(""+comisionesTotalesDepositoRetiros);
            
        }
    }
    
}
