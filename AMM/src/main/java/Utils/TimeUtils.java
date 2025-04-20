package Utils;
/**
 *
 * @author nyath
 */
import java.sql.Time;
import java.time.Duration;
import java.sql.Timestamp;
import java.time.LocalTime;

public class TimeUtils {
    public Time calcHoraFinalizacion(Time hora_inicial, Time duracion){
        // Convertir a LocalTime
        LocalTime inicial = hora_inicial.toLocalTime();
        LocalTime tiempoDuracion = duracion.toLocalTime();
        
        // Crear Duration
        Duration tiempo_sum = Duration.ofHours(tiempoDuracion.getHour()).plusMinutes(tiempoDuracion.getMinute());
        
        // Sumar duración
        LocalTime result = inicial.plus(tiempo_sum);
        
        // Retornar valor como sql.Time
        return Time.valueOf(result);
    }
    
    public Timestamp getNowTime (){
        return new Timestamp(System.currentTimeMillis());
    }
}
