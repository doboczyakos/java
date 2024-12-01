package com.example.gyakorlat;

import com.example.gyakorlat.hulladekSzallitas.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
class SeedDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepo userRepository,
                                   LakigRepo lakigRepository,
                                   NaptarRepo naptarRepository,
                                   SzolgaltatasRepo szolgaltatasRepository) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            if (userRepository.count() == 0) {
                Iterable<User> users = List.of(new User[] {
                        new User("admin", passwordEncoder.encode("Asdf1234"), "Admin", "ADMIN"),
                        new User("user", passwordEncoder.encode("Uiop1234"), "Felhasználó", "USER"),
                        new User("visitor", passwordEncoder.encode("Vbnm1234"), "Látogató", "VISITOR")
                });
                userRepository.saveAll(users);
            }

            if (szolgaltatasRepository.count() == 0) {
                Iterable<Szolgaltatas> szolgaltatasok = List.of(new Szolgaltatas[]{
                        new Szolgaltatas(1, "mua", "Műanyag hulladék: PET palack, kozmetikai flakonok(PP+HDPE), szatyor, zacskó."),
                        new Szolgaltatas(2, "uv", "Üveg hulladék: színes és fehér üveg."),
                        new Szolgaltatas(3, "zold", "Zöldhulladék: komposztálható, kerti hulladékok."),
                        new Szolgaltatas(4, "pa", "Papírhulladékok: újságok, könyvek, kartondobozok."),
                        new Szolgaltatas(5, "kom", "Kommunális hulladék: szilárd, a lakókörnyezetünkben található, nem lebomló, nem veszélyes hulladék.")
                });
                szolgaltatasRepository.saveAll(szolgaltatasok);
            }
            if (naptarRepository.count() == 0){
                var naptarStr = "2018.01.03\t1\n" +
                        "2018.01.03\t4\n" +
                        "2018.01.04\t5\n" +
                        "2018.01.11\t5\n" +
                        "2018.01.17\t1\n" +
                        "2018.01.17\t4\n" +
                        "2018.01.18\t5\n" +
                        "2018.01.18\t3\n" +
                        "2018.01.25\t5\n" +
                        "2018.01.25\t3\n" +
                        "2018.01.31\t1\n" +
                        "2018.01.31\t4\n" +
                        "2018.02.01\t5\n" +
                        "2018.02.08\t5\n" +
                        "2018.02.14\t1\n" +
                        "2018.02.14\t4\n" +
                        "2018.02.15\t5\n" +
                        "2018.02.22\t5\n" +
                        "2018.02.28\t1\n" +
                        "2018.02.28\t4\n" +
                        "2018.03.01\t5\n" +
                        "2018.03.05\t3\n" +
                        "2018.03.08\t5\n" +
                        "2018.03.12\t3\n" +
                        "2018.03.14\t1\n" +
                        "2018.03.14\t4\n" +
                        "2018.03.15\t5\n" +
                        "2018.03.19\t3\n" +
                        "2018.03.22\t5\n" +
                        "2018.03.26\t3\n" +
                        "2018.03.28\t1\n" +
                        "2018.03.28\t4\n" +
                        "2018.03.29\t5\n" +
                        "2018.04.02\t3\n" +
                        "2018.04.05\t5\n" +
                        "2018.04.09\t3\n" +
                        "2018.04.11\t1\n" +
                        "2018.04.11\t4\n" +
                        "2018.04.12\t5\n" +
                        "2018.04.16\t3\n" +
                        "2018.04.19\t5\n" +
                        "2018.04.23\t3\n" +
                        "2018.04.25\t1\n" +
                        "2018.04.25\t4\n" +
                        "2018.04.26\t5\n" +
                        "2018.04.30\t3\n" +
                        "2018.05.03\t5\n" +
                        "2018.05.07\t3\n" +
                        "2018.05.09\t1\n" +
                        "2018.05.09\t4\n" +
                        "2018.05.10\t5\n" +
                        "2018.05.14\t3\n" +
                        "2018.05.17\t5\n" +
                        "2018.05.21\t3\n" +
                        "2018.05.23\t1\n" +
                        "2018.05.23\t4\n" +
                        "2018.05.24\t5\n" +
                        "2018.05.28\t3\n" +
                        "2018.05.31\t5\n" +
                        "2018.06.03\t5\n" +
                        "2018.06.04\t3\n" +
                        "2018.06.06\t1\n" +
                        "2018.06.06\t4\n" +
                        "2018.06.07\t5\n" +
                        "2018.06.10\t5\n" +
                        "2018.06.11\t3\n" +
                        "2018.06.14\t5\n" +
                        "2018.06.17\t5\n" +
                        "2018.06.18\t3\n" +
                        "2018.06.20\t1\n" +
                        "2018.06.20\t4\n" +
                        "2018.06.21\t5\n" +
                        "2018.06.24\t5\n" +
                        "2018.06.25\t3\n" +
                        "2018.06.28\t5\n" +
                        "2018.07.01\t5\n" +
                        "2018.07.02\t3\n" +
                        "2018.07.04\t1\n" +
                        "2018.07.04\t4\n" +
                        "2018.07.05\t5\n" +
                        "2018.07.08\t5\n" +
                        "2018.07.09\t3\n" +
                        "2018.07.12\t5\n" +
                        "2018.07.15\t5\n" +
                        "2018.07.16\t3\n" +
                        "2018.07.18\t1\n" +
                        "2018.07.18\t4\n" +
                        "2018.07.19\t5\n" +
                        "2018.07.22\t5\n" +
                        "2018.07.23\t3\n" +
                        "2018.07.26\t5\n" +
                        "2018.07.29\t5\n" +
                        "2018.07.30\t3\n" +
                        "2018.08.01\t1\n" +
                        "2018.08.01\t4\n" +
                        "2018.08.02\t5\n" +
                        "2018.08.05\t5\n" +
                        "2018.08.06\t3\n" +
                        "2018.08.09\t5\n" +
                        "2018.08.12\t5\n" +
                        "2018.08.13\t3\n" +
                        "2018.08.15\t1\n" +
                        "2018.08.15\t4\n" +
                        "2018.08.16\t5\n" +
                        "2018.08.19\t5\n" +
                        "2018.08.20\t3\n" +
                        "2018.08.23\t5\n" +
                        "2018.08.26\t5\n" +
                        "2018.08.27\t3\n" +
                        "2018.08.29\t1\n" +
                        "2018.08.29\t4\n" +
                        "2018.08.30\t5\n" +
                        "2018.09.03\t3\n" +
                        "2018.09.06\t5\n" +
                        "2018.09.10\t3\n" +
                        "2018.09.12\t1\n" +
                        "2018.09.12\t4\n" +
                        "2018.09.13\t5\n" +
                        "2018.09.17\t3\n" +
                        "2018.09.20\t5\n" +
                        "2018.09.24\t3\n" +
                        "2018.09.26\t1\n" +
                        "2018.09.26\t4\n" +
                        "2018.09.27\t5\n" +
                        "2018.10.01\t3\n" +
                        "2018.10.04\t5\n" +
                        "2018.10.08\t3\n" +
                        "2018.10.10\t1\n" +
                        "2018.10.10\t4\n" +
                        "2018.10.11\t5\n" +
                        "2018.10.15\t3\n" +
                        "2018.10.18\t5\n" +
                        "2018.10.22\t3\n" +
                        "2018.10.24\t1\n" +
                        "2018.10.24\t4\n" +
                        "2018.10.25\t5\n" +
                        "2018.10.29\t3\n" +
                        "2018.11.01\t5\n" +
                        "2018.11.05\t3\n" +
                        "2018.11.07\t1\n" +
                        "2018.11.07\t4\n" +
                        "2018.11.08\t5\n" +
                        "2018.11.12\t3\n" +
                        "2018.11.15\t5\n" +
                        "2018.11.19\t3\n" +
                        "2018.11.21\t1\n" +
                        "2018.11.21\t4\n" +
                        "2018.11.22\t5\n" +
                        "2018.11.26\t3\n" +
                        "2018.11.29\t5\n" +
                        "2018.12.05\t1\n" +
                        "2018.12.05\t4\n" +
                        "2018.12.06\t5\n" +
                        "2018.12.13\t5\n" +
                        "2018.12.19\t1\n" +
                        "2018.12.20\t5\n" +
                        "2018.12.27\t5\n";
                var naptarRows = naptarStr.split("\n");
                for (var naptarRow : naptarRows) {
                    var naptarCells = naptarRow.split("\t");
                    var dateParts = Arrays.stream(naptarCells[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
                    naptarRepository.save(new Naptar(new Date(dateParts[0] - 1900, dateParts[1] - 1, dateParts[2]), Integer.parseInt(naptarCells[1])));
                }
            }
            if (lakigRepository.count() == 0) {
                String lakigStr = "2018.01.04\t5\t1\n" +
                        "2018.01.11\t5\t1\n" +
                        "2018.01.18\t4\t2\n" +
                        "2018.01.18\t5\t1\n" +
                        "2018.01.17\t3\t1\n" +
                        "2018.01.24\t5\t1\n" +
                        "2018.01.30\t1\t3\n" +
                        "2018.01.31\t4\t1\n" +
                        "2018.02.01\t5\t1\n" +
                        "2018.02.08\t5\t1\n" +
                        "2018.02.13\t4\t1\n" +
                        "2018.02.15\t5\t1\n" +
                        "2018.02.22\t5\t1\n" +
                        "2018.02.27\t1\t2\n" +
                        "2018.03.02\t4\t1\n" +
                        "2018.03.01\t5\t1\n" +
                        "2018.03.04\t3\t2\n" +
                        "2018.03.08\t5\t1\n" +
                        "2018.03.15\t5\t1\n" +
                        "2018.03.21\t5\t1\n" +
                        "2018.03.29\t5\t1\n" +
                        "2018.04.03\t3\t6\n" +
                        "2018.04.05\t5\t1\n" +
                        "2018.04.11\t3\t19\n" +
                        "2018.04.09\t4\t1\n" +
                        "2018.04.10\t5\t1\n" +
                        "2018.04.19\t5\t1\n" +
                        "2018.04.26\t5\t1\n" +
                        "2018.04.29\t3\t5\n" +
                        "2018.05.03\t5\t1\n" +
                        "2018.05.06\t3\t4\n" +
                        "2018.05.10\t5\t1\n" +
                        "2018.05.16\t3\t3\n" +
                        "2018.05.17\t5\t1\n" +
                        "2018.05.21\t3\t3\n" +
                        "2018.05.22\t1\t1\n" +
                        "2018.05.24\t4\t1\n" +
                        "2018.05.24\t5\t1\n" +
                        "2018.05.27\t3\t3\n" +
                        "2018.05.31\t5\t1\n" +
                        "2018.06.03\t5\t1\n" +
                        "2018.06.04\t3\t5\n" +
                        "2018.06.04\t1\t3\n" +
                        "2018.06.06\t4\t3\n" +
                        "2018.06.07\t5\t1\n" +
                        "2018.06.10\t5\t1\n" +
                        "2018.06.10\t3\t2\n" +
                        "2018.06.14\t5\t1\n" +
                        "2018.06.17\t5\t1\n" +
                        "2018.06.20\t3\t2\n" +
                        "2018.06.20\t4\t1\n" +
                        "2018.06.21\t5\t1\n" +
                        "2018.06.24\t5\t1\n" +
                        "2018.06.25\t3\t2\n" +
                        "2018.06.27\t5\t1\n" +
                        "2018.07.01\t5\t1\n" +
                        "2018.07.02\t3\t1\n" +
                        "2018.07.02\t4\t3\n" +
                        "2018.07.05\t5\t1\n" +
                        "2018.07.08\t5\t1\n" +
                        "2018.07.08\t3\t3\n" +
                        "2018.07.12\t5\t1\n" +
                        "2018.07.15\t5\t1\n" +
                        "2018.07.16\t3\t1\n" +
                        "2018.07.18\t1\t1\n" +
                        "2018.07.19\t4\t2\n" +
                        "2018.07.19\t5\t1\n" +
                        "2018.07.22\t5\t1\n" +
                        "2018.07.22\t3\t1\n" +
                        "2018.07.26\t5\t1\n" +
                        "2018.07.29\t5\t1\n" +
                        "2018.07.31\t3\t2\n" +
                        "2018.08.26\t5\t1\n" +
                        "2018.08.29\t3\t3\n" +
                        "2018.08.27\t1\t1\n" +
                        "2018.08.29\t4\t1\n" +
                        "2018.08.30\t5\t1\n" +
                        "2018.09.01\t3\t4\n" +
                        "2018.09.06\t5\t1\n" +
                        "2018.09.09\t3\t3\n" +
                        "2018.09.10\t1\t3\n" +
                        "2018.09.13\t5\t1\n" +
                        "2018.09.18\t3\t2\n" +
                        "2018.09.20\t5\t1\n" +
                        "2018.09.23\t3\t1\n" +
                        "2018.09.24\t4\t2\n" +
                        "2018.09.27\t5\t1\n" +
                        "2018.09.29\t3\t1\n" +
                        "2018.10.04\t5\t1\n" +
                        "2018.10.10\t3\t2\n" +
                        "2018.10.10\t5\t1\n" +
                        "2018.10.15\t3\t2\n" +
                        "2018.10.18\t5\t1\n" +
                        "2018.10.22\t3\t6\n" +
                        "2018.10.25\t5\t1\n" +
                        "2018.10.31\t3\t3\n" +
                        "2018.11.01\t5\t1\n" +
                        "2018.11.04\t3\t13\n" +
                        "2018.11.05\t1\t2\n" +
                        "2018.11.07\t4\t1\n" +
                        "2018.11.15\t5\t1\n" +
                        "2018.11.17\t3\t7\n" +
                        "2018.11.22\t5\t1\n" +
                        "2018.11.26\t3\t3\n" +
                        "2018.11.29\t5\t1\n" +
                        "2018.12.06\t5\t1\n" +
                        "2018.12.13\t5\t1\n" +
                        "2018.12.19\t4\t2\n" +
                        "2018.12.20\t5\t1\n" +
                        "2018.12.26\t5\t1\n";
                var lakigRows = lakigStr.split("\n");
                for (var lakigRow : lakigRows) {
                    var lakigCells = lakigRow.split("\t");
                    var dateParts = Arrays.stream(lakigCells[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
                    lakigRepository.save(new Lakig(new Date(dateParts[0] - 1900, dateParts[1] - 1, dateParts[2]), Integer.parseInt(lakigCells[1]), Integer.parseInt(lakigCells[2])));
                }
            }
        };
    }
}