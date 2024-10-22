package com.example.introduccionbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurses")
public class NurseController {
	@Autowired
	private NurseRepository nurseRepository;
	//private List<Nurse> nurseList = new ArrayList<>();

	/*public NurseController() {
		nurseList.add(new Nurse(1, "nurse1", "password1"));
		nurseList.add(new Nurse(2, "nurse2", "password2"));
	}

	@GetMapping("/all")
	public List<Nurse> getAllNurses() {
		return nurseList;
	}

	@GetMapping("/name/{name}")
	private ResponseEntity<Nurse> findByName(@PathVariable String name) {
		for (Nurse nurse : nurseList) {
			System.out.println(nurse);
			if (name.equals(nurse.getUsername())) {
				return ResponseEntity.ok(nurse);
			}
		}
		return ResponseEntity.notFound().build();
	}*/

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
    Optional<Nurse> existingNurse = nurseRepository.findByUsernameAndPassword(username, password);
    if (existingNurse.isPresent()) {
        return new ResponseEntity<>("Username and password already exist. Login failed.", HttpStatus.CONFLICT);
    }
    Nurse newNurse = new Nurse();
    newNurse.setUsername(username);
    newNurse.setPassword(password);
    nurseRepository.save(newNurse);
    return new ResponseEntity<>("Login successful", HttpStatus.OK);
	}
}

	/*public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
		for (Nurse nurse : nurseList) {
			if (nurse.getUsername().equals(username) && nurse.getPassword().equals(password)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}*/