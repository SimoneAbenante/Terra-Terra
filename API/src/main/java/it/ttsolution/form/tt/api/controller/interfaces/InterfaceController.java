package it.ttsolution.form.tt.api.controller.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.inter.InterfaceDto;

@RestController
public interface InterfaceController<D extends InterfaceDto> {

	@GetMapping(value = "/", produces = "application/json")
	List<D> getAll() throws Exception;

	@GetMapping(value = "/{id}", produces = "application/json")
	D getById(@PathVariable Integer id) throws Exception;

	@PostMapping(value = "/", produces = "application/json")
	D save(@RequestBody D dto) throws Exception;

	@PostMapping(value = "/all", produces = "application/json")
	List<D> saveAll(@RequestBody List<D> listDto) throws Exception;

	@DeleteMapping(value = "/{id}", produces = "application/json")
	Boolean delete(@PathVariable Integer id) throws Exception;

	@DeleteMapping(value = "/questometododovrebbeesseresegretoquindinonfacilmenteaccessibile", produces = "application/json")
	Boolean deleteAll() throws Exception;

}
