package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }








    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0) {loadData();}
    }

    private void loadData() {
        //Loading data
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dermatology = new Specialty();
        dermatology.setDescription("Dermatology");
        Specialty savedDermatology = specialtyService.save(dermatology);

        Owner owner1 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Boston");
        owner1.setAddress("Brivibas iela 1");
        owner1.setCity("Riga");
        owner1.setTelephone("+371 29000000");
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);


        ownerService.save(owner1);

        Owner owner2 = new Owner();

        owner2.setFirstName("Robert");
        owner2.setLastName("Chicago");
        owner2.setAddress("Brivibas iela 2");
        owner2.setCity("Riga");
        owner2.setTelephone("+371 29000001");

        Pet bobsCat = new Pet();
        bobsCat.setName("Sniedzite");
        bobsCat.setOwner(owner2);
        bobsCat.setBirthDate(LocalDate.now());
        bobsCat.setPetType(savedCatPetType);
        owner2.getPets().add(bobsCat);

        ownerService.save(owner2);

        //Adding visit for a cat(hahahaha)
        Visit catVisit = new Visit();

        catVisit.setPet(bobsCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();

        vet1.setFirstName("Graham");
        vet1.setLastName("Atlanta");
        vet1.getSpecialties().add(savedRadiology);
        vet1.getSpecialties().add(savedDermatology);

        vetService.save(vet1);

        Vet vet2 = new Vet();

        vet2.setFirstName("Sarah");
        vet2.setLastName("Michigan");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);
        System.out.println("Loaded Vets...");
    }
}
