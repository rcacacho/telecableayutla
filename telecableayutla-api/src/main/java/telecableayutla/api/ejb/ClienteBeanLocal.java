package telecableayutla.api.ejb;

import java.util.List;
import javax.ejb.Local;
import telecableayutla.api.entity.Cliente;

/**
 *
 * @author rcacacho
 */
@Local
public interface ClienteBeanLocal {

    List<Cliente> ListClientes();

    Cliente saveCliente(Cliente cliente);

    Cliente updateCliente(Cliente cliente);

    List<Cliente> ListClientesByNombre(String nombre);

    List<Cliente> ListClientesByCodigo(String cui);

    Cliente findClienteById(Integer idcliente);

    List<Cliente> ListClientesByIdMunucipio(Integer idmunicipio);

    List<Cliente> ListClientesByNombreAndSectorAndMunicipio(String nombre, String sector, Integer idmunicipio);

    List<Cliente> ListClientesByNombreAndSector(String nombre, String sector);

    List<Cliente> ListClientesByNombreAndMunicipio(String nombre, Integer idmunicipio);

    List<Cliente> ListClientesBySectorAndMunicipio(String sector, Integer idmunicipio);

    List<Cliente> ListClientesBySector(String sector);

    List<Cliente> listClientesByInMunucipio();
    
    List<Cliente> ListClientesByIdSector(Integer idsector);

}
