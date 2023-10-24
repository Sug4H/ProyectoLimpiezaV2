package Controller;

import Database.ActividadLimpiezaDAO;
import Model.ActividadLimpieza;
import java.util.List;

public class ActividadLimpiezaController {
    private ActividadLimpiezaDAO actividadLimpiezaDAO;

    public ActividadLimpiezaController() {
        actividadLimpiezaDAO = new ActividadLimpiezaDAO();
    }

    public void agregarActividad(ActividadLimpieza actividad) {
        actividadLimpiezaDAO.insertarActividadLimpieza(actividad);
    }

    public void actualizarActividad(ActividadLimpieza actividad) {
        actividadLimpiezaDAO.actualizarActividadLimpieza(actividad);
    }

    public void eliminarActividad(int actividadId) {
        actividadLimpiezaDAO.eliminarActividadLimpieza(actividadId);
    }

    public List<ActividadLimpieza> obtenerTodasLasActividades() {
        return actividadLimpiezaDAO.obtenerTodasLasActividades();
    }

    public int obtenerResumenPorCuadrilla(int idCuadrilla) {
        List<ActividadLimpieza> actividades = obtenerTodasLasActividades();
        int count = 0;
        for (ActividadLimpieza actividad : actividades) {
            if (actividad.getId_jefeCuadrilla() == idCuadrilla) {
                count++;
            }
        }
        return count;
    }
}
