/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uv.pojo.Empleado;

/**
 *
 * @author gaddiel
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
}
