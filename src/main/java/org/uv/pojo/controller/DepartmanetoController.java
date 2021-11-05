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
import org.uv.pojo.Departamento;
import org.uv.repository.DepartamentoRepository;

/**
 *
 * @author gaddiel
 */
@RestController
@RequestMapping("/departamentos")
public class DepartmanetoController {

    @Autowired
    private DepartamentoRepository dr;

    @GetMapping("/")
    public List<Departamento> showAll() {
        return dr.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Departamento departamento, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity("Verifique los datos", HttpStatus.BAD_REQUEST);
        }

        Departamento dep = dr.save(departamento);

        return new ResponseEntity(dep, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Departamento departamento,
            BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity("Verifique los datos", HttpStatus.BAD_REQUEST);
        }

        Optional<Departamento> d = dr.findById(id);
        d.get().setNombre(departamento.getNombre());

        Departamento dep = dr.save(d.get());

        return new ResponseEntity(dep, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Optional<Departamento> departamento = null;
        Map<String, Boolean> respuesta = new HashMap<>();

        if (dr.existsById(id)) {
            departamento = dr.findById(id);
            dr.delete(departamento.get());
            respuesta.put("delete", Boolean.TRUE);
            return new ResponseEntity(respuesta, HttpStatus.OK);
        } else {
            respuesta.put("delete", Boolean.FALSE);
            return new ResponseEntity(respuesta, HttpStatus.BAD_REQUEST);
        }

    }

}
