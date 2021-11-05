/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.pojo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.pojo.Empleado;
import org.uv.repository.EmpleadoRepository;

/**
 *
 * @author gaddiel
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository er;

    @GetMapping("/")
    public List<Empleado> showAll() {
        return er.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Empleado empleado, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity("Verifique los campos", HttpStatus.BAD_REQUEST);
        }

        Empleado emp = er.save(empleado);
        return new ResponseEntity(emp, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Empleado empleado,
            BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity("Verifique los datos", HttpStatus.BAD_REQUEST);
        }

        Optional<Empleado> e = er.findById(id);
        e.get().setNombre(empleado.getNombre());
        e.get().setDireccion(empleado.getDireccion());
        e.get().setTelefono(empleado.getTelefono());
        e.get().setDepartamento(empleado.getDepartamento());

        Empleado emp = er.save(e.get());

        return new ResponseEntity(emp, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        Map<String, Boolean> respuesta = new HashMap<>();
        Optional<Empleado> empleado = null;

        if (er.existsById(id)) {
            empleado = er.findById(id);
            er.delete(empleado.get());
            respuesta.put("delete", Boolean.TRUE);
            return new ResponseEntity(respuesta, HttpStatus.OK);
        } else {
            respuesta.put("delete", Boolean.FALSE);
            return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
        }

    }

}
