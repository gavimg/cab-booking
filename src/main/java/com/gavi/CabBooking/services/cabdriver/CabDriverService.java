package com.gavi.CabBooking.services.cabdriver;

import com.gavi.CabBooking.dtos.CabDriverCabDTO;
import com.gavi.CabBooking.entities.CabDriver;
import org.springframework.http.ResponseEntity;

public interface CabDriverService {
    public ResponseEntity<CabDriver> insertCabDriver(CabDriverCabDTO cabdto);

    public ResponseEntity<CabDriver> updateCabDriver(CabDriverCabDTO cabdto, String user, String pass);

    public ResponseEntity<String> deleteCabDriver(CabDriver cabdto);

    public ResponseEntity<String> updateStatus(CabDriver cabdto);
}
